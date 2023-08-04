package com.ssafy.game.match.api.service;

import com.ssafy.game.match.db.entity.Group;
import com.ssafy.game.match.db.repository.GroupRepository;
import com.ssafy.game.match.api.request.Member;
import com.ssafy.game.match.api.response.LastTimeResponse;
import com.ssafy.game.match.api.response.MatchResponse;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.match.common.MatchStatus;
import com.ssafy.game.match.db.repository.MatchMemberSession;
import com.ssafy.game.util.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchService {
    private final int GAME_ENTER_REQUEST_WAIT_SECOND = 10;
    private final Deque<Member> matchMemberQueue;
    private final MatchMemberSession matchMemberSession;
    private final GroupRepository groupRepository;
    private final MessageSender messageSender;

    @Autowired
    public MatchService(MatchMemberSession matchMemberSession, GroupRepository groupRepository, MessageSender messageSender) {
        this.matchMemberSession = matchMemberSession;
        this.groupRepository = groupRepository;
        this.messageSender = messageSender;
        this.matchMemberQueue = new ArrayDeque<>();
    }

    public void createMatchMemberByMemberId(String memberId){
        Member matchMember = new Member(memberId);

        matchMemberQueue.offer(matchMember);
        this.matchMemberSession.insertMember(matchMember);
    }

    public void deleteMatchMemberByMemberId(String memberId){
        this.matchMemberSession.deleteMemberByMemberId(memberId);
    }

    public boolean matchable(){
        if(matchMemberQueue.size()<GameSetting.MAX_GAMEMEMBER_COUNT) return false;

        return true;
    }

    public void sendMatchResult(List<Member> groupMemberList){
        try{
            Group group = groupRepository.createNewGroup();
            sendMatchStatusToGroupMembers(groupMemberList, group,MatchStatus.MATCHED);

            int second = GAME_ENTER_REQUEST_WAIT_SECOND;
            while(second-->0){
                sendObjectToGroupMembers(
                        groupMemberList,
                        new LastTimeResponse(second)
                );

                if(group.getMembers().size() == GameSetting.MAX_GAMEMEMBER_COUNT){
                    sendMatchStatusToGroupMembers(groupMemberList, group, MatchStatus.CREATED);
//                    GameProcessor gameProcessor = new GameProcessor(game,this.messageSender);
//                    Thread thread = new Thread(gameProcessor);
//                    thread.start();
                    return;
                }

                Thread.sleep(1000);
            }

            Collection<Member> enterGroupMemberList = group.getMembers().values();

            sendMatchStatusToGroupMembers(enterGroupMemberList, group,MatchStatus.MATCHING);

            for(Member member : group.getMembers().values()){
                if(member.isConnected()){
                    this.matchMemberQueue.offerFirst(member);
                }
            }
            groupRepository.deleteGroupByGroupId(group.getGroupId());
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized List<Member> getGroupMemberList(){
        Member matchMember;
        List<Member> groupMemberList = new ArrayList<>(GameSetting.MAX_GAMEMEMBER_COUNT);

        while(!matchMemberQueue.isEmpty() && groupMemberList.size()<GameSetting.MAX_GAMEMEMBER_COUNT){
            matchMember = matchMemberQueue.poll();

            if(matchMember.isConnected()){
                groupMemberList.add(matchMember);
            }
        }

        if(groupMemberList.size() == GameSetting.MAX_GAMEMEMBER_COUNT){
            return groupMemberList;
        }

        for(Member cancelMember : groupMemberList){
            if(cancelMember.isConnected()){
                matchMemberQueue.offerFirst(cancelMember);
            }
        }

        return null;
    }

    public void enterGame(String memberId, String groupId){
        groupRepository.findGroupByGroupId(groupId)
                .getMembers()
                .put(memberId,matchMemberSession.findByMemberId(memberId));
    }

    private void sendObjectToGroupMembers(Collection<Member> groupMemberList, Object object){
        for(Member groupMember : groupMemberList){
            messageSender.sendObjectToMember(
                    groupMember.getMemberId(),
                    "/queue/match",
                    object
            );
        }
    }

    private void sendMatchStatusToGroupMembers(Collection<Member> groupMemberList, Group group, int matchStatus){
        for(Member groupMember : groupMemberList){
            messageSender.sendObjectToMember(
                    groupMember.getMemberId(),
                    "/queue/match",
                    new MatchResponse(
                            groupMember.getMemberId(),
                            group.getGroupId(),
                            matchStatus
                    )
            );
        }
    }
}

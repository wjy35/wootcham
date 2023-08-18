package com.ssafy.game.match.db.repository;

import com.ssafy.game.match.api.request.Member;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MatchMemberSession {
    private final Map<String, Member> session;

    public MatchMemberSession() {
        this.session = new HashMap<>();
    }

    public void insertMember(Member matchMember){
        session.put(matchMember.getMemberId(), matchMember);
    }

    public void deleteMemberByMemberId(String memberId){
        this.session.get(memberId).disconnect();
        this.session.remove(memberId);
    }

    public Member findByMemberId(String memberId){
        return session.get(memberId);
    }
}

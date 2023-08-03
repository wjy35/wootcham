package com.ssafy.game.match.db.repository;

import com.ssafy.game.match.api.request.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MatchMemberSession {
    private final Map<String, Member> session;

    public MatchMemberSession() {
        this.session = new HashMap<>();
    }

    public void insertMember(Member matchMember){
        session.put(matchMember.getSessionId(), matchMember);
    }

    public void deleteMemberBySessionId(String sessionId){
        this.session.get(sessionId).disconnect();
        this.session.remove(sessionId);
    }

    public Member findBySessionId(String sessionId){
        return session.get(sessionId);
    }
}

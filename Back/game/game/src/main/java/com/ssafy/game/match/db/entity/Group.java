package com.ssafy.game.match.db.entity;

import com.ssafy.game.match.api.request.Member;

import java.util.HashMap;
import java.util.Map;

public class Group {
    private String groupId;
    private Map<String, Member> members;

    public String getGroupId() {
        return groupId;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Group(String groupId) {
        this.groupId = groupId;
        this.members = new HashMap<>();
    }
}

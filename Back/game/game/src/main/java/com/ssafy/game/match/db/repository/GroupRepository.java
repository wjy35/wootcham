package com.ssafy.game.match.db.repository;

import com.ssafy.game.match.db.entity.Group;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@ToString
@Getter
public class GroupRepository {

    private Map<String, Group> groups;

    public GroupRepository() {
        this.groups = new HashMap<>();
    }

    public void deleteGroupByGroupId(String gameId){
        this.groups.remove(gameId);
    }

    public Group createNewGroup(){
        Group group = new Group(UUID.randomUUID().toString());
        this.groups.put(group.getGroupId(), group);
        return group;
    }

    public Group findGroupByGroupId(String gameId){
        return this.groups.get(gameId);
    }
}

package com.ssafy.game.match.api.observer;

import com.ssafy.game.match.api.request.Member;
import com.ssafy.game.match.api.service.MatchService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

@RequiredArgsConstructor
public class MatchObserver implements Observer {
    private final MatchService matchService;

    @Override
    public void update(Observable o, Object arg) {
        try{
            List<Member> groupMemberList = Optional.of(matchService.getGroupMemberList()).get();

            Thread thread = new Thread(
                    () -> matchService.sendMatchResult(groupMemberList)
            );
            thread.start();

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}

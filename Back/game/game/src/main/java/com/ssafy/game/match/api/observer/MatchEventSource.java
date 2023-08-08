package com.ssafy.game.match.api.observer;


import com.ssafy.game.match.api.service.MatchService;
import lombok.RequiredArgsConstructor;

import java.util.Observable;

@RequiredArgsConstructor
public class MatchEventSource extends Observable implements Runnable{

    private final MatchService matchService;

    @Override
    public void run() {
        System.out.println("matchService = " + matchService);
        while(true){
            if(matchService.matchable()){
                setChanged();
                notifyObservers();
            }
        }
    }
}

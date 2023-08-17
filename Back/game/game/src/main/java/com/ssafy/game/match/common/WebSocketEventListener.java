package com.ssafy.game.match.common;

import com.ssafy.game.game.api.service.GameService;
import com.ssafy.game.match.api.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final MatchService matchService;
    private final GameService gameService;

    @EventListener
    void disconnectSession(SessionDisconnectEvent event){
        System.out.println("diconnect");
        matchService.deleteMatchMemberByMemberId(event.getSessionId());
    }
}

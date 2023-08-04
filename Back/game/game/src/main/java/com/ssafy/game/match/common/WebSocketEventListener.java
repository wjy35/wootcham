package com.ssafy.game.match.common;

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

    @EventListener
    void connectedSession(SessionConnectedEvent event){
        System.out.println(event.getMessage().getHeaders().get("simpSessionId").toString());
        matchService.createMatchMemberBySessionId(event.getMessage().getHeaders().get("simpSessionId").toString());
    }

    @EventListener
    void disconnectSession(SessionDisconnectEvent event){
        matchService.deleteMatchMemberBySessionId(event.getSessionId());
    }
}

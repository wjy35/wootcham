package com.ssafy.game.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    private final SimpMessageSendingOperations template;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageSender(SimpMessageSendingOperations template) {
        this.template = template;
        objectMapper = new ObjectMapper();
    }

    public void sendStringToAll(String destination,String message){
        this.template.convertAndSend(destination,message);
    }

    public void sendObjectToAll(String destination,Object object){
        try {
            System.out.println(destination);
            String message = objectMapper.writeValueAsString(object);
            sendStringToAll(destination,message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendStringToMember(String memberId,String destination,String message){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setLeaveMutable(true);
        headerAccessor.setSessionId(memberId);

        template.convertAndSendToUser(
                memberId,
                destination,
                message,
                headerAccessor.getMessageHeaders()
        );
    }

    public void sendObjectToMember(String memberId,String destination,Object object){
        try {
            String message = objectMapper.writeValueAsString(object);
            sendStringToMember(memberId,destination,message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

package com.cobranet.secrethitler.ctrl;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.cobranet.secrethitler.model.Action;
import com.cobranet.secrethitler.model.ChatMessage;
import com.cobranet.secrethitler.model.PlayerGame;
import com.cobranet.secrethitler.service.GameService;
import com.cobranet.secrethitler.service.UserService;
import com.cobranet.secrethitler.user.MyUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class GameCtrl {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;




    @PostMapping("/message")
    public void sendMessageToUser(Principal p , @RequestBody ChatMessage message ) throws JsonProcessingException {
        Action a = new Action();
        ObjectMapper mapper = new ObjectMapper();
        a.setActionType("NEW_MESSAGE");
        message.setFrom(p.getName());
        message.setWhen(Timestamp.valueOf(LocalDateTime.now()));
        a.setJsonObject(mapper.writeValueAsString(message));
        simpMessagingTemplate.convertAndSend("/topic/",a);
       // simpMessagingTemplate.convertAndSend("/queue/user"+p.getName(),a);
    }

    @DeleteMapping("games")
    public void deleteGame(Principal p){
        try {
            MyUser user = userService.getUser(p);
            gameService.deleteGame(user.getId());

        } catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,e.getMessage());

            }
    }
    @PostMapping("games")
    public PlayerGame proposeGame(Principal p){
        try {
            MyUser user = userService.getUser(p);
            PlayerGame pg = gameService.proposeGame(user.getId());
            return pg;
        }
        catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    };

    @PostMapping("joingame/{gameId}")
    public List<PlayerGame> joinGame(@PathVariable Long gameId,Principal p){
        MyUser user = userService.getUser(p);
        return gameService.joinGame(user.getId(),gameId);
    }
    @GetMapping("mygame")
    public List<PlayerGame> getMyGame(Principal p){
        MyUser user = userService.getUser(p);
        return gameService.myGame(user.getId());
    }

}

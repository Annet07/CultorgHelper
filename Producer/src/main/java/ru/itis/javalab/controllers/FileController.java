package ru.itis.javalab.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.dto.ConcertApplicationDto;
import ru.itis.javalab.dto.ConcertCostumeDto;
import ru.itis.javalab.dto.NightSetupDto;
import ru.itis.javalab.dto.TehGroupDto;
import ru.itis.javalab.service.UserService;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${token.secret.key}")
    private String secretKey;

    @Autowired
    private UserService userService;

    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;

    @PostMapping("/concertApplication")
    public ResponseEntity<?> sendFile(@RequestHeader("A-TOKEN") String accessToken, @RequestBody ConcertApplicationDto concertApplicationDto){
        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String email = decodedJWT.getClaim("email").asString();
        Long userId = userService.getUserByEmail(email).getId();
        String dto = gson.toJson(concertApplicationDto);
        String message = userId + "~~~" + dto;
        CorrelationData data = new CorrelationData();
        return ResponseEntity.ok(rabbitTemplate.convertSendAndReceive("pdf.concert.application", (Object) message, data));
    }

    @PostMapping("/nightSetup")
    public ResponseEntity<?> sendFile(@RequestBody NightSetupDto nightSetupDto){
        String message = gson.toJson(nightSetupDto);
        CorrelationData data = new CorrelationData();
        return ResponseEntity.ok(rabbitTemplate.convertSendAndReceive("pdf.night.setup", (Object) message, data));
    }

    @PostMapping("/concertCostume")
    public ResponseEntity<?> sendFile(@RequestBody ConcertCostumeDto concertCostumeDto){
        String message = gson.toJson(concertCostumeDto);
        CorrelationData data = new CorrelationData();
        return ResponseEntity.ok(rabbitTemplate.convertSendAndReceive("pdf.concert.costume", (Object) message, data));
    }

    @PostMapping("/tehGroup")
    public ResponseEntity<?> sendFile(@RequestBody TehGroupDto tehGroupDto){
        String message = gson.toJson(tehGroupDto);
        CorrelationData data = new CorrelationData();
        return ResponseEntity.ok(rabbitTemplate.convertSendAndReceive("pdf.teh.group", (Object) message, data));
    }
}

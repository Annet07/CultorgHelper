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
import ru.itis.javalab.service.FileService;
import ru.itis.javalab.service.UserService;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;

    @PostMapping("/concertApplication")
    public ResponseEntity<?> sendFile(@RequestHeader("A-TOKEN") String accessToken, @RequestBody ConcertApplicationDto concertApplicationDto) throws IOException {
        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String email = decodedJWT.getClaim("email").asString();
        Long userId = userService.getUserByEmail(email).getId();
        String dto = gson.toJson(concertApplicationDto);
        String message = userId + "~~~" + dto;
        CorrelationData data = new CorrelationData();
        byte[] reply = (byte[]) rabbitTemplate.convertSendAndReceive("pdf.concert.application", (Object) message, data);
        return ResponseEntity.ok(reply);
    }

    @PostMapping("/nightSetup")
    public ResponseEntity<?> sendFile(@RequestHeader("A-TOKEN") String accessToken, @RequestBody NightSetupDto nightSetupDto) throws IOException {
        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String email = decodedJWT.getClaim("email").asString();
        Long userId = userService.getUserByEmail(email).getId();
        String dto = gson.toJson(nightSetupDto);
        String message = userId + "~~~" + dto;
        CorrelationData data = new CorrelationData();
        byte[] reply = (byte[]) rabbitTemplate.convertSendAndReceive("pdf.night.setup", (Object) message, data);
        return ResponseEntity.ok(reply);
    }

    @PostMapping("/concertCostume")
    public ResponseEntity<?> sendFile(@RequestHeader("A-TOKEN") String accessToken, @RequestBody ConcertCostumeDto concertCostumeDto) throws IOException {
        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String email = decodedJWT.getClaim("email").asString();
        Long userId = userService.getUserByEmail(email).getId();
        String dto = gson.toJson(concertCostumeDto);
        String message = userId + "~~~" + dto;
        CorrelationData data = new CorrelationData();
        byte[] reply = (byte[]) rabbitTemplate.convertSendAndReceive("pdf.concert.costume", (Object) message, data);
        return ResponseEntity.ok(reply);
    }

    @PostMapping("/tehGroup")
    public ResponseEntity<?> sendFile(@RequestHeader("A-TOKEN") String accessToken, @RequestBody TehGroupDto tehGroupDto) throws IOException {
        DecodedJWT decodedJWT = JWT.decode(accessToken);
        String email = decodedJWT.getClaim("email").asString();
        Long userId = userService.getUserByEmail(email).getId();
        String dto = gson.toJson(tehGroupDto);
        String message = userId + "~~~" + dto;
        CorrelationData data = new CorrelationData();
        byte[] reply = (byte[]) rabbitTemplate.convertSendAndReceive("pdf.teh.group", (Object) message, data);
        return ResponseEntity.ok(reply);
    }
}

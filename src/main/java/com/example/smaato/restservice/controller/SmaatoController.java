package com.example.smaato.restservice.controller;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.example.smaato.restservice.SmaatoAccept;
import com.example.smaato.restservice.service.SmaatoAcceptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
public class SmaatoController {
    private final AtomicLong counter = new AtomicLong();
    private SmaatoAcceptService acceptService;
    public SmaatoController(SmaatoAcceptService acceptService) {
        this.acceptService = acceptService;
    }
    @GetMapping("/api/smaato/accept")
    public String getDetails(@RequestParam(name = "id") Integer id,@RequestParam(required = false) String endpoint) throws IOException {
        try {
            acceptService.getResStatus(id,endpoint);
            return "ok";
        }
        catch (Exception e) {
            return "failed";
        }
    }
    @RequestMapping(value = "/smaatopost", method = RequestMethod.POST)
    public ResponseEntity < String > postSmaato(@RequestBody SmaatoAccept accept) {
        try {
            //acceptService.writePostResponse();
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
    }
}

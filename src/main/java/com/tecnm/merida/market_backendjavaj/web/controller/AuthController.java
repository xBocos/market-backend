package com.tecnm.merida.market_backendjavaj.web.controller;

import com.tecnm.merida.market_backendjavaj.domain.Client;
import com.tecnm.merida.market_backendjavaj.domain.dto.AuthenticationRequest;
import com.tecnm.merida.market_backendjavaj.domain.dto.AuthenticationResponse;
import com.tecnm.merida.market_backendjavaj.domain.service.ClientService;
import com.tecnm.merida.market_backendjavaj.web.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
        Optional<Client> clientOpt = clientService.getByEmail(request.getEmail());

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (client.getPassword() != null && passwordEncoder.matches(request.getPassword(), client.getPassword())) {
                String jwt = jwtUtil.generateToken(client.getEmail());
                return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
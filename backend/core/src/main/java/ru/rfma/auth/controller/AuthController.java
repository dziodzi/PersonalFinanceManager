package ru.rfma.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rfma.auth.dto.JwtRequest;
import ru.rfma.auth.dto.JwtRequestReg;
import ru.rfma.auth.dto.JwtResponse;
import ru.rfma.auth.dto.RefreshJwtRequest;
import ru.rfma.auth.service.AuthService;
import ru.rfma.auth.service.ClientService;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ClientService clientService;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public ResponseEntity<JwtResponse> register(@RequestBody JwtRequestReg requestReg) throws AuthException {
        final JwtResponse token = authService.register(requestReg);
        return ResponseEntity.ok(token);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void setAdminRole(@PathVariable("id") String id) throws Exception {
        clientService.setAdminRole(id);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
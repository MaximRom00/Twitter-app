package by.rom.xapp.service;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.auth.JwtRequest;
import by.rom.xapp.dto.auth.JwtResponse;
import org.springframework.security.core.Authentication;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);

    Authentication getAuthentication();

    User getAuthenticatedUser();
}

package by.rom.xapp.service;

import by.rom.xapp.dto.auth.JwtRequest;
import by.rom.xapp.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}

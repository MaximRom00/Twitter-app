package by.rom.xapp.service.impl;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.auth.JwtRequest;
import by.rom.xapp.dto.auth.JwtResponse;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.UserService;
import by.rom.xapp.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.name(), loginRequest.password()));

        User user = userService.findUserByUserName(loginRequest.name());

        return new JwtResponse(user.getId(), user.getUsername(),
                jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(), user.getAuthorities()),
                jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));

    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

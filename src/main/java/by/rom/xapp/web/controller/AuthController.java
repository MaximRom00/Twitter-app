package by.rom.xapp.web.controller;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.user.UserDto;
import by.rom.xapp.dto.auth.JwtRequest;
import by.rom.xapp.dto.auth.JwtResponse;
import by.rom.xapp.mapper.impl.UserMapperImpl;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    private final UserMapperImpl userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody JwtRequest request){
        log.info(LOGIN_USER, request);
        return authService.login(request);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody UserDto userDto){
        log.info(SAVE_USER, userDto);
        User user = userService.saveUser(userDto);
        return userMapper.toDto(user);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        log.info(REFRESH_TOKEN, refreshToken);
        return authService.refresh(refreshToken);
    }
}

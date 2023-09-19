package by.rom.xapp.web.controller;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.user.UserDto;
import by.rom.xapp.mapper.impl.UserMapperImpl;
import by.rom.xapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapperImpl mapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserDto userRequestDto){
        log.info(REGISTER_REQUEST_FROM_USER, userRequestDto);

        userService.saveUser(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        log.info(GET_USER_BY_ID, id);

        User user = userService.findById(id);
        return mapper.toDto(user);
    }

}

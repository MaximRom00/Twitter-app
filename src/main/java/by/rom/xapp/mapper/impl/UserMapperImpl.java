package by.rom.xapp.mapper.impl;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.user.UserDto;
import by.rom.xapp.exception.NotFoundException;
import by.rom.xapp.mapper.Mapper;
import by.rom.xapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static by.rom.xapp.domain.user.RoleType.*;
import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_USERDTO_TO_USER;
import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_USER_TO_USERDTO;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapperImpl implements Mapper<User, UserDto> {

    private final PasswordEncoder encoder;
    private final RoleService roleService;

    @Override
    public User toEntity(UserDto userDto) {
        log.info(MAP_USERDTO_TO_USER, userDto);

        Optional<Role> role = roleService.findRole(Role.builder().authority(ROLE_USER).build());

        if(userDto.getRole() == null){
            userDto.setRole(Set.of(
                    role.get()
            ));
        }

        else{
            userDto.getRole()
                    .forEach(searchRole -> roleService.findRole(searchRole)
                            .orElseThrow(()-> new NotFoundException("No such role: " + userDto.getRole())));
        }

        return User.builder()
                .username(userDto.getName())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .imageLink(userDto.getImageLink())
                .authorities(userDto.getRole())
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        log.info(MAP_USER_TO_USERDTO, user);

        return UserDto.builder()
                .name(user.getUsername())
                .imageLink(user.getImageLink())
                .email(user.getEmail())
                .build();
    }
}

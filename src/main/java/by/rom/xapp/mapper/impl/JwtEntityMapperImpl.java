package by.rom.xapp.mapper.impl;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.mapper.Mapper;
import by.rom.xapp.web.security.JwtEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_USER_TO_JWTENTITY;

@Component
@Slf4j
public class JwtEntityMapperImpl implements Mapper<User, JwtEntity> {

    @Override
    public User toEntity(JwtEntity K) {
        return null;
    }

    @Override
    public JwtEntity toDto(User user) {
        log.info(MAP_USER_TO_JWTENTITY, user);

        return JwtEntity.builder()
                .id(user.getId())
                .name(user.getUsername())
                .password(user.getPassword())
                .authorities(mapToGrantedAuthorities(user.getAuthorities()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(Role::getAuthority)
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

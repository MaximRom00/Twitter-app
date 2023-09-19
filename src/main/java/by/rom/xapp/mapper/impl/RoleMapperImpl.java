package by.rom.xapp.mapper.impl;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.dto.user.RoleDto;
import by.rom.xapp.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@Component
@Slf4j
public class RoleMapperImpl implements Mapper<Role, RoleDto> {
    @Override
    public Role toEntity(RoleDto roleDto) {
        log.info(MAP_ROLEDTO_TO_ROLE, roleDto);

        return Role.builder()
                .authority(roleDto.authority())
                .build();
    }

    @Override
    public RoleDto toDto(Role role) {
        log.info(MAP_ROLE_TO_ROLEDTO, role);

        return new RoleDto(role.getId(), role.getAuthority());
    }
}

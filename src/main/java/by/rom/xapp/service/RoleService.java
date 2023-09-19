package by.rom.xapp.service;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.dto.user.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService{

    Optional<Role> findRole(Role role);

    List<RoleDto> findAll();

    RoleDto save(RoleDto roleDto);
}

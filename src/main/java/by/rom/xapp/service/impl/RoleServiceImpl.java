package by.rom.xapp.service.impl;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.dto.user.RoleDto;
import by.rom.xapp.mapper.impl.RoleMapperImpl;
import by.rom.xapp.repository.RoleRepository;
import by.rom.xapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapperImpl roleMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findRole(Role role) {
        log.info(FIND_ROLE, role);
        return roleRepository.findByAuthority(role.getAuthority());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {

        log.info(FIND_ALL_ROLES);

        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto save(RoleDto roleDto) {

        log.info(SAVE_ROLE, roleDto);

        Role role = roleMapper.toEntity(roleDto);
        roleRepository.save(role);

        return roleMapper.toDto(role);
    }
}

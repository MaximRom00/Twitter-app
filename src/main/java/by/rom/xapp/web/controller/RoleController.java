package by.rom.xapp.web.controller;

import by.rom.xapp.dto.user.RoleDto;
import by.rom.xapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("@customExpression.canAccessByRole()")
    public List<RoleDto> getAllRoles(){
        return roleService.findAll();
    }

    @PostMapping("/create")
    @PreAuthorize("@customExpression.canAccessByRole()")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto createRole(@RequestBody RoleDto roleDto){
        return roleService.save(roleDto);
    }
}

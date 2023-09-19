package by.rom.xapp.dto.user;

import by.rom.xapp.domain.user.RoleType;
import jakarta.validation.constraints.NotNull;

public record RoleDto(Long id,
                      @NotNull(message = "Role is mandatory. You can choose 1 and more roles.") RoleType authority) {
}

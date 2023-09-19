package by.rom.xapp.dto.auth;

import jakarta.validation.constraints.NotNull;

public record JwtRequest(@NotNull(message = "Name must be not null") String name,
                         @NotNull(message = "Password must be not null") String password) {

}

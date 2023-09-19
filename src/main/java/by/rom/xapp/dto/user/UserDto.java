package by.rom.xapp.dto.user;

import by.rom.xapp.domain.user.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UserDto {

    @NotNull(message = "Name must be not null")
    @Size(min = 2, message = "Name length must be bigger than 2 symbols")
    private final String name;

    @NotNull(message = "Password must be not null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String password;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email must be not null")
    private final String email;

    private final String imageLink;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Role> role;

}




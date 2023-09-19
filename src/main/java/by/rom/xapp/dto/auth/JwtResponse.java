package by.rom.xapp.dto.auth;

public record JwtResponse(Long id,
                         String name,
                         String accessToken,
                         String refreshToken) {
}

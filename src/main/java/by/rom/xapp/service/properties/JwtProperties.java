package by.rom.xapp.service.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtProperties {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.access}")
    private long access;

    @Value("${security.jwt.refresh}")
    private long refresh;
}

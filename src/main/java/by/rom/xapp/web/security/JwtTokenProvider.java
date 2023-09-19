package by.rom.xapp.web.security;

import by.rom.xapp.domain.user.Role;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.auth.JwtResponse;
import by.rom.xapp.exception.ExpiredRefreshToken;
import by.rom.xapp.service.UserService;
import by.rom.xapp.service.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;

    private final UserService userService;

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long id, String userName, Set<Role> roles){
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("id", id);
        claims.put("roles", getRoles(roles));

        Instant expiredTime = Instant.now().plus(jwtProperties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expiredTime))
                .signWith(key)
                .compact();

    }

    public String createRefreshToken(Long id, String userName){
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("id", id);

        Instant expiredTime = Instant.now().plus(jwtProperties.getAccess(), ChronoUnit.HOURS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(expiredTime))
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(String refreshToken){
        if (!validateExpiredTimeToken(refreshToken)) {
            throw new ExpiredRefreshToken("Token expired!");
        }

        Long id = Long.valueOf(Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getId());

        User user = userService.findById(id);

        return new JwtResponse(id,
                user.getUsername(),
                createAccessToken(id, user.getUsername(), user.getAuthorities()),
                createRefreshToken(id, user.getUsername()));
    }

    public Authentication getAuthentication(String token){
        String userName = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public boolean validateExpiredTimeToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return !claims.getBody().getExpiration().before(new Date());
    }

    private List<String> getRoles(Set<Role> roles){
        return roles.stream()
                .map(Role::getAuthority)

                .map(Enum::name)

                .collect(Collectors.toList());
    }
}

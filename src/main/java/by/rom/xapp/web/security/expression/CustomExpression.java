package by.rom.xapp.web.security.expression;


import by.rom.xapp.domain.user.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static by.rom.xapp.domain.user.RoleType.*;

@Service
@RequiredArgsConstructor
public class CustomExpression {

    public boolean canAccess(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return hasAnyRole(authentication, ROLE_ADMIN);
    }

    private boolean hasAnyRole(Authentication authentication, RoleType... roles) {
        for (RoleType role: roles){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(simpleGrantedAuthority)){
                return true;
            }
        }
        return false;
    }
}

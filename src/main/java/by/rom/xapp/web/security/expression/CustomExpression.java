package by.rom.xapp.web.security.expression;


import by.rom.xapp.domain.Tweet;
import by.rom.xapp.domain.user.RoleType;
import by.rom.xapp.service.TweetService;
import by.rom.xapp.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static by.rom.xapp.domain.user.RoleType.*;

@Service
@RequiredArgsConstructor
public class CustomExpression {

    private final TweetService tweetService;

    public boolean canAccessByRole(){
        Authentication authentication = getAuthentication();

        return hasAnyRole(authentication, ROLE_ADMIN);
    }

    public boolean canAccessByUser(Long id){
        JwtEntity authUser = (JwtEntity) getAuthentication().getPrincipal();

        Tweet tweet = tweetService.findTweetById(id);

        return authUser.getId().equals(tweet.getUser().getId());
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

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

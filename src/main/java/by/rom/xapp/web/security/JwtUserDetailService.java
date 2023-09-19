package by.rom.xapp.web.security;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.mapper.impl.JwtEntityMapperImpl;
import by.rom.xapp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserServiceImpl userAccountService;

    private final JwtEntityMapperImpl jwtEntityMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAccountService.findUserByUserName(username);
        return jwtEntityMapper.toDto(user);
    }
}

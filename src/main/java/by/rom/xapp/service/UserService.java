package by.rom.xapp.service;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.user.UserDto;


public interface UserService {

    User saveUser(UserDto userDto);

    User findUserByUserName(String userName);

    User findById(Long id);
}

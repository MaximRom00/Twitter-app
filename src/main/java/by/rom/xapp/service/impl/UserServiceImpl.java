package by.rom.xapp.service.impl;

import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.user.UserDto;
import by.rom.xapp.exception.NotFoundException;
import by.rom.xapp.mapper.impl.UserMapperImpl;
import by.rom.xapp.repository.UserRepository;
import by.rom.xapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapperImpl userMapper;

    @Override
    @Transactional
    public User saveUser(UserDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);

        if (userRepository.existsByEmailAndUsername(user.getUsername(), user.getEmail())){
            throw new RuntimeException("User already exists");
        }
        else {
            log.info(SAVE_USER, user);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(()-> new NotFoundException("User not found with username: " + userName));
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found with id: " + id));
    }

}

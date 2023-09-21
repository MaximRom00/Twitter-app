package by.rom.xapp.mapper.impl;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.UserService;
import by.rom.xapp.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class TweetMapperImpl {

    private final UserService userService;

    public Tweet toEntity(TweetRequest tweetRequest) {
        log.info(MAP_TWEETREQUEST_TO_TWEET, tweetRequest);

        return Tweet.builder()
                .message(tweetRequest.message())
                .user(getAuthenticatedUser())
                .build();
    }

    public TweetResponse toDto(Tweet tweet){
        log.info(MAP_TWEET_TO_TWEETRESPONSE, tweet);

        return new TweetResponse(tweet.getId(),
                tweet.getMessage(),
                tweet.getCreatedTimestamp(),
                tweet.getUpdatedTimestamp());
    }

    private User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity jwtEntity = (JwtEntity) authentication.getPrincipal();

        return userService.findUserByUserName(jwtEntity.getName());
    }
 }

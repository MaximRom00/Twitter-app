package by.rom.xapp.mapper.impl.tweet;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.mapper.MapperRequest;
import by.rom.xapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_TWEETREQUEST_TO_TWEET;


@Component
@RequiredArgsConstructor
@Slf4j
public class TwitterRequestMapperImpl implements MapperRequest<Tweet, TweetRequest> {

    private final AuthService authService;

    @Override
    public Tweet requestToEntity(TweetRequest tweetRequest) {
        log.info(MAP_TWEETREQUEST_TO_TWEET, tweetRequest);

        User user = authService.getAuthenticatedUser();

        return Tweet.builder()
                .message(tweetRequest.message())
                .user(user)
                .build();
    }
}

package by.rom.xapp.mapper.impl.tweet;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.mapper.MapperResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_TWEET_TO_TWEETRESPONSE;

@Component
@RequiredArgsConstructor
@Slf4j
public class TwitterResponseMapperImpl implements MapperResponse<TweetResponse, Tweet> {

    @Override
    public TweetResponse entityToResponse(Tweet tweet){
        log.info(MAP_TWEET_TO_TWEETRESPONSE, tweet);

        return new TweetResponse(tweet.getId(),
                tweet.getMessage(),
                tweet.getCreatedTimestamp(),
                tweet.getUpdatedTimestamp());
    }
}

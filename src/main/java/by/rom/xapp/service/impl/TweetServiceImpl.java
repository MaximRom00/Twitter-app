package by.rom.xapp.service.impl;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.mapper.impl.TweetMapperImpl;
import by.rom.xapp.repository.TweetRepository;
import by.rom.xapp.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    private final TweetMapperImpl tweetMapper;

    @Override
    public TweetResponse saveTweet(TweetRequest tweetRequest) {
        Tweet tweet = tweetMapper.toEntity(tweetRequest);

        Tweet createdTweet = tweetRepository.save(tweet);

        return tweetMapper.toDto(createdTweet);
    }
}

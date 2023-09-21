package by.rom.xapp.service.impl;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.exception.NotFoundException;
import by.rom.xapp.mapper.impl.TweetMapperImpl;
import by.rom.xapp.repository.TweetRepository;
import by.rom.xapp.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    private final TweetMapperImpl tweetMapper;

    @Override
    @Transactional
    public TweetResponse saveTweet(TweetRequest tweetRequest) {
        Tweet tweet = tweetMapper.toEntity(tweetRequest);

        Tweet createdTweet = tweetRepository.save(tweet);

        return tweetMapper.toDto(createdTweet);
    }

    @Override
    @Transactional
    public TweetResponse updateTweet(TweetRequest tweetRequest) {

        return tweetRepository.findById(tweetRequest.id())
                .map(tweetDB -> {
                    Tweet tweet = tweetMapper.toEntity(tweetRequest);

                    tweet.setId(tweetRequest.id());
                    tweet.setCreatedTimestamp(tweetDB.getCreatedTimestamp());
                    tweet.setUpdatedTimestamp(tweetDB.getUpdatedTimestamp());
                    tweet.setMessage(tweetRequest.message());

                    return tweet;
                })
                .map(tweetRepository::save)
                .map(tweetMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Tweet not found with id: " + tweetRequest.id()));
    }

    @Override
    @Transactional(readOnly = true)
    public Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Tweet not found with id: " + id));
    }
}

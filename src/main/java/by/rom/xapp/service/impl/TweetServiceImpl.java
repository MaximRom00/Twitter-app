package by.rom.xapp.service.impl;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.PageSettings;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.exception.NotFoundException;
import by.rom.xapp.mapper.impl.TweetMapperImpl;
import by.rom.xapp.repository.TweetRepository;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.TweetService;
import by.rom.xapp.web.security.JwtEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    private final TweetMapperImpl tweetMapper;

    private final AuthService authService;

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
    public List<TweetResponse> getAllUserTweets(PageSettings pageSettings) {
        JwtEntity jwtEntity = (JwtEntity) authService.getAuthentication().getPrincipal();

        Long userId = jwtEntity.getId();

        Sort sort = Sort.by(Sort.Direction.ASC, "createdTimestamp");
        Pageable pageable = PageRequest.of(pageSettings.page(), pageSettings.limit(), sort);

        return tweetRepository.findAllByUser_Id(userId, pageable)
                .stream()
                .map(tweetMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Tweet not found with id: " + id));
    }

    @Override
    @Transactional
    public boolean deleteTweet(Long id) {
        return tweetRepository.findById(id)
                .map(tweet -> {
                    tweetRepository.delete(tweet);
                    return true;
                })
                .orElse(false);
    }
}

package by.rom.xapp.service;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.PageSettings;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;

import java.util.List;

public interface TweetService {
    TweetResponse saveTweet(TweetRequest tweetRequest);

    TweetResponse updateTweet(TweetRequest tweetRequest);

    List<TweetResponse> getAllUserTweets(PageSettings pageSettings);

    Tweet findTweetById(Long id);

    boolean deleteTweet(Long id);
}

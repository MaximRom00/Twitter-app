package by.rom.xapp.service;

import by.rom.xapp.domain.Tweet;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;

public interface TweetService {
    TweetResponse saveTweet(TweetRequest tweetRequest);
}

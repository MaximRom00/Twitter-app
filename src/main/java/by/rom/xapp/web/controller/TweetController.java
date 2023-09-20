package by.rom.xapp.web.controller;

import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.CREATE_NEW_TWEET;

@RestController
@RequestMapping(("/api/v1/tweets"))
@RequiredArgsConstructor
@Slf4j
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse createTweet(@Valid @RequestBody TweetRequest tweetRequest){
        log.info(CREATE_NEW_TWEET, tweetRequest);

        return tweetService.saveTweet(tweetRequest);
    }
}

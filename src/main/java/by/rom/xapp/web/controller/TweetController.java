package by.rom.xapp.web.controller;

import by.rom.xapp.dto.tweet.PageSettings;
import by.rom.xapp.dto.tweet.TweetRequest;
import by.rom.xapp.dto.tweet.TweetResponse;
import by.rom.xapp.mapper.impl.TweetMapperImpl;
import by.rom.xapp.service.TweetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@RestController
@RequestMapping(("/api/v1/tweets"))
@RequiredArgsConstructor
@Slf4j
public class TweetController {

    private final TweetService tweetService;

    private final TweetMapperImpl tweetMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse createTweet(@Valid @RequestBody TweetRequest tweetRequest){
        log.info(CREATE_NEW_TWEET, tweetRequest);

        return tweetService.saveTweet(tweetRequest);
    }

    @PutMapping
    @PreAuthorize("@customExpression.canAccessByUser(#tweetRequest.id())")
    public TweetResponse updateTweet(@Valid @RequestBody TweetRequest tweetRequest){
        log.info(EDIT_TWEET, tweetRequest);

        return tweetService.updateTweet(tweetRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@customExpression.canAccessByUser(#id)")
    public ResponseEntity<?> deleteTweet(@PathVariable Long id){
        log.info(DELETE_TWEET, id);

        return tweetService.deleteTweet(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public List<TweetResponse> getUserTweets(@Valid PageSettings pageSettings){
        log.info(GET_ALL_USER_TWEETS);

        return tweetService.getAllUserTweets(pageSettings);
    }


    @GetMapping("/{id}")
    @PreAuthorize("@customExpression.canAccessByUser(#id)")
    public TweetResponse getTweet(@PathVariable Long id){
        log.info(GET_TWEET, id);

        return tweetMapper.toDto(tweetService.findTweetById(id));
    }
}

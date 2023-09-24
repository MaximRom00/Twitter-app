package by.rom.xapp.web.controller;

import by.rom.xapp.dto.subscription.SubscribeRequest;
import by.rom.xapp.dto.subscription.UnsubscribeRequest;
import by.rom.xapp.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public void subscribe(@Valid @RequestBody SubscribeRequest subscribeRequest){
        log.info(SUBSCRIBE_TO, subscribeRequest.followerId());

        subscriptionService.subscribe(subscribeRequest);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribe(@Valid @RequestBody UnsubscribeRequest unsubscribeRequest){
        log.info(UNSUBSCRIBE_TO, unsubscribeRequest.followerId());

        subscriptionService.unsubscribe(unsubscribeRequest);
    }
}

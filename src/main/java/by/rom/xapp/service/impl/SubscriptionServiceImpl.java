package by.rom.xapp.service.impl;

import by.rom.xapp.domain.Subscription;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.subscription.SubscribeRequest;
import by.rom.xapp.dto.subscription.UnsubscribeRequest;
import by.rom.xapp.exception.SubscriptionException;
import by.rom.xapp.mapper.impl.subscription.SubscribeRequestMapperImpl;
import by.rom.xapp.mapper.impl.subscription.UnsubscribeRequestMapperImpl;
import by.rom.xapp.repository.SubscriptionRepository;
import by.rom.xapp.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final SubscribeRequestMapperImpl subscribeRequestMapper;

    private final UnsubscribeRequestMapperImpl unsubscribeRequestMapper;

    @Override
    @Transactional
    public void subscribe(SubscribeRequest subscribeRequest) {
        Subscription subscription = subscribeRequestMapper.requestToEntity(subscribeRequest);

        User follower = subscription.getFollower();
        User following = subscription.getFollowing();

        if (follower.equals(following)){
            throw new SubscriptionException("You can't subscribe to yourself!");
        }

        if (subscriptionRepository.existsByFollowerAndFollowing(follower, following)){
            throw new SubscriptionException("You've already subscribed to: " + following);
        }

        subscriptionRepository.save(subscription);
    }

    @Override
    @Transactional
    public void unsubscribe(UnsubscribeRequest unsubscribeRequest) {
        Subscription subscription = unsubscribeRequestMapper.requestToEntity(unsubscribeRequest);

        User follower = subscription.getFollower();
        User following = subscription.getFollowing();

        if (follower.equals(following)){
            throw new SubscriptionException("You can't unsubscribe to yourself!");
        }

        if (!subscriptionRepository.existsByFollowerAndFollowing(follower, following)){
            throw new SubscriptionException("You've already unsubscribed to: " + following);
        }

        subscriptionRepository
                .findByFollowerAndFollowing(follower, following)
                .ifPresent(subscriptionRepository::delete);
    }
}

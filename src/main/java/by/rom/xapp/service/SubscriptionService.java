package by.rom.xapp.service;

import by.rom.xapp.dto.subscription.SubscribeRequest;
import by.rom.xapp.dto.subscription.UnsubscribeRequest;

public interface SubscriptionService {

    void subscribe(SubscribeRequest subscribeRequest);

    void unsubscribe(UnsubscribeRequest unsubscribeRequest);

}

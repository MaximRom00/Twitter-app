package by.rom.xapp.mapper.impl.subscription;

import by.rom.xapp.domain.Subscription;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.subscription.SubscribeRequest;
import by.rom.xapp.mapper.MapperRequest;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_SUBSCRIBEREQUEST_TO_SUBSCRIPTION;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscribeRequestMapperImpl implements MapperRequest<Subscription, SubscribeRequest> {

    private final AuthService authService;

    private final UserService userService;

    @Override
    public Subscription requestToEntity(SubscribeRequest subscribeRequest) {
        log.info(MAP_SUBSCRIBEREQUEST_TO_SUBSCRIPTION, subscribeRequest);

        Subscription subscription = new Subscription();

        User currentUser = authService.getAuthenticatedUser();

        User user = userService.findById(subscribeRequest.followerId());

        subscription.setFollower(currentUser);
        subscription.setFollowing(user);

        return subscription;
    }
}

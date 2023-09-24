package by.rom.xapp.mapper.impl.subscription;

import by.rom.xapp.domain.Subscription;
import by.rom.xapp.domain.user.User;
import by.rom.xapp.dto.subscription.UnsubscribeRequest;
import by.rom.xapp.mapper.MapperRequest;
import by.rom.xapp.service.AuthService;
import by.rom.xapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static by.rom.xapp.dto.validation.ValidationMessageConstant.MAP_UNSUBSCRIBEREQUEST_TO_SUBSCRIPTION;

@Component
@RequiredArgsConstructor
@Slf4j
public class UnsubscribeRequestMapperImpl implements MapperRequest<Subscription, UnsubscribeRequest> {

    private final AuthService authService;

    private final UserService userService;

    @Override
    public Subscription requestToEntity(UnsubscribeRequest unsubscribeRequest) {
        log.info(MAP_UNSUBSCRIBEREQUEST_TO_SUBSCRIPTION, unsubscribeRequest);

        Subscription subscription = new Subscription();

        User currentUser = authService.getAuthenticatedUser();

        User user = userService.findById(unsubscribeRequest.followerId());

        subscription.setFollower(currentUser);
        subscription.setFollowing(user);

        return subscription;
    }
}
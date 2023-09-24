package by.rom.xapp.dto.subscription;

import jakarta.validation.constraints.NotNull;

public record SubscribeRequest(@NotNull Long followerId) {
}

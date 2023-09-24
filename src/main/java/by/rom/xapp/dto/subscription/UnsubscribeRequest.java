package by.rom.xapp.dto.subscription;

import jakarta.validation.constraints.NotNull;

public record UnsubscribeRequest(@NotNull Long followerId) {
}

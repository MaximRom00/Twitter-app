package by.rom.xapp.dto.tweet;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TweetRequest(
        @NotNull(message = "Message must be not null")
        @Size(min = 5, max = 200)
        String message) {
}

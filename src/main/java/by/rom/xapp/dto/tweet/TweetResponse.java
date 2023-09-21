package by.rom.xapp.dto.tweet;

import java.time.Instant;

public record TweetResponse(Long id, String message, Instant createTimestamp, Instant updatedTimestamp) {
}

package by.rom.xapp.dto.tweet;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PageSettings(@Min(0) Integer page,
                           @Min(3) @Max(100) Integer limit) {
}

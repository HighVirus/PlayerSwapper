package it.ivirus.playerswap.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PlayerSwapData {

    private final Map<UUID, Long> cooldown = new HashMap<>();
    private final Set<UUID> shooters = new HashSet<>();

    @Getter(lazy = true)
    private static final PlayerSwapData instance = new PlayerSwapData();
}

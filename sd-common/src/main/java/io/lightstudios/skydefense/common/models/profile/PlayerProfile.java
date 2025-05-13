package io.lightstudios.skydefense.common.models.profile;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class PlayerProfile {

    // defaults
    private UUID playerUUID;
    private UUID profileUUID;
    private String profileName;
    private boolean active;
    private boolean locked;

    // statistics
    private LocalDateTime profileCreated;
    private LocalDateTime profileLastLogin;
    private BigDecimal profilePlayTime;

}

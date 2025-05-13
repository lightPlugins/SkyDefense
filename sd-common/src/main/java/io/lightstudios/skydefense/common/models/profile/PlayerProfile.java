package io.lightstudios.skydefense.common.models.profile;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class PlayerProfile {

    private UUID playerUUID;
    private UUID profileUUID;
    private String profileName;
    private boolean active;
    private boolean locked;

    private BigDecimal profileXP;
    private int profileLevel;

}

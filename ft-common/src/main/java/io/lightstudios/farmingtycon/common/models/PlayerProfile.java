package io.lightstudios.farmingtycon.common.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PlayerProfile {

    private UUID playerUUID;
    private UUID profileUUID;
    private String profileName;

}

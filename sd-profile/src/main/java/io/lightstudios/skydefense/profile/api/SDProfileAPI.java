package io.lightstudios.skydefense.profile.api;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class SDProfileAPI {

    private final Map<UUID, List<PlayerProfile>> playerProfiles = new HashMap<>();


    public void switchProfile(UUID playerUUID, UUID targetProfileUUID) {
        List<PlayerProfile> profiles = playerProfiles.get(playerUUID);

        if (profiles == null) return;

        for (PlayerProfile profile : profiles) {
            // Nur das Zielprofil aktivieren
            profile.setActive(profile.getProfileUUID().equals(targetProfileUUID));
        }
    }

    public PlayerProfile getActiveProfile(UUID playerUUID) {
        List<PlayerProfile> profiles = playerProfiles.get(playerUUID);

        if (profiles == null) return null;

        for (PlayerProfile profile : profiles) {
            if (profile.isActive()) {
                return profile;
            }
        }
        return null;
    }


}

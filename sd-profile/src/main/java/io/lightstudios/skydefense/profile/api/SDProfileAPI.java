package io.lightstudios.skydefense.profile.api;

import io.lightstudios.core.util.LightTimers;
import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import io.lightstudios.skydefense.profile.implementer.custom.SwitchProfileEvent;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class SDProfileAPI {

    private final Map<UUID, List<PlayerProfile>> playerProfiles = new HashMap<>();

    public void switchProfile(UUID playerUUID, UUID targetProfileUUID) {
        List<PlayerProfile> profiles = playerProfiles.get(playerUUID);

        PlayerProfile previousProfile = getActiveProfile(playerUUID);

        if (profiles == null) return;

        for (PlayerProfile profile : profiles) {
            // Nur das Zielprofil aktivieren, nur ein Profil kann aktiv sein
            profile.setActive(profile.getProfileUUID().equals(targetProfileUUID));
        }

        SwitchProfileEvent event = new SwitchProfileEvent(previousProfile, getActiveProfile(targetProfileUUID), playerUUID);

        LightTimers.doSync(task -> {
            if(!event.isCancelled()) {
                // call event
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 0);
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

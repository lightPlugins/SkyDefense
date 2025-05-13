package io.lightstudios.skydefense.profile.implementer.bukkit;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import io.lightstudios.skydefense.profile.SDProfile;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

public class ReadActiveProfile implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        SDProfileAPI api = SDProfile.instance.getSdProfileAPI();

        // Überprüfen, ob der Spieler bereits Profile hat
        if (!api.getPlayerProfiles().containsKey(playerUUID)) {
            // Standardprofile erstellen
            PlayerProfile profile1 = new PlayerProfile();
            profile1.setPlayerUUID(playerUUID);
            profile1.setProfileUUID(UUID.randomUUID());
            profile1.setProfileName("Profil 1");
            profile1.setActive(true); // Standardmäßig aktiv

            PlayerProfile profile2 = new PlayerProfile();
            profile2.setPlayerUUID(playerUUID);
            profile2.setProfileUUID(UUID.randomUUID());
            profile2.setProfileName("Profil 2");
            profile2.setActive(false);

            PlayerProfile profile3 = new PlayerProfile();
            profile3.setPlayerUUID(playerUUID);
            profile3.setProfileUUID(UUID.randomUUID());
            profile3.setProfileName("Profil 3");
            profile3.setActive(false);

            PlayerProfile profile4 = new PlayerProfile();
            profile4.setPlayerUUID(playerUUID);
            profile4.setProfileUUID(UUID.randomUUID());
            profile4.setProfileName("Profil 4");
            profile4.setActive(false);

            PlayerProfile profile5 = new PlayerProfile();
            profile5.setPlayerUUID(playerUUID);
            profile5.setProfileUUID(UUID.randomUUID());
            profile5.setProfileName("Profil 5");
            profile5.setActive(false);

            // Profile zur Map hinzufügen
            api.getPlayerProfiles().put(playerUUID, List.of(profile1, profile2, profile3, profile4, profile5));
        }
    }
}

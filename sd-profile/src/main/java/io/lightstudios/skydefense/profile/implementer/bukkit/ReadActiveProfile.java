package io.lightstudios.skydefense.profile.implementer.bukkit;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import io.lightstudios.skydefense.profile.SDProfile;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ReadActiveProfile implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        SDProfileAPI api = SDProfile.instance.getSdProfileAPI();

        // Überprüfen, ob der Spieler bereits Profile hat,
        // wenn nicht, erstelle Standardprofile mit init values.
        // Der Spieler muss IMMER 5 Profile haben, auch wenn er sie nicht gekauft hat.
        // Erst mit einem Premium rang hat er Zugriff auf die Profile 3-5.
        // Es darf IMMER nur ein Profil aktiv sein.
        if (!api.getPlayerProfiles().containsKey(playerUUID)) {
            // Standardprofile erstellen
            PlayerProfile profile1 = new PlayerProfile();
            profile1.setPlayerUUID(playerUUID);
            profile1.setProfileUUID(UUID.randomUUID());
            profile1.setProfileName("Profil 1");
            profile1.setActive(true); // Standardmäßig aktiv
            profile1.setLocked(false); // Standardmäßig nicht gesperrt
            profile1.setProfileXP(new BigDecimal(0));
            profile1.setProfileLevel(1);

            PlayerProfile profile2 = new PlayerProfile();
            profile2.setPlayerUUID(playerUUID);
            profile2.setProfileUUID(UUID.randomUUID());
            profile2.setProfileName("Profil 2");
            profile2.setActive(false);
            profile2.setLocked(false); // Standardmäßig nicht gesperrt
            profile2.setProfileXP(new BigDecimal(0));
            profile2.setProfileLevel(1);

            PlayerProfile profile3 = new PlayerProfile();
            profile3.setPlayerUUID(playerUUID);
            profile3.setProfileUUID(UUID.randomUUID());
            profile3.setProfileName("Profil 3 (Premium)");
            profile3.setActive(false);
            profile3.setLocked(player.hasPermission("xx.xx")); // need premium
            profile3.setProfileXP(new BigDecimal(0));
            profile3.setProfileLevel(1);

            PlayerProfile profile4 = new PlayerProfile();
            profile4.setPlayerUUID(playerUUID);
            profile4.setProfileUUID(UUID.randomUUID());
            profile4.setProfileName("Profil 4 (Premium)");
            profile4.setActive(false);
            profile4.setLocked(player.hasPermission("xx.xx")); // need premium
            profile4.setProfileXP(new BigDecimal(0));
            profile4.setProfileLevel(1);

            PlayerProfile profile5 = new PlayerProfile();
            profile5.setPlayerUUID(playerUUID);
            profile5.setProfileUUID(UUID.randomUUID());
            profile5.setProfileName("Profil 5 (Premium)");
            profile5.setActive(false);
            profile5.setLocked(player.hasPermission("xx.xx")); // need premium
            profile5.setProfileXP(new BigDecimal(0));
            profile5.setProfileLevel(1);

            // Profile zur Map hinzufügen
            api.getPlayerProfiles().put(playerUUID, List.of(profile1, profile2, profile3, profile4, profile5));
        }
    }
}

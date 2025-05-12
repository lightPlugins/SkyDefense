package io.lightstudios.skydefense.profile.implementer.bukkit;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class ReadActiveProfile implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        PlayerProfile playerProfile = new PlayerProfile();

        playerProfile.setPlayerUUID(player.getUniqueId());
        playerProfile.setProfileUUID(UUID.randomUUID());
        playerProfile.setProfileName("FlowerPot");


    }
}

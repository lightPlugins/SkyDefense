package io.lightstudios.skydefense.hunt.implementer.bukkit;

import io.lightstudios.skydefense.profile.implementer.custom.SwitchProfileEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CheckForProfileSwitch implements Listener {


    @EventHandler
    public void onProfileSwitch(SwitchProfileEvent event) {

        Player player = Bukkit.getPlayer(event.getPlayerUUID());

        if(player != null) {
            player.sendMessage("Profile Switch Event Triggered!");
        }


    }
}

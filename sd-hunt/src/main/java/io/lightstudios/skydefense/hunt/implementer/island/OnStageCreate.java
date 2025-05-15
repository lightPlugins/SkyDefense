package io.lightstudios.skydefense.hunt.implementer.island;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.events.IslandCreateEvent;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class OnStageCreate implements Listener {

    @EventHandler
    public void onIslandCreate(IslandCreateEvent event) {

        SuperiorPlayer superiorPlayer = event.getPlayer();
        Player physicalPlayer = event.getPlayer().asPlayer();

        UUID islandID = event.getIsland().getUniqueId();

        Island island = SuperiorSkyblockAPI.getIslandByUUID(islandID);

        if(island == null) {
            event.setCancelled(true);
        }

    }
}

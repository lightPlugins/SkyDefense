package io.lightstudios.skydefense.common.models.hunt;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.List;

@Getter
@Setter
public class HuntStage {

    private EntityType stageEntityType;
    private List<Location> mobSpawnLocations;
    private int neededKills;

}

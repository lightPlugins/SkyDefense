package io.lightstudios.skydefense.common.models.aincraft;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;

@Getter
@Setter
public class AincraftStage {

    private String stageID;
    private int stageLevel;
    private EntityType stageMob;
    private int currentKills;
    private int nextStageKills;

}

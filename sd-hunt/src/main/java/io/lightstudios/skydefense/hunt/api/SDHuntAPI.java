package io.lightstudios.skydefense.hunt.api;

import io.lightstudios.core.util.files.MultiFileManager;
import io.lightstudios.skydefense.common.models.hunt.HuntStage;
import io.lightstudios.skydefense.hunt.SDHunt;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SDHuntAPI {

    public final List<HuntStage> huntStages = new ArrayList<>();

    public SDHuntAPI(MultiFileManager stagesFiles) {

        loadStages(stagesFiles);

    }

    private void loadStages(MultiFileManager stagesFiles) {

        for(File file : stagesFiles.getYamlFiles()) {

            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            HuntStage stage = new HuntStage();
            stage.setNeededKills(config.getInt("needed-kills", 10));

            EntityType entityType = EntityType.valueOf(config.getString("entity-type", "ZOMBIE").toUpperCase());
            stage.setStageEntityType(entityType);

            ConfigurationSection spawnLocationSection = config.getConfigurationSection("mob-spawn-locations");

            if(spawnLocationSection != null) {

                List<Location> locations = new ArrayList<>();

                for(String key : spawnLocationSection.getKeys(false)) {
                    String path = "mob-spawn-locations." + key;
                    double x = spawnLocationSection.getDouble(path + ".x");
                    double y = spawnLocationSection.getDouble(path + ".y");
                    double z = spawnLocationSection.getDouble(path + ".z");
                    String worldName = spawnLocationSection.getString(path + ".world");

                    if(worldName == null) {
                        SDHunt.instance.getConsolePrinter().printError(List.of(
                                "Could not load the spawn location " + key +" for stage " + file.getName(),
                                "The world name is null. Please check the config."
                        ));
                        return;
                    }

                    World world = SDHunt.instance.getServer().getWorld(worldName);

                    if(world == null) {
                        SDHunt.instance.getConsolePrinter().printError(List.of(
                                "Could not load the spawn location " + key + " for stage " + file.getName(),
                                "The world " + worldName + " does not exist. Please check the config."
                        ));
                        return;
                    }

                    locations.add(new Location(world, x, y, z));
                }

                stage.setMobSpawnLocations(locations);
            } else {
                SDHunt.instance.getConsolePrinter().printError(List.of(
                        "Spawn locations for stage " + file.getName() + " are not set.",
                        "Please check the target stage config."
                ));
                return;
            }

            huntStages.add(stage);

        }
    }

    public void createHuntStage(HuntStage stage, Player player) {




    }

}

package io.lightstudios.skydefense.hunt;

import org.bukkit.plugin.java.JavaPlugin;

public class SDHunt extends JavaPlugin {

    public static SDHunt instance;

    @Override
    public void onLoad() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onEnable() {
        getLogger().info("SkyDefense Hunt plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SkyDefense Hunt plugin has been disabled.");
    }

}

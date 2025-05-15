package io.lightstudios.skydefense.hunt;

import io.lightstudios.core.util.ConsolePrinter;
import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.core.util.files.MultiFileManager;
import io.lightstudios.skydefense.hunt.configs.MessagesConfig;
import io.lightstudios.skydefense.hunt.configs.SettingsConfig;
import io.lightstudios.skydefense.hunt.implementer.bukkit.CheckForProfileSwitch;
import io.lightstudios.skydefense.profile.SDProfile;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

@Getter
public class SDHunt extends JavaPlugin {

    public static SDHunt instance;
    private SDProfileAPI profileAPI;

    private ConsolePrinter consolePrinter;

    private FileManager settings;
    public FileManager messages;
    private SettingsConfig settingsConfig;
    private MessagesConfig messagesConfig;

    private MultiFileManager stageFiles;

    @Override
    public void onLoad() {
        instance = this;
        this.consolePrinter = new ConsolePrinter("§7[§rSky§bHunt§7] §r");
        profileAPI = SDProfile.instance.getSdProfileAPI();

        loadDefaults();

        if(profileAPI == null) {
            getConsolePrinter().printError(List.of(
                    "Could not load the SkyDefense Profile API.",
                    "Please make sure the Profile module is loaded before the Hunt module."
            ));
            throw new IllegalStateException("SkyDefense Profile API is not loaded.");
        }
    }

    @Override
    public void onEnable() {
        getConsolePrinter().printInfo("Enabling SkyDefense Profile Module ...");

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("SkyDefense Hunt plugin has been disabled.");
        super.onDisable();
    }

    public void loadDefaults() {
        getConsolePrinter().printInfo("Loading default files ...");
        readOrWriteConfigs();
        selectLanguage();
        getConsolePrinter().printInfo("Default files loaded.");
    }

    private void readOrWriteConfigs() {
        this.settings = new FileManager(this, "settings.yml", true);
        this.settingsConfig = new SettingsConfig(settings);

        try {
            getLogger().info("Loading stage files ...");
            this.stageFiles = new MultiFileManager("plugins/" + getName() + "/stages/");
        } catch (IOException e) {
            getConsolePrinter().printError(List.of(
                    "Could not load stage files.",
                    "Please make sure the file is not corrupted.",
                    "Error: " + e.getMessage()
            ));
        }

        getConsolePrinter().printInfo("Loaded §b" + stageFiles.getYamlFiles().size() + "§r stage file(s).");

    }

    private void selectLanguage() {
        String language = settingsConfig.getDefaultLanguage();

        switch (language) {
            case "de":
                this.messages = new FileManager(this, "language/" + "de" + ".yml", true);
                break;
            case "pl":
                this.messages = new FileManager(this, "language/" + "pl" + ".yml", true);
                break;
            default:
                this.messages = new FileManager(this, "language/" + "en" + ".yml", true);
                break;
        }

        this.messagesConfig = new MessagesConfig(messages);

    }

    private void registerEvents() {
        // collect all provided listeners given from profiles
        List<Listener> listeners = List.of(new CheckForProfileSwitch());

        PluginManager pluginManager = getServer().getPluginManager();

        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    public void registerCommands() {
    }

}

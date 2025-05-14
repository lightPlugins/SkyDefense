package io.lightstudios.skydefense.hunt;

import io.lightstudios.core.util.ConsolePrinter;
import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.skydefense.hunt.configs.MessagesConfig;
import io.lightstudios.skydefense.hunt.configs.SettingsConfig;
import io.lightstudios.skydefense.profile.SDProfile;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

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

    public void registerEvents() {
    }

    public void registerCommands() {
    }

}

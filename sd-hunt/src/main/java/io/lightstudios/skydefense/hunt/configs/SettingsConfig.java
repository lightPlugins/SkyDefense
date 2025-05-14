package io.lightstudios.skydefense.hunt.configs;

import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.skydefense.hunt.SDHunt;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class SettingsConfig {

    private final FileConfiguration config;

    public SettingsConfig(FileManager config) {
        this.config = config.getConfig();

        if(this.config == null) {
            SDHunt.instance.getConsolePrinter().printError(List.of(
                    "Failed to load default settings.yml",
                    "Please check if the file exists and try again.",
                    "Otherwise, please contact the developer."
            ));
            throw new RuntimeException("Failed to load default settings.yml");
        }
    }

    public String getDefaultLanguage() {
        return this.config.getString("default-language", "en");
    }
}

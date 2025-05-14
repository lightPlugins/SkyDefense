package io.lightstudios.skydefense.hunt.configs;

import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.skydefense.hunt.SDHunt;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class MessagesConfig {
    private final FileConfiguration config;

    public MessagesConfig(FileManager config) {
        this.config = config.getConfig();

        if(this.config == null) {
            SDHunt.instance.getConsolePrinter().printError(List.of(
                    "Failed to load default en.yml",
                    "Please check if the file exists and try again.",
                    "Otherwise, please contact the developer."
            ));
            throw new IllegalArgumentException("Failed to load default messages.yml");
        }
    }

    public String getPrefix() {
        return this.config.getString("prefix", "&7[&bSkyHunt&7] &f");
    }
}

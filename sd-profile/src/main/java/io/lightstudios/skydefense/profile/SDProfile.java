package io.lightstudios.skydefense.profile;

import io.lightstudios.core.commands.manager.CommandManager;
import io.lightstudios.core.inventory.model.InventoryData;
import io.lightstudios.core.util.ConsolePrinter;
import io.lightstudios.core.util.files.FileManager;
import io.lightstudios.core.util.files.MultiFileManager;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import io.lightstudios.skydefense.profile.commands.OpenProfileMenu;
import io.lightstudios.skydefense.profile.commands.ReloadCommand;
import io.lightstudios.skydefense.profile.configs.MessagesConfig;
import io.lightstudios.skydefense.profile.configs.SettingsConfig;
import io.lightstudios.skydefense.profile.implementer.bukkit.ReadActiveProfile;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SDProfile extends JavaPlugin {

    public static SDProfile instance;
    public ConsolePrinter consolePrinter;

    public SDProfileAPI sdProfileAPI;

    public FileManager settings;
    public SettingsConfig settingsConfig;

    public FileManager messages;
    public MessagesConfig messagesConfig;

    private MultiFileManager inventoryFiles;
    private final Map<String, InventoryData> inventories = new HashMap<>();

    @Override
    public void onLoad() {
        instance = this;
        this.consolePrinter = new ConsolePrinter("§7[§rSky§bDefense§7] §r");
        loadDefaults();
        this.sdProfileAPI = new SDProfileAPI();
    }

    @Override
    public void onEnable() {
        getConsolePrinter().printInfo("Enabling SkyDefense Profile Module ...");

        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void loadDefaults() {
        this.consolePrinter.printInfo("Loading default configs...");
        readOrWriteConfigs();
        selectLanguage();
        loadInventories();
        this.consolePrinter.printInfo("Default configs loaded.");
    }


    private void readOrWriteConfigs() {
        this.settings = new FileManager(this, "settings.yml", true);
        this.settingsConfig = new SettingsConfig(settings);

        new FileManager(this, "inventories/profile-menu.yml", true);

        try {
            this.inventoryFiles = new MultiFileManager("plugins/" + getName() + "/inventories/");
        } catch (IOException e) {
            this.consolePrinter.printError(List.of(
                    "Something went wrong on reading profile files",
                    "with exception: " + e.getMessage()
            ));
        }

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

        this.messagesConfig = new MessagesConfig(this.messages);

    }

    private void loadInventories() {

        getConsolePrinter().printInfo("(Re)Loading inventories ...");
        this.inventories.clear();

        List<File> files = inventoryFiles.getYamlFiles();
        if(files.isEmpty()) {
            getConsolePrinter().printWarning(List.of(
                    "No inventory files found in the inventories folder.",
                    "LightSlayer will not work without inventories.",
                    "Please check, if the inventories folder is not empty."));
            return;
        }

        files.forEach(file -> {
            try {
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                String id = file.getName().replace(".yml", "");
                InventoryData inventoryData = new InventoryData(config);
                this.inventories.put(id, inventoryData);
                getConsolePrinter().printInfo("Loaded inventory §b" + id);
            } catch (Exception e) {
                getConsolePrinter().printError(List.of(
                        "Could not load inventory file: " + file.getName(),
                        "with exception: " + e.getMessage()));
            }
        });
    }

    private void registerCommands() {
        // Register commands here
        new CommandManager(new ArrayList<>(List.of(
                new OpenProfileMenu(),
                new ReloadCommand()
        )), "profile");
    }

    private void registerEvents() {
        // collect all provided listeners given from profiles
        List<Listener> listeners = List.of(new ReadActiveProfile());

        PluginManager pluginManager = getServer().getPluginManager();

        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

}

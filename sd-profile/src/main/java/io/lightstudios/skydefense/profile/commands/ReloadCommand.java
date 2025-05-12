package io.lightstudios.skydefense.profile.commands;

import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.skydefense.common.permissions.ProfilePerms;
import io.lightstudios.skydefense.profile.SDProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of("reload");
    }

    @Override
    public String getDescription() {
        return "Reloads the configs";
    }

    @Override
    public String getSyntax() {
        return "/profile reload";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return ProfilePerms.PROFILE_RELOAD.getPermission();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {;
            if (args.length == 1) {
                return getSubcommand();
            }
            return List.of();
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] strings) {

        SDProfile.instance.loadDefaults();

        player.sendMessage(Component.text("<green>Reloaded Profile Configs!"));

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}

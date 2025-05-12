package io.lightstudios.skydefense.profile.commands;

import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.skydefense.common.permissions.ProfilePerms;
import io.lightstudios.skydefense.profile.inventories.ProfileMenuInv;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class OpenProfileMenu implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of("open");
    }

    @Override
    public String getDescription() {
        return "Opens the Profile Menu";
    }

    @Override
    public String getSyntax() {
        return "/profile open";
    }

    @Override
    public int maxArgs() {
        return 1;
    }

    @Override
    public String getPermission() {
        return ProfilePerms.PROFILE_OPEN.getPermission();
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

        ProfileMenuInv profileMenuInv = new ProfileMenuInv();
        profileMenuInv.openInventory(player);

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}

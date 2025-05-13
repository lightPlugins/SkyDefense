package io.lightstudios.skydefense.profile.commands;

import io.lightstudios.core.util.interfaces.LightCommand;
import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import io.lightstudios.skydefense.common.permissions.ProfilePerms;
import io.lightstudios.skydefense.profile.SDProfile;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class InviteCommand implements LightCommand {
    @Override
    public List<String> getSubcommand() {
        return List.of("invite");
    }

    @Override
    public String getDescription() {
        return "Invites a player to your profile.";
    }

    @Override
    public String getSyntax() {
        return "/profile invite <player>";
    }

    @Override
    public int maxArgs() {
        return 2;
    }

    @Override
    public String getPermission() {
        return ProfilePerms.PROFILE_INVITE.getPermission();
    }

    @Override
    public TabCompleter registerTabCompleter() {
        return (sender, command, alias, args) -> {;
            if (args.length == 1) {
                return getSubcommand();
            }
            if (args.length == 2) {
                return Bukkit.getOnlinePlayers().stream()
                        .map(Player::getName)
                        .toList();
            }
            return List.of();
        };
    }

    @Override
    public boolean performAsPlayer(Player player, String[] args) {

        PlayerProfile playerProfile = SDProfile.instance.sdProfileAPI.getActiveProfile(player.getUniqueId());

        if(playerProfile == null) {
            player.sendMessage("You don't have an active profile.");
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if(target == null) {
            player.sendMessage("Player " + args[1] +" not found.");
            return false;
        }

        PlayerProfile targetProfile = SDProfile.instance.sdProfileAPI.getActiveProfile(target.getUniqueId());

        if(targetProfile == null) {
            player.sendMessage("Player " + target.getName() + " doesn't have an active profile.");
            return false;
        }

        return false;
    }

    @Override
    public boolean performAsConsole(ConsoleCommandSender consoleCommandSender, String[] strings) {
        return false;
    }
}

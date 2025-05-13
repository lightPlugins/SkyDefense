package io.lightstudios.skydefense.profile.inventories;

import io.lightstudios.core.inventory.LightInventory;
import io.lightstudios.core.inventory.model.InventoryData;
import io.lightstudios.core.inventory.model.MenuItem;
import io.lightstudios.core.util.LightTimers;
import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import io.lightstudios.skydefense.profile.SDProfile;
import io.lightstudios.skydefense.profile.api.SDProfileAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfileMenuInv {

    private final List<UUID> isOnCooldown = new ArrayList<>();

    public LightInventory editMenu(Player player) {
        LightInventory inv = getInventory();
        SDProfileAPI api = SDProfile.instance.getSdProfileAPI();
        UUID playerUUID = player.getUniqueId();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            List<PlayerProfile> profiles = api.getPlayerProfiles().get(playerUUID);
            boolean isLocked = index >= 2; // Profile 3-5 sind gesperrt by default

            if(player.hasPermission("sdprofile.premium")) {
                // Premium-Berechtigung
                isLocked = false;
            }

            boolean isActive;

            if (profiles != null && index < profiles.size()) {
                isActive = profiles.get(index).isActive();
            } else {
                isActive = false;
            }

            boolean finalIsLocked = isLocked;
            MenuItem item = new MenuItem(getProfileItem(isLocked, isActive), (clickEvent, clickedItem) -> {
                if (finalIsLocked) {
                    player.sendMessage("Dieses Profil ist gesperrt. Kaufe Premium, um es zu entsperren.");
                    openInventory(player);
                } else {
                    if (profiles != null && index < profiles.size()) {
                        if(!isActive) {

                            if(isOnCooldown.contains(playerUUID)) {
                                player.sendMessage("Du kannst dein Profil erst wieder in 5 Sekunden wechseln.");
                                return;
                            }

                            api.switchProfile(playerUUID, profiles.get(index).getProfileUUID());
                            isOnCooldown.add(playerUUID);

                            LightTimers.doSync(task -> {
                                if(isOnCooldown.contains(playerUUID)) {
                                    isOnCooldown.remove(playerUUID);
                                    task.cancel();
                                    return;
                                }
                                task.cancel();
                            }, 20 * 5);

                        } else {
                            player.sendMessage("Dieses profil ist bereits aktiv.");
                            return;
                        }

                    } else {
                        player.sendMessage("Ungültiges Profil. Bitte versuche es erneut.");
                    }
                    player.sendMessage("Du hast zu Profil " + (index + 1) + " gewechselt.");
                    openInventory(player);
                }
            });

            inv.setItem(1, 10 + index, item);
        }
        return inv;
    }

    private ItemStack getProfileItem(boolean isLocked, boolean isActive) {
        ItemStack item = new ItemStack(isLocked ? Material.BARRIER : Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (!isLocked && isActive) {
                meta.addEnchant(Enchantment.EFFICIENCY, 1, true); // Gloweffekt hinzufügen
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS); // Verzauberungseffekt verstecken
            }
            meta.displayName(Component.text(isLocked ? "Gesperrt" : "Verfügbar")
                    .append(Component.text(" - "))
                    .append(!isLocked && isActive ? Component.text("Aktiv") : Component.text("Inaktiv")));
            item.setItemMeta(meta);
        }

        return item;
    }

    private InventoryData getMainMenu() {
        return SDProfile.instance.getInventories().get("profile-menu");
    }
    private LightInventory getInventory() {
        return new LightInventory(getMainMenu());
    }
    public void openInventory(Player player) {
        editMenu(player).open(player);
    }
}

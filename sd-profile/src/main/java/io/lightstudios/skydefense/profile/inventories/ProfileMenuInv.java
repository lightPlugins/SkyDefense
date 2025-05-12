package io.lightstudios.skydefense.profile.inventories;

import io.lightstudios.core.inventory.LightInventory;
import io.lightstudios.core.inventory.model.InventoryData;
import io.lightstudios.core.inventory.model.MenuItem;
import io.lightstudios.skydefense.profile.SDProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileMenuInv {

    private LightInventory editMenu(Player player) {
        LightInventory inv = getInventory();

        Material lockedMaterial = Material.valueOf(getMainMenu().getExtraSettings().getString("locked-slot.item", "DIAMOND").toUpperCase());
        Material activeMaterial = Material.valueOf(getMainMenu().getExtraSettings().getString("active-slot.item", "DIAMOND").toUpperCase());
        Material availableMaterial = Material.valueOf(getMainMenu().getExtraSettings().getString("available-slot.item", "DIAMOND").toUpperCase());

        List<String> lockedLore = getMainMenu().getExtraSettings().getStringList("locked-slot.lore");
        List<String> activeLore = getMainMenu().getExtraSettings().getStringList("active-slot.lore");
        List<String> availableLore = getMainMenu().getExtraSettings().getStringList("available-slot.lore");

        String lockedName = getMainMenu().getExtraSettings().getString("locked-slot.display-name", "ERROR");
        String activeName = getMainMenu().getExtraSettings().getString("active-slot.display-name", "ERROR");
        String availableName = getMainMenu().getExtraSettings().getString("available-slot.display-name", "ERROR");

        ItemStack lockedItem = new ItemStack(lockedMaterial);
        ItemStack activeItem = new ItemStack(activeMaterial);
        ItemStack availableItem = new ItemStack(availableMaterial);

        ItemMeta lockedMeta = lockedItem.getItemMeta();
        ItemMeta activeMeta = activeItem.getItemMeta();
        ItemMeta availableMeta = availableItem.getItemMeta();

        lockedMeta.displayName(Component.text(lockedName));
        activeMeta.displayName(Component.text(activeName));
        availableMeta.displayName(Component.text(availableName));

        lockedMeta.lore(lockedLore.stream().map(Component::text).toList());
        activeMeta.lore(activeLore.stream().map(Component::text).toList());
        availableMeta.lore(availableLore.stream().map(Component::text).toList());

        lockedItem.setItemMeta(lockedMeta);
        activeItem.setItemMeta(activeMeta);
        availableItem.setItemMeta(availableMeta);

        MenuItem lockedItemMenu = new MenuItem(lockedItem, (clickEvent, clickedItem) -> {
            // Handle locked item click
            player.sendMessage("This item is locked.");
        });

        MenuItem activeItemMenu = new MenuItem(activeItem, (clickEvent, clickedItem) -> {
            // Handle active item click
            player.sendMessage("This item is active.");
        });

        MenuItem availableItemMenu = new MenuItem(availableItem, (clickEvent, clickedItem) -> {
            // Handle available item click
            player.sendMessage("This item is available.");
        });

        inv.setItem(1, 12, lockedItemMenu);
        inv.setItem(1, 13, lockedItemMenu);
        inv.setItem(1, 14, lockedItemMenu);
        inv.setItem(1, 10, activeItemMenu);
        inv.setItem(1, 11, availableItemMenu);


        return inv;
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

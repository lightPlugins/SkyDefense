package io.lightstudios.skydefense.profile.implementer.custom;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SwitchProfileEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    @Getter
    private final PlayerProfile previousProfile;
    @Getter
    private final PlayerProfile newProfile;
    @Getter
    private final UUID playerUUID;

    public SwitchProfileEvent(
            PlayerProfile previousProfile,
            PlayerProfile newProfile,
            UUID playerUUID) {
        this.previousProfile = previousProfile;
        this.newProfile = newProfile;
        this.playerUUID = playerUUID;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

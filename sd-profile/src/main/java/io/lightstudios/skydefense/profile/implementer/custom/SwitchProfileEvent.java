package io.lightstudios.skydefense.profile.implementer.custom;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SwitchProfileEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    @Getter
    private final PlayerProfile previousProfile;
    @Getter
    private final PlayerProfile newProfile;

    public SwitchProfileEvent(
            PlayerProfile previousProfile,
            PlayerProfile newProfile) {
        this.previousProfile = previousProfile;
        this.newProfile = newProfile;
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
}

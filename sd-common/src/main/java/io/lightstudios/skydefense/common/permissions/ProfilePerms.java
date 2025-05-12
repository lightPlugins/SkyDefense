package io.lightstudios.skydefense.common.permissions;

import lombok.Getter;

@Getter
public enum ProfilePerms {

    PROFILE_OPEN("skydefense.commands.profile.open"),
    PROFILE_RELOAD("skydefense.commands.profile.reload"),
    PROFILE_DELETE("skydefense.profile.delete"),
    PROFILE_CREATE("skydefense.profile.create")
    ;

    private final String permission;

    ProfilePerms(String permission) {
        this.permission = permission;
    }
}

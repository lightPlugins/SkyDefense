package io.lightstudios.skydefense.profile.api;

import io.lightstudios.skydefense.common.models.profile.PlayerProfile;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class SDProfileAPI {

    private Map<UUID, List<PlayerProfile>> playerProfiles = new HashMap<>();

}

package io.github.suel_ki.foreverbabies.config.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.suel_ki.foreverbabies.common.Constants;
import io.github.suel_ki.foreverbabies.config.TinyConfig;

public class ConfigMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> TinyConfig.getScreen(parent, Constants.MOD_ID);
    }
}


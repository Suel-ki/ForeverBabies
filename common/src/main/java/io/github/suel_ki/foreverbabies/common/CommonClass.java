package io.github.suel_ki.foreverbabies.common;

import io.github.suel_ki.foreverbabies.config.ModConfig;
import io.github.suel_ki.foreverbabies.config.TinyConfig;

public class CommonClass {

    public static void init() {
        TinyConfig.init(Constants.MOD_ID, ModConfig.class);
        TinyConfig.write(Constants.MOD_ID);
    }
}
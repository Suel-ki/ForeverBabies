package io.github.suel_ki.foreverbabies;

import io.github.suel_ki.foreverbabies.common.CommonClass;
import io.github.suel_ki.foreverbabies.common.Constants;
import net.fabricmc.api.ModInitializer;

public class ForeverBabies implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();
    }
}

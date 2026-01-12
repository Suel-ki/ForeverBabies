package io.github.suel_ki.foreverbabies;

import io.github.suel_ki.foreverbabies.common.CommonClass;
import io.github.suel_ki.foreverbabies.common.Constants;
import io.github.suel_ki.foreverbabies.config.neoforge.ConfigMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

@Mod(Constants.MOD_ID)
public class ForeverBabies {

    public ForeverBabies(IEventBus eventBus) {
        CommonClass.init();
        if (FMLLoader.getCurrent().getDist() == Dist.CLIENT) {
            ConfigMenu.register();
        }
    }
}
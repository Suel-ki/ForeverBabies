package io.github.suel_ki.foreverbabies.util;

import io.github.suel_ki.foreverbabies.common.Constants;
import io.github.suel_ki.foreverbabies.config.ModConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.component.CustomData;

public class AgeUtil {
    public static boolean canPrevent(CustomData data, Mob mob) {
        if (ModConfig.entityBlacklist.contains(EntityType.getKey(mob.getType()).toString()))
            return false;
        return data.copyTag().getBooleanOr(Constants.TAG_POISONED, false) || (ModConfig.namingLock && mob.hasCustomName());
    }
}

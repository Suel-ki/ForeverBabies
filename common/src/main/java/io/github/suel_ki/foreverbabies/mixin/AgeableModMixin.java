package io.github.suel_ki.foreverbabies.mixin;

import io.github.suel_ki.foreverbabies.util.AgeUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableMob.class)
public class AgeableModMixin {
    @Shadow int age;

    @Shadow static EntityDataAccessor<Boolean> DATA_BABY_ID;

    @Inject(
            method = "setAge",
            at = @At("HEAD"),
            cancellable = true
    )
    public void setAge(int newAge, CallbackInfo cir) {
        var entity = (AgeableMob) (Object) this;
        if (entity instanceof Animal animal && animal.isBaby()) {
            CustomData data = animal.get(DataComponents.CUSTOM_DATA);
            if (AgeUtil.canPrevent(data, animal)) {
                this.age = -24000;
                animal.getEntityData().set(DATA_BABY_ID, true);
                cir.cancel();
            }
        }
    }
}

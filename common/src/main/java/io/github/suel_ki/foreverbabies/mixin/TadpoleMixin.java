package io.github.suel_ki.foreverbabies.mixin;

import io.github.suel_ki.foreverbabies.util.AgeUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Tadpole.class)
public class TadpoleMixin {

    @Shadow int age;

    @Inject(
            method = "setAge",
            at = @At("HEAD"),
            cancellable = true
    )
    public void setAge(int newAge, CallbackInfo cir) {
        var entity = (Tadpole) (Object) this;
        CustomData data = entity.get(DataComponents.CUSTOM_DATA);
        if (AgeUtil.canPrevent(data, entity)) {
            this.age = 0;
            cir.cancel();
        }
    }
}

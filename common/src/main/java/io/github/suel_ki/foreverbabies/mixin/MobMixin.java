package io.github.suel_ki.foreverbabies.mixin;

import io.github.suel_ki.foreverbabies.common.Constants;
import io.github.suel_ki.foreverbabies.config.ModConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class MobMixin {

    @Inject(
            method = "mobInteract",
            at = @At("HEAD"),
            cancellable = true
    )
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.level().isClientSide()) return;

        var entity = (Mob) (Object) this;

        if (ModConfig.entityBlacklist.contains(EntityType.getKey(entity.getType()).toString()))
            return;

        boolean isFeedable = (entity instanceof Animal && entity.isBaby()) || entity instanceof Tadpole;
        if (!isFeedable) return;

        ItemStack stack = player.getItemInHand(hand);
        CustomData data = entity.get(DataComponents.CUSTOM_DATA);
        boolean isPoisoned = data != null && data.copyTag().getBooleanOr(Constants.TAG_POISONED, false);

        if (stack.is(Items.POISONOUS_POTATO) && !isPoisoned) {
            entity.playSound(SoundEvents.GENERIC_EAT.value(), 1, 1);
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 80));

            data = data.update(tag -> tag.putBoolean(Constants.TAG_POISONED, true));
            entity.setComponent(DataComponents.CUSTOM_DATA, data);

            stack.consume(1, player);

            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
        }

        if (stack.is(Items.MILK_BUCKET) && isPoisoned) {
            entity.playSound(SoundEvents.GENERIC_DRINK.value(), 1, 1);
            if (entity.hasEffect(MobEffects.POISON)) {
                entity.removeEffect(MobEffects.POISON);
            }

            data = data.update(tag -> tag.remove(Constants.TAG_POISONED));
            entity.setComponent(DataComponents.CUSTOM_DATA, data);

            if (!player.getAbilities().instabuild) {
                ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                stack.shrink(1);
                if (!player.addItem(emptyBucket)) {
                    player.drop(emptyBucket, false);
                }
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}

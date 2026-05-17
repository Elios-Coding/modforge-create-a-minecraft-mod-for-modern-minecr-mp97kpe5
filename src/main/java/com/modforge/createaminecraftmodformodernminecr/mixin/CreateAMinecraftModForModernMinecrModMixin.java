package com.modforge.createaminecraftmodformodernminecr.mixin;

import com.modforge.createaminecraftmodformodernminecr.CreateAMinecraftModForModernMinecrMod;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class CreateAMinecraftModForModernMinecrModMixin {
    private static final int MULTIPLIER = 2; // change this to taste

    @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    private void modforge_multiplyOnPickup(PlayerEntity player, CallbackInfo ci) {
        try {
            ItemEntity self = (ItemEntity) (Object) this;
            if (self.getEntityWorld().isClient()) return;
            ItemStack stack = self.getStack();
            if (stack.isEmpty()) return;
            int extra = MULTIPLIER - 1;
            if (extra <= 0) return;
            int original = stack.getCount();
            int newCount = Math.min(original * MULTIPLIER, stack.getMaxCount() * MULTIPLIER);
            stack.setCount(newCount);
        } catch (Throwable ignored) {}
    }
}

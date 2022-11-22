package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.LoomScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.LoomMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin extends AbstractContainerScreen<LoomMenu> {
    public LoomScreenMixin(LoomMenu a, Inventory b, Component c) {
        super(a, b, c);
    }

    @ModifyConstant(method = "containerChanged()V", constant = @Constant(intValue = 6))
    private int modifyBannerPatternAmount(int orig) {
        return BannerUnlimited.getAmountAllowed();
    }
}
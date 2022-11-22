package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(LoomMenu.class)
public abstract class LoomContainerMixin extends AbstractContainerMenu {
    protected LoomContainerMixin(int p_39859_, Inventory p_39860_, final ContainerLevelAccess p_39861_) {
        super(MenuType.LOOM, p_39859_);
    }

    @ModifyConstant(method = "slotsChanged(Lnet/minecraft/world/Container;)V", constant = @Constant(intValue = 6))
    public int modifyCraftMatrixAmount(int old) {
        return BannerUnlimited.getAmountAllowed();
    }
}
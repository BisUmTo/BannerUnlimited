package com.gkoliver.bannerunlimited.mixin;

import com.gkoliver.bannerunlimited.BannerUnlimited;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(BannerItem.class)
public class BannerItemMixin extends StandingAndWallBlockItem {

    public BannerItemMixin(Block floorBlock, Block wallBlockIn, Item.Properties propertiesIn) {
        super(floorBlock, wallBlockIn, propertiesIn);
    }

    @Inject(method = "appendHoverTextFromBannerBlockEntityTag", at = @At("HEAD"), cancellable = true)
    private static void appendHoverTextFromTileEntityTagMixin(ItemStack itemStack, List<Component> list, CallbackInfo ci) {
        CompoundTag compoundNbt = BlockItem.getBlockEntityData(itemStack);
        if (compoundNbt != null && compoundNbt.contains("Patterns")) {
            ListTag listnbt = compoundNbt.getList("Patterns", 10);

            for (int i = 0; i < listnbt.size() && i < BannerUnlimited.getAmountAllowed(); ++i) {
                CompoundTag compoundTag2 = listnbt.getCompound(i);
                DyeColor dyeColor = DyeColor.byId(compoundTag2.getInt("Color"));
                Holder<BannerPattern> holder = BannerPattern.byHash(compoundTag2.getString("Pattern"));
                if (holder != null) {
                    holder.unwrapKey().map((resourceKey) -> {
                        return resourceKey.location().toShortLanguageKey();
                    }).ifPresent((string) -> {
                        list.add(Component.translatable("block.minecraft.banner." + string + "." + dyeColor.getName()).withStyle(ChatFormatting.GRAY));
                    });
                }
            }
        }
        ci.cancel();
    }
}
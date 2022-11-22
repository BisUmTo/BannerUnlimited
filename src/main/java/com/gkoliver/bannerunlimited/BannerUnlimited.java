package com.gkoliver.bannerunlimited;


import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BannerUnlimited implements ModInitializer {
    public static final String MOD_ID = "bannerunlimited";
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static BannerUnlimitedConfigs CONFIG;

    public static int getAmountAllowed() {
        return CONFIG.MAX_AMOUNT;
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(BannerUnlimitedConfigs.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(BannerUnlimitedConfigs.class).getConfig();
        LOGGER.info("Banner Unlimited has been initialized!");
    }
}

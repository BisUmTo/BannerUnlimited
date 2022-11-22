package com.gkoliver.bannerunlimited;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = BannerUnlimited.MOD_ID)
public class BannerUnlimitedConfigs implements ConfigData {
    @ConfigEntry.BoundedDiscrete(min = 0, max = 65535)
    int MAX_AMOUNT = 65535;
}
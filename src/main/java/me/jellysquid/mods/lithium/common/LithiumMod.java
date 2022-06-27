package me.jellysquid.mods.lithium.common;

import me.jellysquid.mods.lithium.common.config.LithiumConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(LithiumMod.MODID)
public class LithiumMod {
    public static LithiumConfig CONFIG;
    
    public static final String MODID = "radium";

    public LithiumMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onInitialize);
    }
    
    public void onInitialize(final FMLCommonSetupEvent event) {
        if (CONFIG == null) {
            throw new IllegalStateException("The mixin plugin did not initialize the config! Did it not load?");
        }
    }
}

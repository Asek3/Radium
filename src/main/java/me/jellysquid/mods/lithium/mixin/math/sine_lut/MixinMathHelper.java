package me.jellysquid.mods.lithium.mixin.math.sine_lut;

import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.jellysquid.mods.lithium.common.util.math.MathUtils;

@Mixin(MathHelper.class)
public class MixinMathHelper {
    /**
     * @author jellysquid3
     * @reason use an optimized implementation
     */
    @Overwrite
    public static float sin(float f) {
    	return MathUtils.sin(f);
    }

    /**
     * @author jellysquid3
     * @reason use an optimized implementation
     */
    @Overwrite
    public static float cos(float f) {
    	return MathUtils.cos(f);
    }
}

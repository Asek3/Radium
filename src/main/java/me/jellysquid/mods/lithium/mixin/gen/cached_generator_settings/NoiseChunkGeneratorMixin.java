package me.jellysquid.mods.lithium.mixin.gen.cached_generator_settings;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
    @Shadow
    @Final
    protected Supplier<ChunkGeneratorSettings> settings;
    private int cachedSeaLevel;

    /**
     * Use cached sea level instead of retrieving from the registry every time.
     * This method is called for every block in the chunk so this will save a lot of registry lookups.
     *
     * @author SuperCoder79
     * @reason avoid registry lookup
     */
    @Overwrite
    public int getSeaLevel() {
    	if (cachedSeaLevel == -1) {
            this.cachedSeaLevel = this.settings.get().getSeaLevel();
        }
        return this.cachedSeaLevel;
    }

    /**
     * Initialize the cache early in the ctor to avoid potential future problems with uninitialized usages
     */
    /*@Inject(
            method = "<init>(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/world/biome/source/BiomeSource;JLjava/util/function/Supplier;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;getGenerationShapeConfig()Lnet/minecraft/world/gen/chunk/GenerationShapeConfig;",
                    shift = At.Shift.BEFORE
            )
    )
    private void hookConstructor(Registry<DoublePerlinNoiseSampler.NoiseParameters> noiseRegistry, BiomeSource biomeSource, BiomeSource biomeSource2, long seed, Supplier<ChunkGeneratorSettings> supplier, CallbackInfo ci) {
        this.cachedSeaLevel = this.settings.get().getSeaLevel();
    }*/
}

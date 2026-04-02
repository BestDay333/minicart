package com.example.nofog.mixin;

import com.example.nofog.NoFogMod;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void onApplyFog(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float fogThickness, CallbackInfoReturnable<FogRenderer.FogData> cir) {
        if (!NoFogMod.fogEnabled) {
            // Возвращаем данные без тумана (минимальная дистанция 0, максимальная очень большая)
            cir.setReturnValue(new FogRenderer.FogData(0.0f, 1000000.0f, false, FogRenderer.FogType.FOG_TERRAIN));
        }
    }
}

package bikerboys.clearview.mixin.client;
import bikerboys.clearview.ClearviewConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static bikerboys.clearview.ClearviewConfig.fogEnd;
import static bikerboys.clearview.ClearviewConfig.fogStart;


@Mixin(BackgroundRenderer.class)
public class ExampleClientMixin  {
    @Shadow
    private static boolean fogEnabled = !ClearviewConfig.FogDisabled;

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void alwaysSpectatorFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        // Force spectator fog settings
        FogShape fogShape = FogShape.SPHERE;
        cir.setReturnValue(new Fog(fogStart, fogEnd, fogShape, color.x, color.y, color.z, color.w));

    }

}
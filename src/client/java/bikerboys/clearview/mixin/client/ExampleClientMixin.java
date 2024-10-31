package bikerboys.clearview.mixin.client;

import bikerboys.clearview.ClearviewConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class ExampleClientMixin  {

   @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
   private static void alwaysSpectatorFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
       // Force spectator fog settings
       float fogStart = ClearviewConfig.fogStart;
       float fogEnd = ClearviewConfig.fogEnd;

       // Set the fog shape, if needed
       FogShape fogShape = FogShape.SPHERE;

       // Apply the fog settings
       RenderSystem.setShaderFogStart(fogStart);
       RenderSystem.setShaderFogEnd(fogEnd);
       RenderSystem.setShaderFogShape(fogShape);

       // Cancel the rest of the method
       ci.cancel();
   }




}
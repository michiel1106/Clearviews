package bikerboys.clearview.mixin.client;

import bikerboys.clearview.ClearviewConfig;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BackgroundRenderer;


import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.lang.Float.*;

@Mixin(BackgroundRenderer.class)
public class ExampleClientMixin  {
    @Shadow
    private static final int WATER_FOG_LENGTH = 0;

   @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
   private static void alwaysSpectatorFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
       // Force spectator fog settings
       float fogStart = ClearviewConfig.FogStart;
       float fogEnd = ClearviewConfig.FogEnd;

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
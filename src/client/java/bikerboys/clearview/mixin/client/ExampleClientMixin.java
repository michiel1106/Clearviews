package bikerboys.clearview.mixin.client;

import bikerboys.clearview.ClearviewClient;
import bikerboys.clearview.ClearviewConfig;
import bikerboys.clearview.FogData;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.buffers.Std140Builder;
import net.minecraft.client.gl.ShaderLoader;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.Camera;

import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.ByteBuffer;

@Mixin(value = FogRenderer.class, priority = 1)
public class ExampleClientMixin  {

    @Shadow private static boolean fogEnabled;

    @Inject(method = "applyFog(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V", at = @At(value = "HEAD"), cancellable = true)
   private void alwaysSpectatorFog(ByteBuffer buffer, int bufPos, Vector4f fogColor, float environmentalStart, float environmentalEnd, float renderDistanceStart, float renderDistanceEnd, float skyEnd, float cloudEnd, CallbackInfo ci) {
       if (ClearviewConfig.FogDisabled) {
           fogEnabled = ClearviewConfig.FogDisabled;
           buffer.position(bufPos);
           Std140Builder.intoBuffer(buffer)
                   .putVec4(fogColor)
                   .putFloat(ClearviewConfig.fogStart)
                   .putFloat(ClearviewConfig.fogEnd)
                   .putFloat(ClearviewConfig.fogStart)
                   .putFloat(ClearviewConfig.fogEnd)
                   .putFloat(skyEnd)
                   .putFloat(cloudEnd);




           ci.cancel();
       }
   }






}
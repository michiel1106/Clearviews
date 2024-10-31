package bikerboys.clearview.mixin.client;


import bikerboys.clearview.ClearviewConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class portaloverlay {


    private DrawContext context;
    private float nauseaStrength;

    @Shadow protected abstract void renderNauseaOverlay(DrawContext context, float nauseaStrength);

    /**
     * @author d
     * @reason d
     */

    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void renderPortalOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {

        if (ClearviewConfig.PortalOverlayDisabled) {
            ci.cancel();
        }
    }
    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void changespygalass(DrawContext context, float scale, CallbackInfo ci) {
        if (ClearviewConfig.RemoveSpyglassBorder) {
            ci.cancel();
        }


    }


   // @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
   // private void renderNauseaOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {
   //     if (ClearviewConfig.PortalOverlayDisabled) {
   //         ci.cancel();
   //     }
   // }
}

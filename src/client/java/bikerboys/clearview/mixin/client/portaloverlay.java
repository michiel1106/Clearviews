package bikerboys.clearview.mixin.client;


import bikerboys.clearview.ClearviewConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class portaloverlay {


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

    /**
     * @author d
     * @reason d
     */

   // @Inject(method = "renderNauseaOverlay", at = @At("HEAD"), cancellable = true)
   // private void renderNauseaOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {
   //     if (ClearviewConfig.PortalOverlayDisabled) {
   //         ci.cancel();
   //     }
   // }
}

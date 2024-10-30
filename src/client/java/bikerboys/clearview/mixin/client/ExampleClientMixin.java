package bikerboys.clearview.mixin.client;
import bikerboys.clearview.ClearviewConfig;
import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(BackgroundRenderer.class)
public class ExampleClientMixin  {
    @Shadow
    private static boolean fogEnabled = !ClearviewConfig.FogDisabled;


}
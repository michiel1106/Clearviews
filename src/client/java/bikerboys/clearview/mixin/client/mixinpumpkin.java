package bikerboys.clearview.mixin.client;

import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public class mixinpumpkin {


    @Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item$Settings;component(Lnet/minecraft/component/ComponentType;Ljava/lang/Object;)Lnet/minecraft/item/Item$Settings;", slice = "CARVED_PUMPKIN"))
    private static void isdone(CallbackInfo ci) {

    }
}

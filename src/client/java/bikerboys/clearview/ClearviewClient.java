package bikerboys.clearview;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;



import static net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.*;

public class ClearviewClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Register a client tick event listener

		END_CLIENT_TICK.register(minecraftClient -> {
			if (minecraftClient.player != null) {
				StatusEffectInstance darknessEffect = minecraftClient.player.getStatusEffect(StatusEffects.DARKNESS);
				StatusEffectInstance blindnessEffect = minecraftClient.player.getStatusEffect(StatusEffects.BLINDNESS);
				StatusEffectInstance nauseaEffect = minecraftClient.player.getStatusEffect(StatusEffects.NAUSEA);

				if (darknessEffect != null && ClearviewConfig.DarknessEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.DARKNESS);
				}
				if (blindnessEffect != null && ClearviewConfig.BlindnessEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.BLINDNESS);
				}
				if (nauseaEffect != null && ClearviewConfig.NauseaEffect) {
					minecraftClient.player.removeStatusEffect(StatusEffects.NAUSEA);
				}

			}

		});

	}


}
package bikerboys.clearview;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;


import static net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.*;

public class ClearviewClient implements ClientModInitializer {
	private static KeyBinding fog;
	private static KeyBinding portal;
	private static KeyBinding spyglass;
	private static KeyBinding darkness;
	private static KeyBinding blindness;
	private static KeyBinding nausea;

	public static final String MOD_ID = "clearview";

	@Override
	public void onInitializeClient() {
		// Register a client tick event listener


		darkness = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.darkness",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);

		blindness = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.blindness",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);

		nausea = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.nausea",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);

		fog = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.fog",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);

		spyglass = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.spyglass",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);

		portal = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.clearview.portal",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"key.category.clearview")
		);


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

				if (darkness.wasPressed()) {
					ClearviewConfig.DarknessEffect = !ClearviewConfig.DarknessEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove darkness is now " + ClearviewConfig.DarknessEffect), true);
				}
				if (blindness.wasPressed()) {
					ClearviewConfig.BlindnessEffect = !ClearviewConfig.BlindnessEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove blindness is now " + ClearviewConfig.BlindnessEffect), true);
				}
				if (nausea.wasPressed()) {
					ClearviewConfig.NauseaEffect = !ClearviewConfig.NauseaEffect;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove nausea is now " + ClearviewConfig.NauseaEffect), true);
				}
				if (fog.wasPressed()){
					ClearviewConfig.FogDisabled = !ClearviewConfig.FogDisabled;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove fog is now " + ClearviewConfig.FogDisabled), true);
				}

				if (portal.wasPressed()){
					ClearviewConfig.PortalOverlayDisabled = !ClearviewConfig.PortalOverlayDisabled;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove portal overlay is now " + ClearviewConfig.PortalOverlayDisabled), true);
				}
				if (spyglass.wasPressed()){
					ClearviewConfig.RemoveSpyglassBorder = !ClearviewConfig.RemoveSpyglassBorder;
					ClearviewConfig.write(MOD_ID);
					minecraftClient.player.sendMessage(Text.of("Remove spyglass border is now " + ClearviewConfig.RemoveSpyglassBorder), true);
				}


			}

		});

	}


}
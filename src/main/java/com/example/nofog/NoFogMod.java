package com.example.nofog;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoFogMod implements ClientModInitializer {
    public static final String MOD_ID = "nofog";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static boolean fogEnabled = true;
    
    private static KeyBinding toggleKeyBinding;

    @Override
    public void onInitializeClient() {
        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.nofog.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F6,
            "category.nofog"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeyBinding.wasPressed()) {
                fogEnabled = !fogEnabled;
                if (fogEnabled) {
                    LOGGER.info("Fog enabled");
                } else {
                    LOGGER.info("Fog disabled");
                }
            }
        });

        LOGGER.info("NoFog mod initialized! Press F6 to toggle fog.");
    }
}

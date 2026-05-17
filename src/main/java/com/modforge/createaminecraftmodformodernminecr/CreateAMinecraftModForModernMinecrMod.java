package com.modforge.createaminecraftmodformodernminecr;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CreateAMinecraftModForModernMinecrMod implements ModInitializer {
    public static final String MOD_ID = "create-a-minecraft-mod-for-modern-minecr-mp97kpe5";
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MOD_ID);

    /** Global TNT explosion power multiplier. Default: 1.0 */
    public static volatile double TNT_POWER_MULTIPLIER = 1.0d;

    @Override
    public void onInitialize() {
        try {
        LOGGER.info("ModForge: {} loaded", MOD_ID);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, env) -> {
            try {
                dispatcher.register(literal("tntpower")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(argument("multiplier", DoubleArgumentType.doubleArg(0.0d))
                        .executes(ctx -> {
                            final double multiplier = DoubleArgumentType.getDouble(ctx, "multiplier");
                            TNT_POWER_MULTIPLIER = multiplier;
                            ctx.getSource().sendFeedback(
                                () -> Text.literal("Global TNT multiplier set to " + multiplier),
                                true
                            );
                            return 1;
                        }))
                    .executes(ctx -> {
                        ctx.getSource().sendFeedback(
                            () -> Text.literal("Usage: /tntpower <multiplier>"),
                            false
                        );
                        return 1;
                    })
                );
            } catch (Throwable t) {
                LOGGER.error("ModForge: failed to register /tntpower", t);
            }
        });
    
        } catch (Throwable __modforge_t) {
            LOGGER.error("ModForge: onInitialize failed", __modforge_t);
        }
    }
}

package io.github.suel_ki.foreverbabies.config.neoforge;

import io.github.suel_ki.foreverbabies.common.Constants;
import io.github.suel_ki.foreverbabies.config.TinyConfig;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

public class ConfigMenu {
    public static void register() {
        ModLoadingContext.get().
                registerExtensionPoint(IConfigScreenFactory.class,
                        () -> new IConfigScreenFactory() {
                            @Override
                            public @NotNull Screen createScreen(@NotNull ModContainer modContainer, @NotNull Screen parent) {
                                return TinyConfig.getScreen(parent, Constants.MOD_ID);
                            }
                        });
    }
}

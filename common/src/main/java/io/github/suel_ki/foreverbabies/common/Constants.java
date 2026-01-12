package io.github.suel_ki.foreverbabies.common;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	public static final String MOD_ID = "foreverbabies";
	public static final String MOD_NAME = "ForeverBabies";
    public static final String TAG_POISONED = MOD_ID + ":poison_potato_applied";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
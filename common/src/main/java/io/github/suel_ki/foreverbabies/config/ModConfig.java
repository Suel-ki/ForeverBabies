package io.github.suel_ki.foreverbabies.config;

import com.google.common.collect.Lists;

import java.util.List;

public class ModConfig extends TinyConfig {

    @Entry(category = "common")
    public static boolean namingLock = false;

    @Entry(category = "common")
    public static List<String> entityBlacklist = Lists.newArrayList("foreverbabies:me");
}

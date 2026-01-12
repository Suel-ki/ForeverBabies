package io.github.suel_ki.foreverbabies.config;

import com.google.common.collect.Lists;

import java.util.List;

public class ModConfig extends TinyConfig {

    @Entry
    public static boolean namingLock = false;

    @Entry
    public static List<String> entityBlacklist = Lists.newArrayList("foreverbabies:me");

    @Entry
    public static List<String> nameBlacklist = Lists.newArrayList("foreverbabies");
}

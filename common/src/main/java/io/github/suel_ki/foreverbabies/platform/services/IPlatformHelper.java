package io.github.suel_ki.foreverbabies.platform.services;

import java.nio.file.Path;

public interface IPlatformHelper {

    boolean isModLoaded(String modId);

    boolean isClient();

    Path getConfigDir();
}
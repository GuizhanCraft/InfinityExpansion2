package net.guizhanss.infinityexpansion2.core.services;

import net.byteflux.libby.LibraryManager;
import net.byteflux.libby.classloader.URLClassLoaderHelper;
import net.byteflux.libby.logging.adapters.JDKLogAdapter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.net.URLClassLoader;
import java.nio.file.Path;

/**
 * We just want to use the root libraries folder, and kotlin runtime is not available yet, so we need this Java class to load the libraries.
 */
public class BukkitLibraryService extends LibraryManager {

    private final URLClassLoaderHelper classLoader;

    public BukkitLibraryService(Plugin plugin) {
        super(new JDKLogAdapter(plugin.getLogger()), new File(".").toPath(), "libraries");
        this.classLoader = new URLClassLoaderHelper((URLClassLoader) plugin.getClass().getClassLoader(), this);
    }

    protected void addToClasspath(Path file) {
        this.classLoader.addToClasspath(file);
    }
}


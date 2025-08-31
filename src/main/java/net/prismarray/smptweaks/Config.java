package net.prismarray.smptweaks;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config {

    String fileName;
    private File configFile;
    private FileConfiguration config;

    protected Config(String fileName) {
        this.fileName = fileName;

        load();
    }


    public void load() {
        configFile = new File(SMPTweaks.getInstance().getDataFolder(), fileName);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            SMPTweaks.getInstance().saveResource(fileName, false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().warning(String.format("FAILED TO SAVE %s!", fileName));
        };
    }

    public String getFileName() {
        return fileName;
    }

    protected FileConfiguration getConfig() {
        return config;
    }
}

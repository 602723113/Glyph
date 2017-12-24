package cc.deepinmc.glyph.manager;

import cc.deepinmc.glyph.Entry;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Be used for manage config
 *
 * @author Zoyn
 * @since 2017-12-24
 */
public class LanguageConfigManager {

    private static FileConfiguration config = Entry.getInstance().getLanguageConfig();

    /**
     * 你不能实例化 LanguageConfigManager
     * <br />
     * You can not instantiate the LanguageConfigManager
     */
    private LanguageConfigManager() {
    }

    /**
     * 取当前 config 的 FileConfiguration 对象
     * <br />
     * get the current config FileConfiguration object
     *
     * @return {@link FileConfiguration}
     */
    public static FileConfiguration getLanguageConfig() {
        return config;
    }

    public static boolean getBooleanByDefault(String key, boolean defaultValue) {
        if (config.contains(key) && config.isBoolean(key)) {
            return config.getBoolean(key);
        }
        return defaultValue;
    }

    /**
     * 取config内的StringList
     * <br />
     * get config's string list
     *
     * @param key          key name
     * @param defaultValue default value
     * @param translate    Whether to convert color character codes
     * @return {@link List}
     */
    public static List<String> getStringListByDefault(String key, List<String> defaultValue, boolean translate) {
        if (config.contains(key) && config.isList(key) && !config.getList(key).isEmpty()) {
            if (translate) {
                return config.getStringList(key)
                        .stream()
                        .map(
                                s -> ChatColor.translateAlternateColorCodes('&', s)
                        )
                        .collect(Collectors.toList());
            } else {
                return config.getStringList(key);
            }
        }
        if (translate) {
            return defaultValue.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList());
        } else {
            return defaultValue;
        }
    }

    /**
     * 取 config 内的 string 对象
     * <br />
     * get config's string object
     *
     * @param key          key name
     * @param defaultValue default value
     * @param translate    Whether to convert color character codes
     * @return {@link String}
     */
    public static String getStringByDefault(String key, String defaultValue, boolean translate) {
        if (config.contains(key) && config.isString(key)) {
            if (translate) {
                return ChatColor.translateAlternateColorCodes('&', config.getString(key));
            } else {
                return config.getString(key);
            }
        }
        if (translate) {
            return ChatColor.translateAlternateColorCodes('&', defaultValue);
        } else {
            return defaultValue;
        }
    }

    public static int getIntByDefault(String key, int defaultValue) {
        if (config.contains(key) && config.isInt(key)) {
            return config.getInt(key);
        }
        return defaultValue;
    }

    public static double getDoubleByDefault(String key, double defaultValue) {
        if (config.contains(key) && config.isDouble(key)) {
            return config.getDouble(key);
        }
        return defaultValue;
    }

    public static long getLongByDefault(String key, long defaultValue) {
        if (config.contains(key) && config.isLong(key)) {
            return config.getLong(key);
        }
        return defaultValue;
    }

}

package coordinatedisplay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles saving and loading mod settings.
 */
public class CoordinateConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/coordinate-display.json");

    // === SETTINGS ===
    public boolean toggle = true;  // whether the HUD is shown

    // === SINGLETON INSTANCE ===
    private static CoordinateConfig INSTANCE = new CoordinateConfig();

    public static CoordinateConfig getInstance() {
        return INSTANCE;
    }

    // === LOAD CONFIG FROM DISK ===
    public static void load() {
        if (!FILE.exists()) {
            save(INSTANCE);
            return;
        }

        try (FileReader reader = new FileReader(FILE)) {
            INSTANCE = GSON.fromJson(reader, CoordinateConfig.class);
        } catch (IOException e) {
            System.err.println("[CoordinateDisplay] Failed to load config: " + e.getMessage());
        }
    }

    // === SAVE CONFIG TO DISK ===
    public static void save(CoordinateConfig config) {
        try {
            if (!FILE.getParentFile().exists()) {
                FILE.getParentFile().mkdirs();
            }
            try (FileWriter writer = new FileWriter(FILE)) {
                GSON.toJson(config, writer);
            }
        } catch (IOException e) {
            System.err.println("[CoordinateDisplay] Failed to save config: " + e.getMessage());
        }
    }
}

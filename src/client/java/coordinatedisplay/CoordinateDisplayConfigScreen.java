package coordinatedisplay;

import coordinatedisplay.config.CoordinateConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class CoordinateDisplayConfigScreen extends Screen {

    private final Screen parent;
    private final CoordinateConfig config;

    public CoordinateDisplayConfigScreen(Screen parent) {
        super(Text.literal("Coordinate Display Config"));
        this.parent = parent;
        this.config = CoordinateConfig.getInstance();
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int x = (this.width - buttonWidth) / 2;
        int y = this.height / 4; // start a bit down

        // Toggle button (ON/OFF)
        var toggleLabel = Text.literal("Toggle Display: " + (config.toggle ? "ON" : "OFF"));
        this.addDrawableChild(
            ButtonWidget.builder(toggleLabel, b -> {
                config.toggle = !config.toggle;
                b.setMessage(Text.literal("Toggle Display: " + (config.toggle ? "ON" : "OFF")));
            }).dimensions(x, y, buttonWidth, buttonHeight).build()
        );
        y += 24;

        // Save & Close
        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("Save and Close"), b -> {
                CoordinateConfig.save(config);
                this.client.setScreen(this.parent);
            }).dimensions(x, y, buttonWidth, buttonHeight).build()
        );
    }

    @Override
    public void close() {
        // Optional: persist when user presses Esc
        CoordinateConfig.save(config);
        this.client.setScreen(this.parent);
    }
}

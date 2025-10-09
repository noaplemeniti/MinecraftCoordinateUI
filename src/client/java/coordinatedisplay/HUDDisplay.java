package coordinatedisplay;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

public class HUDDisplay {
    public CoordinateState state;
    public void setState(CoordinateState state) {
        this.state = state;
    }
    public void render(DrawContext context, PlayerEntity player, TextRenderer textRenderer) {
        String coords = String.format("X: %.1f Y: %.1f Z: %.1f", state.x, state.y, state.z);
        String orientation = "Facing: " + state.orientationString(player.getYaw());

        int x = 10;
        int y = 10;
        context.fill(5, 5, 150, 35, 0x80000000);
        context.drawText(textRenderer, coords, x, y, 0xFFFFFFFF, false);
        context.drawText(textRenderer, orientation, x, y + 10, 0xFFFFFFFF, false);
    }
}

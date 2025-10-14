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
        String coordx = String.format("X: %.0f %s", state.x, state.orientationx);
        String coordy  = String.format("Y: %.0f", state.y);
        String coordz = String.format("Z: %.0f %s", state.z, state.orientationz);
        String orientation = "Facing: " + state.orientationString(player.getYaw());

        int x = 10;
        int y = 10;
        context.fill(5, 5, 50, 65, 0x80000000);
        context.drawText(textRenderer, coordx, x, y, 0xFFFFFFFF, false);
        context.drawText(textRenderer, coordy, x, y + 10, 0xFFFFFFFF, false);
        context.drawText(textRenderer, coordz, x, y + 20, 0xFFFFFFFF, false);
        context.drawText(textRenderer, orientation, x, y + 30, 0xFFFFFFFF, false);
    }
}

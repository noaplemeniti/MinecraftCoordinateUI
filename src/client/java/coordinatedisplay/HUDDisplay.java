package coordinatedisplay;

import org.joml.Matrix3x2f;

import java.util.List;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;

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
        context.fill(x-8,y-8, x+50, y+26, 0x80000000); // semi-transparent background
        var matrices = context.getMatrices();
        Matrix3x2f oldMatrix = new Matrix3x2f(matrices); // copy current matrix

        float scale = 0.5f;
        matrices.scale(scale, scale); // scale X and Y

        context.drawText(textRenderer, coordx, x, y, 0xFFFFFFFF, false);
        context.drawText(textRenderer, coordy, x, y + 10, 0xFFFFFFFF, false);
        context.drawText(textRenderer, coordz, x, y + 20, 0xFFFFFFFF, false);
        context.drawText(textRenderer, orientation, x, y + 30, 0xFFFFFFFF, false);
        Text label = Text.literal("Biome: ");
        Text biome  = state.biome;

        int labelW = textRenderer.getWidth(label);
        int maxContentWidth = 100; // pick a width you like

        List<OrderedText> parts = textRenderer.wrapLines(biome, maxContentWidth);
        int lineH = textRenderer.fontHeight;
        context.drawText(textRenderer, label, x, y+40, 0xFFFFFFFF, true);
        if (!parts.isEmpty()) {
            context.drawText(textRenderer, parts.get(0), x + labelW, y+40, 0xFFFFFFFF, true);
        }
        for (int i = 1; i < parts.size(); i++) {
            context.drawText(textRenderer, parts.get(i), x + labelW, (y+40) + i * lineH, 0xFFFFFFFF, true);
        }
        matrices.set(oldMatrix); // restore old matrix
    }
}

package coordinatedisplay;

import coordinatedisplay.config.CoordinateConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class CoordinateDisplay implements ClientModInitializer {

    private static final CoordinateState STATE = new CoordinateState();
    private final HUDDisplay hud = new HUDDisplay(); // one instance
    @Override
    public void onInitializeClient() {
        // runs 20 times/second on the client
        ClientTickEvents.END_CLIENT_TICK.register(this::updateCoordinates);
		HudRenderCallback.EVENT.register(this::renderHud);
        CoordinateConfig.load();
	}

    private void updateCoordinates(MinecraftClient client) {
        if (client == null || client.player == null) return;

        var p = client.player;
        
        STATE.x = p.getX();
        STATE.y = p.getY();
        STATE.z = p.getZ();
        STATE.orientation = STATE.calculateOrientation(p.getYaw());
        STATE.setOrientationStrings(p.getYaw());
    }

	private void renderHud(DrawContext ctx, RenderTickCounter tickCounter) {
    	var mc = MinecraftClient.getInstance();
        var cfg = CoordinateConfig.getInstance();
        if (cfg.toggle != true){ return; }
    	if (mc.player == null) return; // in menus
    	var tr = mc.textRenderer;
		hud.setState(STATE); // update the state
    	hud.render(ctx, mc.player, tr); // delegate the actual drawing
	}

}

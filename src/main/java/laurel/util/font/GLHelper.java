package laurel.util.font;

import net.minecraft.client.renderer.GlStateManager;

public class GLHelper {

    public static void setColor(int color, float alpha) {
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        GlStateManager.color(r, g, b, alpha);
    }

    public static void setColor(int color) {
        setColor(color, (float) (color >> 24 & 255) / 255.0F);
    }
}

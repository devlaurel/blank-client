package laurel.gui;

import laurel.util.shader.RoundedRectHelper;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class GuiRenderer {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public void draw() {

        RoundedRectHelper.drawRoundedRect(40, 40, 100, 100, 18, Color.red);
    }
}

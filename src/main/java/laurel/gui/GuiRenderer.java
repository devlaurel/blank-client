package laurel.gui;

import laurel.util.font.FontUtils;
import laurel.util.shader.RoundedRectHelper;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class GuiRenderer {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public void draw() {

        RoundedRectHelper.drawRoundedRect(40, 40, 100, 20, 6, new Color(28, 28, 28));
        FontUtils.greycliff.drawString("Naga client", 60, 46, -1);
    }
}

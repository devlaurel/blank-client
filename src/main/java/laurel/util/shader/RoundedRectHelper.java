package laurel.util.shader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RoundedRectHelper {

    private static final Minecraft mc = Minecraft.getMinecraft();
    
    private static final String roundedRectFragment = "#version 120\n" +
            "\n" +
            "uniform vec2 location, rectSize;\n" +
            "uniform vec4 color;\n" +
            "uniform float radius;\n" +
            "uniform bool blur;\n" +
            "\n" +
            "float roundSDF(vec2 p, vec2 b, float r) {\n" +
            "\treturn length(max(abs(p) - b, 0.0)) - r;\n" +
            "}\n" +
            "\n" +
            "void main() {\n" +
            "\tvec2 rectHalf = rectSize * .5;\n" +
            "\tfloat smoothedAlpha =  (1.0-smoothstep(0.0, 1.0, roundSDF(rectHalf - (gl_TexCoord[0].st * rectSize), rectHalf - radius - 1., radius))) * color.a;\n" +
            "\t\n" +
            "\tgl_FragColor = vec4(color.rgb, smoothedAlpha);\n" +
            "}";

    private static final Shader roundedRectShader = new Shader(roundedRectFragment);

    private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius, Shader roundedRectShader) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        roundedRectShader.setUniformf("location", x * scaledResolution.getScaleFactor(), (mc.displayHeight - (height * scaledResolution.getScaleFactor())) - (y * scaledResolution.getScaleFactor()));
        roundedRectShader.setUniformf("rectSize", width * scaledResolution.getScaleFactor(), height * scaledResolution.getScaleFactor());
        roundedRectShader.setUniformf("radius", radius * scaledResolution.getScaleFactor());
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float radius, Color color) {
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        roundedRectShader.init();

        setupRoundedRectUniforms(x, y, width, height, radius, roundedRectShader);
        roundedRectShader.setUniformf("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

        Shader.drawQuads(x - 1, y - 1, width + 2, height + 2);
        roundedRectShader.unload();
        GlStateManager.disableBlend();
    }
}

package laurel.util.font;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class FontUtils {
	
    public static volatile int completed;

	private static int scale;
	private static int prevScale;
	
    public static MinecraftFontRenderer
            greycliff
            ;
    
    public static Font
            greycliff24_
            ;
	
    public static void init() {
		
        Map<String, Font> locationMap = new HashMap<>();

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        
        scale = sr.getScaleFactor();
        
        if(scale != prevScale) {
        	prevScale = scale;

            FontUtils.greycliff24_ = FontUtils.getFont(locationMap, "Fontspring-DEMO-greycliffcf-bold.ttf", 24);
            FontUtils.greycliff = new MinecraftFontRenderer(FontUtils.greycliff24_);
        }
    }
    
    public static Font getFont(Map<String, Font> locationMap, String location, float size) {
        Font font;
        
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        
        size = size * ((float) sr.getScaleFactor() / 2);
        
        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
                InputStream is = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation("blank/fonts/" + location)).getInputStream();
                locationMap.put(location, font = Font.createFont(0, is));
                font = font.deriveFont(Font.PLAIN, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("default", Font.PLAIN, +10);
        }
        return font;
    }

    public static boolean hasLoaded() {
        return completed >= 3;
    }
}

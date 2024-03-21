package laurel.gui.clickgui.dropdown;

import laurel.Blank;
import laurel.module.Module;
import laurel.module.ModuleCategory;
import laurel.util.shader.RoundedRectHelper;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public class ClickGui extends GuiScreen {

    public static final ClickGui INSTANCE = new ClickGui();

    private ModuleCategory selectedModuleCategory = ModuleCategory.COMBAT;
    private Module selectedModule;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RoundedRectHelper.drawRoundedRect(40, 40, 400, 12, 0, new Color(14, 14, 14));
        mc.fontRendererObj.drawStringWithShadow("blank" + "/clickgui/ClickGui.java", 42, 42, Color.lightGray.getRGB());

        RoundedRectHelper.drawRoundedRect(392, 40, 20, 8, 2,
                isMouseInside(mouseX, mouseY, 392, 40, 20, 8) ? new Color(68, 68, 68) : new Color(48, 48, 48));
        mc.fontRendererObj.drawStringWithShadow("_", 400, 38, Color.lightGray.getRGB());

        RoundedRectHelper.drawRoundedRect(416, 40, 20, 8, 2,
                isMouseInside(mouseX, mouseY, 416, 40, 20, 8) ? new Color(120, 28, 28).brighter() : new Color(120, 28, 28));
        mc.fontRendererObj.drawStringWithShadow("x", 424, 39, Color.lightGray.getRGB());

        RoundedRectHelper.drawRoundedRect(40, 52, 400, 268, 0, new Color(28, 28, 28));

        RoundedRectHelper.drawRoundedRect(120, 52, 1, 268, 0, new Color(14, 14, 14));
        RoundedRectHelper.drawRoundedRect(120, 68, 320, 1, 0, new Color(14, 14, 14));

        int index = 0;
        int moduleCategoryStringWidth = 0;
        for (ModuleCategory moduleCategory : ModuleCategory.values()) {
            RoundedRectHelper.drawRoundedRect(124 + index * 6 + moduleCategoryStringWidth, 54, mc.fontRendererObj.getStringWidth(moduleCategory.name) + 4, 12, 4,
                    isMouseInside(mouseX, mouseY, 123 + index * 6 + moduleCategoryStringWidth, 54, mc.fontRendererObj.getStringWidth(moduleCategory.name) + 6, 12) ? new Color(48, 48, 48) : new Color(14, 14, 14));
            mc.fontRendererObj.drawStringWithShadow(moduleCategory.name, 126 + index * 6 + moduleCategoryStringWidth, 56, isModuleCategorySelected(moduleCategory) ? new Color(0, 120, 180).getRGB() : Color.gray.getRGB());
            index++;
            moduleCategoryStringWidth += mc.fontRendererObj.getStringWidth(moduleCategory.name);
        }

        int index1 = 0;
        for (Module module : Blank.getModuleManager().getModulesByCategory(selectedModuleCategory)) {
            RoundedRectHelper.drawRoundedRect(42, 54 + index1 * 14, mc.fontRendererObj.getStringWidth(module.getName()) + 4, 12, 4,
                    isMouseInside(mouseX, mouseY, 42, 53 + index1 * 14, mc.fontRendererObj.getStringWidth(module.getName()) + 4, 14) ? new Color(48, 48, 48) : new Color(14, 14, 14));
            mc.fontRendererObj.drawStringWithShadow(module.getName(), 44, 56 + index1 * 14, isModuleSelected(module) ? new Color(0, 120, 180).getRGB() : Color.gray.getRGB());
            index1++;
        }

        if (selectedModule == null) return;

        mc.fontRendererObj.drawStringWithShadow(selectedModule.getName() + " [" + Keyboard.getKeyName(selectedModule.getKey()) + "]" + " - " + "TODO // Module description", 124, 72, Color.darkGray.getRGB());
        RoundedRectHelper.drawRoundedRect(124, 84, mc.fontRendererObj.getStringWidth("Toggled: " + selectedModule.isEnabled()) + 4, 12, 4, new Color(20, 20, 20));
        mc.fontRendererObj.drawStringWithShadow("Toggled: ", 126, 86, Color.gray.getRGB());
        mc.fontRendererObj.drawStringWithShadow(String.valueOf(selectedModule.isEnabled()), mc.fontRendererObj.getStringWidth("Toggled: ") + 126, 86, selectedModule.isEnabled() ? Color.green.getRGB() : Color.red.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            int index = 0;
            int moduleCategoryStringWidth = 0;
            for (ModuleCategory moduleCategory : ModuleCategory.values()) {
                if (isMouseInside(mouseX, mouseY, 123 + index * 6 + moduleCategoryStringWidth, 54, mc.fontRendererObj.getStringWidth(moduleCategory.name) + 6, 12)) {
                    selectedModuleCategory = moduleCategory;
                }
                index++;
                moduleCategoryStringWidth += mc.fontRendererObj.getStringWidth(moduleCategory.name);
            }

            int index1 = 0;
            for (Module module : Blank.getModuleManager().getModulesByCategory(selectedModuleCategory)) {
                RoundedRectHelper.drawRoundedRect(42, 53 + index1 * 14, mc.fontRendererObj.getStringWidth(module.getName()) + 4, 14, 10, new Color(14, 14, 14));
                if (isMouseInside(mouseX, mouseY, 42, 54 + index1 * 14, mc.fontRendererObj.getStringWidth(module.getName()) + 4, 12)) {
                    selectedModule = module;
                }
                index1++;
            }

            if (isMouseInside(mouseX, mouseY, 392, 40, 20, 8) || isMouseInside(mouseX, mouseY, 416, 40, 20, 8)) {
                mc.thePlayer.closeScreen();
            }

            if (selectedModule == null) return;
            if (isMouseInside(mouseX, mouseY, 124, 84, mc.fontRendererObj.getStringWidth("Toggled: " + selectedModule.isEnabled()) + 4, 12)) {
                selectedModule.toggle();
            }
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    private boolean isModuleCategorySelected(ModuleCategory moduleCategory) {
        return moduleCategory == selectedModuleCategory;
    }

    private boolean isModuleSelected(Module module) {
        return module == selectedModule;
    }

    private boolean isMouseInside(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}

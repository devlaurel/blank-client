package laurel;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventKey;
import laurel.gui.GuiRenderer;
import laurel.module.Module;
import laurel.module.ModuleManager;
import laurel.util.font.FontUtils;

public class Blank {

    public static final Blank INSTANCE = new Blank();
    private static ModuleManager moduleManager;
    private static GuiRenderer guiRenderer;

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static GuiRenderer getGuiRenderer() {
        return guiRenderer;
    }

    // Called when minecraft has finished loading
    public final void init() {
        FontUtils.init();
        moduleManager = new ModuleManager();
        guiRenderer = new GuiRenderer();
    }

    public void onEvent(AbstractEvent event) {
        getModuleManager().getEnabledModules().forEach(module -> module.onEvent(event));

        // Key handling
        if (event instanceof EventKey) {
            int key = ((EventKey) event).key;

            getModuleManager().getModules().stream()
                    .filter(module -> module.getKey() == key)
                    .forEach(Module::toggle);
        }
    }
}

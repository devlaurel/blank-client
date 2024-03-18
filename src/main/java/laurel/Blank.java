package laurel;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventKey;
import laurel.module.Module;
import laurel.module.ModuleManager;

public class Blank {

    public static final Blank INSTANCE = new Blank();
    public static ModuleManager moduleManager;

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    // Called when minecraft has finished loading
    public final void init() {
        moduleManager = new ModuleManager();
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

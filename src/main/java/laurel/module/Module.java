package laurel.module;

import laurel.event.AbstractEvent;
import net.minecraft.client.Minecraft;

public abstract class Module {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    private int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // Module info
    private final String name, description;
    private final ModuleCategory moduleCategory;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ModuleCategory getModuleCategory() {
        return moduleCategory;
    }

    public Module(String name, String description, ModuleCategory moduleCategory) {
        this.name = name;
        this.description = description;
        this.moduleCategory = moduleCategory;
    }

    public void toggle() {
        enabled = !enabled;

        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    // To be implemented by subclasses
    // A super method is not necessary
    public void onEnable() {
    }
    public void onDisable() {
    }

    // Calls if the param-event is being called.
    public void onEvent(AbstractEvent event) {
    }
}

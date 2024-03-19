package laurel.module.impl;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventUpdate;
import laurel.module.Module;
import laurel.module.ModuleCategory;

public class Flight extends Module {
    public Flight() {
        super("Flight", "Allows you to fly", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEvent(AbstractEvent event) {
        if (event instanceof EventUpdate) {
            mc.thePlayer.capabilities.isFlying = true;
        }
        // super method is useless
        // super.onEvent(event);
    }

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;

        // super.onDisable();
    }
}

package laurel.module.impl;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventUpdate;
import laurel.module.Module;
import laurel.module.ModuleCategory;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Makes you sprint", ModuleCategory.MOVEMENT);
    }

    @Override
    public void onEvent(AbstractEvent event) {
        if (event instanceof EventUpdate) {
            // NOT legit
            // mc.thePlayer.setSprinting(true);

            mc.gameSettings.keyBindSprint.pressed = true;
        }
    }
}

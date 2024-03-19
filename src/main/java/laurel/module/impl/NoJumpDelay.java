package laurel.module.impl;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventUpdate;
import laurel.module.Module;
import laurel.module.ModuleCategory;

public class NoJumpDelay extends Module {
    public NoJumpDelay() {
        super("NoJumpDelay", "Removes your jump delay", ModuleCategory.PLAYER);
    }

    @Override
    public void onEvent(AbstractEvent event) {
        if (event instanceof EventUpdate) {
            mc.thePlayer.jumpTicks = 0;
        }
    }
}

package laurel.module.impl;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventPackedReceive;
import laurel.module.Module;
import laurel.module.ModuleCategory;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.lwjgl.input.Keyboard;

public class NoVelocity extends Module {
    public NoVelocity() {
        super("NoVelocity", "Removes/Reduces your velocity", ModuleCategory.PLAYER);

        setKey(Keyboard.KEY_4);
    }

    @Override
    public void onEvent(AbstractEvent event) {
        // you basically cancel the velocity packet :)

        // bypasses on blocksmc and vulcan!
        if (event instanceof EventPackedReceive) {
            if (((EventPackedReceive) event).packet instanceof S12PacketEntityVelocity) {
                event.cancel();
            }
        }
        super.onEvent(event);
    }
}

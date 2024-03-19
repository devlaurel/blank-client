package laurel.module.impl;

import laurel.event.AbstractEvent;
import laurel.event.impl.EventPacketReceive;
import laurel.module.Module;
import laurel.module.ModuleCategory;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.lwjgl.input.Keyboard;

public class NoVelocity extends Module {
    public NoVelocity() {
        super("NoVelocity", "Removes/Reduces your velocity", ModuleCategory.PLAYER);
    }

    @Override
    public void onEvent(AbstractEvent event) {
        // you basically cancel the velocity packet :)

        // bypasses on blocksmc and vulcan!
        if (event instanceof EventPacketReceive) {
            if (((EventPacketReceive) event).packet instanceof S12PacketEntityVelocity) {
                event.cancel();
            }
        }
    }
}

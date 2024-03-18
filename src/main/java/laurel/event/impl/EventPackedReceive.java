package laurel.event.impl;

import laurel.event.AbstractEvent;
import net.minecraft.network.Packet;

public class EventPackedReceive extends AbstractEvent {

    public final Packet<?> packet;

    public EventPackedReceive(Packet<?> packet) {
        this.packet = packet;
    }
}

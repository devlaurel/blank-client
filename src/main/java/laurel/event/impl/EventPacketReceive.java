package laurel.event.impl;

import laurel.event.AbstractEvent;
import net.minecraft.network.Packet;

public class EventPacketReceive extends AbstractEvent {

    public final Packet<?> packet;

    public EventPacketReceive(Packet<?> packet) {
        this.packet = packet;
    }
}

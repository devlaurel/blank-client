package laurel.event.impl;

import laurel.event.AbstractEvent;

public class EventKey extends AbstractEvent {

    // you don't need a getter, just make this public
    public final int key;

    // use a constructor to force a key
    public EventKey(int key) {
        this.key = key;
    }
}

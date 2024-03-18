package laurel.event;

public abstract class AbstractEvent {

    private boolean canceled;

    public boolean isCanceled() {
        return canceled;
    }

    // event.cancel() makes more sense because you cannot "un-cancel" an event.

    public void cancel() {
        this.canceled = true;
    }

    /*
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    */
}

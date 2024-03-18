package laurel.module;

public enum ModuleCategory {
    COMBAT("Combat"), MOVEMENT("Movement"), RENDER("Render"), PLAYER("Player");

    public final String name;

    ModuleCategory(String name) {
        this.name = name;
    }
}

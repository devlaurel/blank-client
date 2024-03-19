package laurel.module;

import laurel.module.impl.Flight;
import laurel.module.impl.NoJumpDelay;
import laurel.module.impl.NoVelocity;
import laurel.module.impl.Sprint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {

    private final List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> getEnabledModules() {
        return modules.stream()
                .filter(Module::isEnabled)
                .collect(Collectors.toList());
    }

    public List<Module> getModulesByCategory(ModuleCategory moduleCategory) {
        return modules.stream()
                .filter(module -> module.getModuleCategory() == moduleCategory)
                .collect(Collectors.toList());
    }

    public ModuleManager() {
        modules = new ArrayList<>();

        registerModule(new Flight());
        registerModule(new NoVelocity());
        registerModule(new NoJumpDelay());
        registerModule(new Sprint());
    }

    // not really necessary but makes the code clearer  ... (dedicated to superblaubeere27)
    private void registerModule(Module module) {
        modules.add(module);
    }
}

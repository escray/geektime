import java.util.List;
import java.util.Arrays;

public class ComponentTree {
    public static void main(String[] args) {
        ComponentModule subModule31 = new ComponentModule(
                new DefaultModule("31"),
                new DefaultModule("311"),
                new DefaultModule("312"),
                new DefaultModule("313")
        );

        ComponentModule subModule3 = new ComponentModule(
                new DefaultModule("3"),
                subModule31,
                new DefaultModule("32"),
                new DefaultModule("33")
        );

        ComponentModule module = new ComponentModule(
                new DefaultModule("0"),
                new DefaultModule("1"),
                new DefaultModule("2"),
                subModule3
        );

        module.print();
    }
}

interface Module {
    void print();
}

class DefaultModule implements Module {
    private final String value;

    public DefaultModule(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println(value);
    }
}

class ComponentModule implements Module {
    private final Module currentModule;
    private final List<Module> modules;

    public ComponentModule(Module currentModule, Module... modules) {
        this.currentModule = currentModule;
        this.modules = Arrays.asList(modules);
    }

    @Override
    public void print() {
        this.currentModule.print();
        this.modules.forEach(Module::print);
    }
}

interface AnyThing {
    void exe();
}

public class DesignPattern {
    public static void main(String[] args) {
        AnyThing t = new Moon(new Dream(new You(null)));
        t.exe();
        System.out.println();
        t = new Dream(new Moon(new You(null)));
        t.exe();
    }
}

class Moon implements AnyThing {
    private AnyThing a;
    public Moon(AnyThing a) {
        this.a = a;
    }
    public void exe() {
        System.out.print("明月装饰了");
        a.exe();
    }
}

class Dream implements AnyThing {
    private AnyThing a;
    public Dream(AnyThing a) {
        this.a=a;
    }
    public void exe() {
        System.out.print("梦装饰了");
        a.exe();
    }
}

class You implements AnyThing {
    private AnyThing a;
    public You(AnyThing a) {
        this.a = a;
    }
    public void exe() {
        System.out.print("你");
    }
}

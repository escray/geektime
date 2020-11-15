public class Singleton {
    private Singleton() {

    }
    private static Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance;
    }
}

class Singleton2 {
    private Singleton2() {

    }
    private static Singleton2 instance = null;
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

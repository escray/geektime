public class LiskovSubstitution {
    public abstract class Parent {
        public abstract void f() throws AException;
    }

    public class Child extends Parent {
        @Override
        public void f() throws BException {
            System.out.println("Child");
        }
    }

    class AException extends Exception {
    }

    class BException extends AException {
    }
}

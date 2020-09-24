public interface ButtonServer {
     void turnOn();
     void turnOff();
}

class Lamp implements ButtonServer {
    public void turnOn() {
        System.out.println("Lamp On");
    }

    public void turnOff() {
        System.out.println("Lamp Off");
    }
}

class Button {
    ButtonServer bs;
    public void Button(ButtonServer buttonServer) {
        bs = buttonServer;
    }
    public void poll() {
        bs.turnOff();
        bs.turnOn();
    }
}

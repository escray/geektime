import java.util.List;
import java.util.LinkedList;

public class Phone {
    private Dialer dialer;
    private Button[] digitButtons;
    private Button sendButton;
    public final static int STAR = "42";

    public Phone() {
        dialer = new Dialer();
        digitButtons = new Button[10];
        for (int i = 0; i < digitButtons.length; i++) {
            digitButtons[i] = new Button();
            final int digit = i;
            digitButtons[i].addListener(new ButtonListener(){
                public void buttonPressed() {
                    dialer.enterDigit(digit);
                }
            });
        }
        sendButton = new Button();
        sendButton.addListener(new ButtonListener() {
            public void buttonPressed() {
                dialer.dial();
            }
        });
        starButton = new Button();
        startButton.addListener(new ButtonListener() {
           public void buttonPressed() {
               dialer.enterDigit(STAR);
           }
        });
    }

    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.digitButtons[9].press();
        phone.digitButtons[1].press();
        phone.digitButtons[1].press();
        phone.sendButton.press();
    }
}

class Button {
    private List<ButtonListener> listeners;

    public Button() {
        this.listeners = new LinkedList<ButtonListener>();
    }

    public void addListener(ButtonListener listener) {
        assert listener != null;
        listeners.add(listener);
    }

//    abstract void onPress();

    public void press() {
//        onPress();
        for (ButtonListener listener : listeners) {
            listener.buttonPressed();
        }
    }
}

class ButtonListener {
    void buttonPressed() {

    }
}

class Dialer {
    public void enterDigit(int digit) {
        System.out.println("enter digit: " + digit);
    }

    public void dial() {
        System.out.println("dialing...");
    }
}

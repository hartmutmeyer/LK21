public class ZeichenException extends Exception {

    public ZeichenException() {
        super("Sie habe ein verbotenes Zeichen eingegeben. Es sind nur Zahlen und ein Punkt erlaubt.");
    }
}

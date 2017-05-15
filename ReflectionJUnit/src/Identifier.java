public final class Identifier {

    public transient String str;

    protected Identifier() {
        str = "str";
    }

    public synchronized String getString() {
        return str;
    }
}
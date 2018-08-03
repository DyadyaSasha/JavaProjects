package app.models;

public class Data {

    private double x;
    private double y;
    private boolean autoIncreasedBounds;
    private boolean isPanning;

    public Data(double x, double y, boolean autoIncreasedBounds, boolean isPanning){
        this.x = x;
        this.y = y;
        this.autoIncreasedBounds = autoIncreasedBounds;
        this.isPanning = isPanning;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isAutoIncreasedBounds() {
        return autoIncreasedBounds;
    }

    public void setAutoIncreasedBounds(boolean autoIncreasedBounds) {
        this.autoIncreasedBounds = autoIncreasedBounds;
    }

    public boolean isPanning() {
        return isPanning;
    }
}

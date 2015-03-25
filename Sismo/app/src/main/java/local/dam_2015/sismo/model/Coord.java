package local.dam_2015.sismo.model;

/**
 * Created by cursomovil on 25/03/15.
 */
public class Coord {
    private double lgtd;
    private double lttd;
    private double depth;

    public Coord(double lgtd, double lttd, double depth) {
        this.lgtd = lgtd;
        this.lttd = lttd;
        this.depth = depth;
    }

    public double getLgtd() {
        return lgtd;
    }

    public void setLgtd(double lgtd) {
        this.lgtd = lgtd;
    }

    public double getLttd() {
        return lttd;
    }

    public void setLttd(double lttd) {
        this.lttd = lttd;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}

package local.dam_2015.sismo.model;

import java.util.Date;

/**
 * Created by cursomovil on 25/03/15.
 */
public class EarthQ {
    private Coord coordinate;
    private Date date;
    private double magnitude;
    private String place;
    private String _id;
    private String URL;

    public EarthQ(Coord coordinate, Date date, double magnitude, String place, String _id) {
        this.coordinate = coordinate;
        this.date = date;
        this.magnitude = magnitude;
        this.place = place;
        this._id = _id;
    }

    public EarthQ() {

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return this.getPlace();
    }

    public Coord getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coord coordinate) {
        this.coordinate = coordinate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTime(long time) {

        this.date=new Date(time);
    }

    public boolean isValid() {
        boolean reply = true;

        if(this.coordinate.getLgtd()>180 || this.coordinate.getLgtd()<-180){
            reply=false;
        }
        if(this.coordinate.getLttd()>85 || this.coordinate.getLttd()<-85.05115){
            reply=false;
        }
        if(this.magnitude<0){
            reply=false;
        }

        return reply;
    }
}

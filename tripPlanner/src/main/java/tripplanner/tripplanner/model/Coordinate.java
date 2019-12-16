package tripplanner.tripplanner.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Coordinate implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

package bioroid.model.location;

public class Location {

    private int x;

    private int y;

    // required for JAXB
    public Location() {

    }

    public Location(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public void incrementX() {
	x++;
    }

    public void decrementX() {
	x--;
    }

    public void incrementY() {
	y++;
    }

    public void derementY() {
	y--;
    }

    public void updateWith(Location location) {
	x = location.x;
	y = location.y;
    }

    public Location copy() {
	Location newLocation = new Location();
	newLocation.setX(x);
	newLocation.setY(y);
	return newLocation;
    }

    public boolean isNextTo(Location location) {
	return (Math.abs(this.x - location.x) <= 1 && Math.abs(this.y - location.y) <= 1);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Location other = (Location) obj;
	if (x != other.x)
	    return false;
	if (y != other.y)
	    return false;
	return true;
    }

}

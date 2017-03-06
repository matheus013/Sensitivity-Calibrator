package environment.temperature;
import java.awt.Point;
import java.util.Random;

public class SourceHeat {
	private double modifier;
	private Point location;
	private int radius = 10;

	public SourceHeat(double modifier, Point location) {
		// TODO Auto-generated constructor stub
		this.setModifier(modifier);
		this.setLocation(location);
	}

	public double getModifier() {
		return modifier;
	}

	public double getModifier(double dist) {
		if (dist > radius)
			return 0;
		return modifier * (dist / radius);
	}

	public void setModifier(double modifier) {
		this.modifier = modifier;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public static Point nextPoint(int dimension) {
		Random generator = new Random();
		return new Point(generator.nextInt(dimension), generator.nextInt(dimension));
	}

	public static SourceHeat nextSourceHeat(int dimension) {
		Random generator = new Random();

		SourceHeat sh = new SourceHeat((double) generator.nextInt(50) - 30, nextPoint(dimension));
		return sh;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}

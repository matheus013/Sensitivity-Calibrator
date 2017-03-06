package environment.temperature;

import java.awt.Point;
import java.util.ArrayList;

public class Temperature {
	private int dimension;
	private double temp = 25;
	private ArrayList<SourceHeat> sourceHeats;

	public Temperature(int dimension) {
		// TODO Auto-generated constructor stub
		this.dimension = dimension;
		sourceHeats = new ArrayList<>();
		initSource();
	}

	public Temperature() {
		sourceHeats = new ArrayList<>();
	}

	private void initSource() {

		for (int i = 0; i < dimension * 0.25; i++) {
			sourceHeats.add(SourceHeat.nextSourceHeat(dimension));
		}
	}

	public void initSource(int dimension) {

		for (int i = 0; i < dimension * 0.25; i++) {
			sourceHeats.add(SourceHeat.nextSourceHeat(dimension));
		}
	}

	public double getTemperature(Point p) {
		double temp = this.temp;
		for (SourceHeat sourceHeat : sourceHeats) {
			temp += sourceHeat.getModifier(sourceHeat.getLocation().distance(p));
		}
		return temp;
	}

}

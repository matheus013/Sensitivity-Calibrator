package environment.temperature;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import jsensor.nodes.Node;
import projects.Flooding.Sensors.ModelIdealNode;

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

	public double getTemperature(Node node) {
		Point p = new Point(node.getPosition().getPosX(), node.getPosition().getPosX());
		double temp = this.temp;
		for (SourceHeat sourceHeat : sourceHeats) {
			temp += sourceHeat.getModifier(sourceHeat.getLocation().distance(p));
		}
		Random generator = new Random();
		double sensitivity = 0.5;
		
		if (node instanceof ModelIdealNode) {
			ModelIdealNode ideal = (ModelIdealNode) node;
			sensitivity = ideal.sensitivity;
		}
		
		boolean signal = generator.nextBoolean();
		double variation = generator.nextDouble() * sensitivity;
		
		if (signal)
			temp *= 1 + variation;
		else
			temp *= 1 - variation;
		return temp;
	}

}

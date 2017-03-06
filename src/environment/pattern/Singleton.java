package environment.pattern;

import environment.temperature.Temperature;
import jsensor.runtime.Jsensor;

public class Singleton {
	static Temperature t;

	public synchronized static Temperature getInstance() {
		if (t == null)
			t = new Temperature(Jsensor.getDimX());
		return t;
	}
}

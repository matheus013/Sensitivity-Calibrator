package environment.pattern;

import environment.temperature.Temperature;

public class Singleton {
	static Temperature t;

	public static Temperature getInstance() {
		if (t == null)
			t = new Temperature();
		return t;
	}
}

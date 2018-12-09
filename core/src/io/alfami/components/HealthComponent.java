package io.alfami.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {

	public int healthPoints;
	public int maxHealthPoints;

	public HealthComponent(int healthPoints, int maxHealthPoints) {
		super();
		this.healthPoints = healthPoints;
		this.maxHealthPoints = maxHealthPoints;
	}

}

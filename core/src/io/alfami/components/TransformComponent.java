package io.alfami.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
	public Vector2 pos = new Vector2();
	public Vector2 scale = new Vector2(1f, 1f);

	public int zIndex;

	public float rotation = 0;

	public TransformComponent(float x, float y) {
		this(x, y, 1);
	}

	public TransformComponent(float x, float y, int zIndex) {
		this(x, y, zIndex, 1f, 1f, 0);
	}

	public TransformComponent(float x, float y, int zIndex, float sclX, float sclY) {
		this(x, y, zIndex, sclX, sclY, 0);
	}

	public TransformComponent(float x, float y, int zIndex, float sclX, float sclY, float rotation) {
		pos.set(x, y);
		this.zIndex = zIndex;
		scale.set(sclX, sclY);
		this.rotation = rotation;
	}

	public TransformComponent(Vector2 pos, Vector2 scl, float rotation) {
		this.pos.set(pos);
		this.scale.set(scl);
		this.rotation = rotation;
	}
}

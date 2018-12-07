package io.alfami.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

import io.alfami.statemachine.PlayerAgent;
import io.alfami.statemachine.PlayerState;

public class PlayerComponent implements Component {
	public static final int IDLE = 0;
	public static final int IDLE_UP = 0;
	public static final int IDLE_DOWN = 1;
	public static final int IDLE_LEFT = 2;
	public static final int IDLE_RIGHT = 3;

	public static final int MOVE_UP = 4;
	public static final int MOVE_DOWN = 5;
	public static final int MOVE_LEFT = 6;
	public static final int MOVE_RIGHT = 7;
	public static final int DIE = 8;

	private final Body body;
    public PlayerAgent playerAgent;

	public int currentState;
	public int hp;

	public PlayerComponent(Body body) {
		this.body = body;
        playerAgent = new PlayerAgent(this);
        playerAgent.stateMachine.setInitialState(PlayerState.IDLE_DOWN);
        currentState = IDLE_RIGHT;
		hp = 500;
	}

	public Body getBody() {
		return body;
	}
}

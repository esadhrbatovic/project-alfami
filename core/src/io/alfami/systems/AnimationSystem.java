package io.alfami.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import io.alfami.components.AnimationComponent;
import io.alfami.components.StateComponent;
import io.alfami.components.TextureComponent;

public class AnimationSystem extends IteratingSystem {

	private final ComponentMapper<TextureComponent> textureM = ComponentMapper.getFor(TextureComponent.class);
	private final ComponentMapper<AnimationComponent> animationM = ComponentMapper.getFor(AnimationComponent.class);
	private final ComponentMapper<StateComponent> stateM = ComponentMapper.getFor(StateComponent.class);

	public AnimationSystem() {
		super(Family.all(TextureComponent.class, AnimationComponent.class, StateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TextureComponent tex = textureM.get(entity);
		AnimationComponent anim = animationM.get(entity);
		StateComponent state = stateM.get(entity);

		tex.region = anim.animations.get(state.getState()).getKeyFrame(state.getStateTime());
	}

}

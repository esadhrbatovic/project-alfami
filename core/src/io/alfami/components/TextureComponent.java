package io.alfami.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class TextureComponent implements Component {
	public TextureRegion region;
	public TextureRegionDrawable textureRegionDrawable;
	
	public TextureComponent(TextureRegion textureRegion) {
		region = new TextureRegion(textureRegion);
		textureRegionDrawable = new TextureRegionDrawable(region);
	}
}

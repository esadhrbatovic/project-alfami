package legacy.io.alfami.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ActionBarSlot {
	Stage stage;
	ImageButton actionbarButton;
	TextureRegionDrawable actionBarButtonDown, actionBarButtonUp;

	public void create(Stage stage) {
		this.stage = stage;
		actionBarButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_down.png")));
		actionBarButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_up.png")));
		actionbarButton = new ImageButton(actionBarButtonUp);
		actionbarButton.getStyle().imageUp = actionBarButtonUp;
		actionbarButton.getStyle().imageDown = actionBarButtonDown;
		actionbarButton.getStyle().imageChecked = actionBarButtonDown;
		actionbarButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				actionbarButton.setChecked(false);
				super.touchUp(event, x, y, pointer, button);
			}
		});

	}
}

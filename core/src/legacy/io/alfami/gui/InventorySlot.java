package legacy.io.alfami.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InventorySlot {
	Stage stage;
	ImageButton inventorySlotButton;
	TextureRegionDrawable inventorySlotButtonEmpty;

	public void create(Stage stage) {
		this.stage = stage;

		inventorySlotButtonEmpty = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/inventory/inventory_slot.png")));
		inventorySlotButton = new ImageButton(inventorySlotButtonEmpty);
		inventorySlotButton.getStyle().imageUp = inventorySlotButtonEmpty;
		inventorySlotButton.getStyle().imageDown = inventorySlotButtonEmpty;
		inventorySlotButton.pad(2f);

	}
}

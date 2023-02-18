package bioroid.engine.entity.ui.main;

import java.util.List;

import org.mini2Dx.core.graphics.Graphics;

import bioroid.GameHolder;
import bioroid.engine.entity.Entity;
import bioroid.font.Fonts;
import bioroid.model.character.GameCharacter;

public class CharacterPanel extends Entity {

    public CharacterPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 1);
    }

    @Override
    protected void renderEntitySpecific(Graphics g) {
	int x2 = getX() + 20;
	int y2 = getY() + 100;
	List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
	for (GameCharacter pc : pcs) {
	    Fonts.GREEN_FONT.draw(g, pc.getName(), x2, y2);
	    y2 = y2 + 20;
	    if (pc.getCurrentHP() < (pc.getMaxHP() / 2)) {
		Fonts.RED_FONT.draw(g, "HP: " + pc.getCurrentHP() + "/" + pc.getMaxHP(), x2, y2);
	    } else {
		Fonts.GREEN_FONT.draw(g, "HP: " + pc.getCurrentHP() + "/" + pc.getMaxHP(), x2, y2);
	    }
	    y2 = y2 + 80;
	}

    }

    @Override
    public void update(float delta) {
	// not needed yet (possibly show damage received or flash with
	// conditions like blind?)
    }

}

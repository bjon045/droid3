package bioroid.control;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

import bioroid.GameHolder;
import bioroid.control.action.Action;
import bioroid.control.action.ActionType;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.ui.BackgroundPanel;
import bioroid.engine.entity.ui.main.CharacterPanel;
import bioroid.model.location.Location;

public class InputController {

    public void checkForInput() {
	// get mouse current location
	int mx = Gdx.input.getX();
	int my = Gdx.input.getY();
	boolean leftMouseClick = Gdx.input.isButtonPressed(Buttons.LEFT); // TODO: fix this to JustPressed!
	// get entity mouse is over
	List<Entity> entities = EntityManager.getEntities(GameHolder.gameMode);
	Entity e = processEntities(mx, my, entities, true);
	// click on entity
	if ((e != null) && leftMouseClick) {
	    e.mousePressed(mx, my);
	}

	if (GameHolder.blockingEntity != null) {
	    return;
	}

	// capture keyboard events
	if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
	    GameHolder.currentAction = new Action(ActionType.MOVE_WEST);
	} else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
	    GameHolder.currentAction = new Action(ActionType.MOVE_EAST);
	} else if (Gdx.input.isKeyJustPressed(Keys.UP)) {
	    GameHolder.currentAction = new Action(ActionType.MOVE_NORTH);
	} else if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
	    GameHolder.currentAction = new Action(ActionType.MOVE_SOUTH);
	} else if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    GameHolder.currentAction = new Action(ActionType.PASS);
	} else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    // GameLoader.saveGame("test");
	    // TODO: quit container.exit();
	} else if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
	    GameHolder.combatMode = true;
	} else if (Gdx.input.isKeyJustPressed(Keys.C)) {
	    CharacterPanel charPanel = EntityManager.getCharacterPanel();
	    charPanel.setActive(!charPanel.isActive());
	    BackgroundPanel backgroundPanel = EntityManager.getBackgroundPanel();
	    Location mainPanelLocation = backgroundPanel.getMainStart();
	    EntityManager.getMapPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    backgroundPanel.getMapWidth(), backgroundPanel.getMainHeight());
	}
    }

    private Entity processEntities(int mx, int my, List<? extends Entity> entities, boolean parentEntities) {
	Entity currentEntity = null;
	for (Entity e : entities) {
	    if (parentEntities && GameHolder.blockingEntity != null && GameHolder.blockingEntity != e) {
		continue;
	    }
	    if (e.inside(mx, my)) {

		if (!e.isMouseInside()) {
		    e.mouseEntered();
		}
		if (e.getChildren() != null) {

		    Entity e2 = processEntities(mx, my, e.getChildren(), false);
		    if ((e2 != null) && (currentEntity == null)) {
			currentEntity = e2;
		    }
		}
		if (currentEntity == null) {
		    currentEntity = e;
		}

	    } else {

		updateMouseNotInside(e);
	    }
	    // mouse not over and was never inside

	}
	return currentEntity;
    }

    private void updateMouseNotInside(Entity e) {

	if (e.isMouseInside()) {
	    // mouse is no longer inside
	    e.mouseExited();
	    if (e.getChildren() != null) {
		for (Entity ee : e.getChildren()) {
		    updateMouseNotInside(ee);
		}
	    }
	}
    }

}

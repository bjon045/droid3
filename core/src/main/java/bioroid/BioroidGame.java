package bioroid;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

import bioroid.control.CoreGameController;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.model.ModelResources;

public class BioroidGame extends BasicGame {

    public static final String GAME_IDENTIFIER = "biodroid";

    private CoreGameController coreGameController;

    @Override
    public void initialise() {
	System.out.println("Model count:" + ModelResources.modelObjects.size());

	coreGameController = new CoreGameController();
	GameHolder.gameMode = GameMode.START_MENU;
    }

    public void init(GameContainer container) {

    }

    @Override
    public void update(float delta) {
	// logic branch
	coreGameController.update(delta);

	// update ui entities
	for (Entity entity : EntityManager.getEntities(GameHolder.gameMode)) {
	    entity.update(delta);
	}

    }

    @Override
    public void render(Graphics g) {
	for (Entity entity : EntityManager.getEntities(GameHolder.gameMode)) {
	    entity.render(g);
	}
    }

    @Override
    public void interpolate(float alpha) {
	// TODO Auto-generated method stub

    }
}

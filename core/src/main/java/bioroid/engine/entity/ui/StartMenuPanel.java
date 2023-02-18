package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.EntityManager;
import bioroid.engine.entity.StaticImageEntity;
import bioroid.engine.entity.StringEntity;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.utils.CoreGameUtils;

public class StartMenuPanel extends Entity {

    private List<Entity> entities = new ArrayList<Entity>();

    public StartMenuPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 0);
	Texture startImage = new Texture(RESOURCE_FOLDER + "/logo.png");

	// TODO: should offset x and y by the entities x and y on these
	// images/button
	StaticImageEntity logo = new StaticImageEntity((width / 2) - (startImage.getWidth() / 2), height / 5,
		startImage);
	entities.add(logo);

	StringEntity startButton = new StringEntity((width / 2) - 75, height / 2, 12, "Start Game");
	startButton.setDrawBorder(true);
	startButton.setListener(new StartButtonListener());
	entities.add(startButton);

	StringEntity loadButton = new StringEntity((width / 2) - 75, (int) (height / 1.75), 12, "Load Game");
	loadButton.setDrawBorder(true);
	loadButton.setListener(new LoadButtonListener());
	entities.add(loadButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public List<Entity> getChildren() {
	return entities;
    }

    public class StartButtonListener implements EntityListener {

	@Override
	public void mouseEntered(Entity e) {
	    // no action required
	}

	@Override
	public void mouseExited(Entity e) {
	    // no action required
	}

	@Override
	public void mousePressed(Entity e, int mouseX, int mouseY) {
	    CoreGameUtils.newGame();
	    EntityManager.getPartyGenerationPanel().reset();
	    GameHolder.gameMode = GameMode.PARTY_GENERATION;
	}

    }

    public class LoadButtonListener implements EntityListener {

	@Override
	public void mouseEntered(Entity e) {
	    // no action required
	}

	@Override
	public void mouseExited(Entity e) {
	    // no action required
	}

	@Override
	public void mousePressed(Entity e, int mouseX, int mouseY) {
	    CoreGameUtils.loadGame("test");
	}

    }

}

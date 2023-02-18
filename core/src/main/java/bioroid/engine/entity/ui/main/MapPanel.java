package bioroid.engine.entity.ui.main;

import static bioroid.Constants.RESOURCE_FOLDER;
import static bioroid.Constants.TILE_SIZE;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

import bioroid.GameHolder;
import bioroid.control.action.Action;
import bioroid.control.action.ActionType;
import bioroid.engine.entity.Entity;
import bioroid.engine.entity.MapBasedEntity;
import bioroid.engine.entity.PlayerCharacterEntity;
import bioroid.engine.entity.listener.EntityListener;
import bioroid.model.character.GameCharacter;
import bioroid.model.location.GameMap;
import bioroid.model.location.Location;

public class MapPanel extends Entity {

    private GameMap gameMap;

    private List<MapBasedEntity> entities = new ArrayList<MapBasedEntity>();

    // location where the map should be centered (in tiles not pixels)
    private Location viewPoint;

    private int mapOffsetX, mapStartX, mapEndX;

    private int mapOffsetY, mapStartY, mapEndY;

    public int mapHeight, mapWidth, mapHalfHeight, mapHalfWidth;

    public MapPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 1);

	Texture mapBackground = new Texture(RESOURCE_FOLDER + "/map_background.png");

	super.setImage(mapBackground);

	determineOptimumPosition();

	setListener(new MapPanelListener(this));
    }

    public void updatePositionAndSize(int x, int y, int width, int height) {
	setX(x);
	setY(y);
	setWidth(width);
	setHeight(height);
	determineOptimumPosition();
	updateOffsets();
    }

    private void determineOptimumPosition() {
	mapHeight = getHeight() / TILE_SIZE;
	mapWidth = getWidth() / TILE_SIZE;
	mapHalfHeight = mapHeight / 2;
	mapHalfWidth = mapWidth / 2;

	// move topleft and bottom right to center of available window
	int widthRemainder = getWidth() % TILE_SIZE;
	int newXOffset = (widthRemainder / 2);
	setX(getX() + newXOffset);

	int heightRemainder = getHeight() % TILE_SIZE;
	int newYOffset = heightRemainder / 2;
	setY(getY() + newYOffset);

	setWidth(getWidth() - widthRemainder);
	setHeight(getHeight() - heightRemainder);
    }

    @Override
    protected void renderEntitySpecific(Graphics g) {
	// render tmxMap
	gameMap.getMap().draw(g, mapOffsetX, mapOffsetY, mapStartX, mapStartY, mapEndX, mapEndY);
    }

    private void updateOffsets() {
	mapOffsetX = getX();
	mapStartX = 0;
	mapEndX = mapWidth;

	if (viewPoint.getX() < mapHalfWidth) {
	    mapOffsetX = mapHalfWidth - viewPoint.getX();
	    mapOffsetX = getX() + (mapOffsetX * TILE_SIZE);
	    mapEndX = mapHalfWidth + viewPoint.getX();
	    if ((mapWidth % 2) == 1) {
		mapEndX++;
	    }
	} else {
	    mapStartX = viewPoint.getX() - mapHalfWidth;
	    // map end incorrect!
	}

	mapOffsetY = getY();
	mapStartY = 0;
	mapEndY = mapHeight;

	if (viewPoint.getY() < mapHalfHeight) {
	    mapOffsetY = mapHalfHeight - viewPoint.getY();
	    mapOffsetY = getY() + (mapOffsetY * TILE_SIZE);
	    mapEndY = mapHalfHeight + viewPoint.getY();
	    if ((mapHeight % 2) == 1) {
		mapEndY++;
	    }
	} else {
	    mapStartY = viewPoint.getY() - mapHalfHeight;
	}
    }

    @Override
    public void update(float delta) {
	// determine what is the current viewpoint
	// note: this is just an optimisation for when the viewpoint has not
	// moved.
	if (!viewPoint.equals(GameHolder.viewPoint)) {
	    viewPoint.updateWith(GameHolder.viewPoint);
	    // calculate what part of the map should be shown
	    updateOffsets();
	}

	// update npc render offsets as they may have moved!
	for (MapBasedEntity e : entities) {
	    e.updateRenderLocation(mapOffsetX, mapOffsetY, mapStartX, mapStartY, mapEndX, mapEndY);
	    e.update(delta);
	}
    }

    /**
     * Call this method on a map transition.
     */
    public void reset() {

	viewPoint = GameHolder.viewPoint.copy();
	updateOffsets();

	// refresh list of entities
	entities.clear();
	List<GameCharacter> pcs = GameHolder.currentGame.getPlayerCharacters();
	for (GameCharacter pc : pcs) {
	    PlayerCharacterEntity pce = new PlayerCharacterEntity(pc);
	    entities.add(pce);
	}
	// load NPC entities onto map
	for (GameCharacter pc : gameMap.getNpc()) {
	    PlayerCharacterEntity pce = new PlayerCharacterEntity(pc);
	    entities.add(pce);
	}
    }

    @Override
    public List<MapBasedEntity> getChildren() {
	return entities;
    }

    public void setGameMap(GameMap gameMap) {
	this.gameMap = gameMap;
    }

    public class MapPanelListener implements EntityListener {

	private MapPanel mapPanel;

	public MapPanelListener(MapPanel mapPanel) {
	    this.mapPanel = mapPanel;
	}

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
	    if (GameHolder.blockingEntity != null) {
		return;
	    }
	    int x = mouseX - mapPanel.mapOffsetX;
	    int y = mouseY - mapPanel.mapOffsetY;
	    x = x / TILE_SIZE;
	    y = y / TILE_SIZE;
	    x = x + mapPanel.mapStartX;
	    y = y + mapPanel.mapStartY;

	    System.out.println("Clicked on location x:" + x + " y:" + y);
	    if (!gameMap.isMoveBlocked(x, y)) {
		GameHolder.currentAction = new Action(ActionType.MOVE, new Location(x, y));
	    }

	}

    }

}

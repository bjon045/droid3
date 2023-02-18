package bioroid.engine.entity;

import static bioroid.Constants.TILE_SIZE;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

import bioroid.model.location.Location;

/**
 * An entity represents any element that appears in the game.
 * 
 * @author Bruce Jones
 */
public abstract class MapBasedEntity extends Entity {

    private boolean onMap;

    public MapBasedEntity(String imgRef, int x, int y, int size) {
	super(x, y, TILE_SIZE * size, TILE_SIZE * size, 2);
	Texture image = new Texture(imgRef);
	super.setImage(image);
    }

    /**
     * Position this entity relative to the current map
     * 
     * @param mapStartY
     * @param mapStartX
     * @param mapOffsetY
     * @param mapOffsetX
     * @param mapEndY
     * @param mapEndX
     */
    public void updateRenderLocation(int mapOffsetX, int mapOffsetY, int mapStartX, int mapStartY, int mapEndX,
	    int mapEndY) {

	if ((getLocation().getX() < mapStartX) || (getLocation().getY() < mapStartY)
		|| (getLocation().getX() >= (mapStartX + mapEndX)) || (getLocation().getY() >= (mapStartY + mapEndY))) {
	    // entity is not visible so do not draw!
	    onMap = false;
	    return;
	}
	onMap = true;

	setX(mapOffsetX + ((getLocation().getX() - mapStartX) * TILE_SIZE));
	setY(mapOffsetY + ((getLocation().getY() - mapStartY) * TILE_SIZE));

    }

    @Override
    public void render(Graphics g) {
	if (onMap) {
	    super.render(g);
	}
    }

    public abstract Location getLocation();

}

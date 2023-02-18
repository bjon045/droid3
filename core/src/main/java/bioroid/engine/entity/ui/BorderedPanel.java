package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

import bioroid.engine.entity.Entity;
import bioroid.model.location.Location;

public class BorderedPanel extends Entity {

    protected Texture horizontalBorder;

    private Texture borderTopLeft;

    protected int borderThickness;

    private List<Entity> children = new ArrayList<Entity>();

    public BorderedPanel(int x, int y, int width, int height) {
	super(x, y, width, height, 0);

	horizontalBorder = new Texture(RESOURCE_FOLDER + "/border_horizontal.png");
	borderTopLeft = new Texture(RESOURCE_FOLDER + "/border_top_left.png");
	borderThickness = horizontalBorder.getHeight();

    }

    @Override
    protected void renderEntitySpecific(Graphics g) {
	// top border
	g.drawTexture(horizontalBorder, getX(), getY(), getWidth(), borderThickness);

	// bottom border
	g.drawTexture(horizontalBorder, getX(), (getY() + getHeight()) - borderThickness, getWidth(), borderThickness);

	// left border
	g.rotate(90, getX(), getY());
	g.drawTexture(horizontalBorder, getX(), getY() - borderThickness, getHeight(), borderThickness);
	g.rotate(-90, getX(), getY());

	// right border
	g.rotate(90, getX(), getY());
	g.drawTexture(horizontalBorder, getX(), (getY() - getWidth()), getHeight(), borderThickness);
	g.rotate(-90, getX(), getY());

	// top left corner
	g.drawTexture(borderTopLeft, getX(), getY());

	// top right corner
	g.rotate(90, getX(), getY());
	g.drawTexture(borderTopLeft, getX(), getY() - getWidth());
	g.rotate(-90, getX(), getY());

	// bottom left corner
	g.rotate(-90, getX(), getY());
	g.drawTexture(borderTopLeft, getX() - getHeight(), getY());
	g.rotate(90, getX(), getY());

	// bottom right corner
	g.rotate(180, getX(), getY());
	g.drawTexture(borderTopLeft, getX() - getWidth(), getY() - getHeight());
	g.rotate(-180, getX(), getY());
    }

    @Override
    public void update(float delta) {
	// not used
    }

    /**
     * Get the topleft pixel location of the main window that is usable i.e. topLeft
     * pixel after the border ends. Used for map, char screen etc
     * 
     * @return
     */

    public Location getMainStart() {
	return new Location(getX() + borderThickness, getY() + borderThickness);
    }

    public int getMainWidth() {
	return getWidth() - borderThickness;
    }

    public int getMainHeight() {
	return getHeight() - (borderThickness * 2);
    }

    public int getBorderThickness() {
	return borderThickness;
    }

    @Override
    public List<Entity> getChildren() {
	return children;
    }

}

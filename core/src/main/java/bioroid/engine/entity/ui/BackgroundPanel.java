package bioroid.engine.entity.ui;

import static bioroid.Constants.RESOURCE_FOLDER;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Texture;

import bioroid.Constants;
import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.EntityManager;

public class BackgroundPanel extends BorderedPanel {

    private Texture borderTee;

    private int halfTeeThickness;

    private int charPanelXSize;

    public BackgroundPanel(int x, int y, int width, int height) {
	super(x, y, width, height);

	horizontalBorder = new Texture(RESOURCE_FOLDER + "/border_horizontal.png");
	borderTee = new Texture(RESOURCE_FOLDER + "/border_tee.png");

	borderThickness = horizontalBorder.getHeight();
	halfTeeThickness = borderTee.getWidth() / 2;
	charPanelXSize = Constants.CHAR_PANEL_WIDTH + (borderThickness * 2);

    }

    @Override
    protected void renderEntitySpecific(Graphics g) {
	super.renderEntitySpecific(g);
	if (GameHolder.gameMode == GameMode.MAIN_GAME && EntityManager.getCharacterPanel().isActive()) {
	    // center border
	    g.rotate(getX(), getY(), 90);
	    g.drawTexture(horizontalBorder, getX(), ((getY() - getWidth()) + charPanelXSize) - borderThickness,
		    getHeight(), borderThickness);
	    g.rotate(getX(), getY(), -90);

	    // top tee
	    g.drawTexture(borderTee,
		    getX() + ((getWidth() - charPanelXSize) + (borderThickness / 2)) - halfTeeThickness, getY());

	    // bottom tee
	    g.rotate(getX(), getY(), 180);
	    g.drawTexture(borderTee,
		    (((getX() - getWidth()) + charPanelXSize) - (borderThickness / 2)) - halfTeeThickness,
		    getY() - getHeight());
	    g.rotate(getX(), getY(), -180);
	}
    }

    public int getMapWidth() {
	return (getWidth() - (EntityManager.getCharacterPanel().isActive() ? charPanelXSize : borderThickness))
		- borderThickness;
    }

    public int getMapHeight() {
	return getMainHeight();
    }

    @Override
    public boolean inside(int xp, int yp) {
	return false;
    }

}

package bioroid.engine.entity;

import java.util.Arrays;
import java.util.List;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

import bioroid.font.Fonts;
import bioroid.utils.StringUtils;

public class StringEntity extends Entity {

    private static final int ROW_GAP = 2;

    private static int lineHeight = (int) Fonts.GREEN_FONT.getLineHeight() + ROW_GAP;

    private static int lineWidth = (int) Fonts.GREEN_FONT.getLineHeight() + 3;// TODO using height, hardcore this

    private Color colour = Color.GREEN;

    private List<String> values;

    public StringEntity(int x, int y, String value) {
	super(x, y, 0, 0, 0);
	values = Arrays.asList(value);
	setHeight(values.size() * lineHeight);
	setWidth(value.length() * lineWidth);
    }

    public StringEntity(int x, int y, int maxWidth, String value) {
	super(x, y, 0, 0, 0);
	values = StringUtils.breakUpByWords(value, maxWidth);
	setHeight(values.size() * lineHeight);
	setWidth(maxWidth * lineWidth);
    }

    public StringEntity(int x, int y, int width, int height, String value) {
	super(x, y, width, height, 0);
	values = StringUtils.breakUpByWords(value, 100);
    }

    public void setColour(Color colour) {
	this.colour = colour;
    }

    @Override
    public void update(float delta) {
	// no update required
    }

    @Override
    protected void renderEntitySpecific(Graphics g) {
	super.renderEntitySpecific(g);
	g.drawCircle(1, 1, 0);// Hack! Otherwise Font draw complains about sprite batch
	int rowNo = 0;
	for (String s : values) {
	    Fonts.GREEN_FONT.draw(g, s, getX(), getY() + (rowNo * lineHeight));
	    rowNo++;
	}
    }

}

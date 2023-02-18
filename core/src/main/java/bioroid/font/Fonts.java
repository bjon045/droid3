package bioroid.font;

import org.mini2Dx.core.font.BitmapFont;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

public class Fonts {

    public static final BitmapFont GREEN_FONT;

    public static final BitmapFont RED_FONT;

    static {
	FileHandle fontFile = Gdx.files.internal("font/space.fnt");
	FileHandle graphicFile = Gdx.files.internal("font/space_00.png");
	// TODO: implement this
	GREEN_FONT = new BitmapFont(fontFile);
	GREEN_FONT.setColor(Color.GREEN);
	RED_FONT = new BitmapFont(fontFile);
	RED_FONT.setColor(Color.RED);

    }
}

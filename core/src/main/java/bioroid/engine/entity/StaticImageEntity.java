package bioroid.engine.entity;

import com.badlogic.gdx.graphics.Texture;

public class StaticImageEntity extends Entity {

    public StaticImageEntity(int x, int y, Texture image) {
	super(x, y, image.getWidth(), image.getHeight(), 0);
	super.setImage(image);
    }

    @Override
    public void update(float delta) {
	// no update required
    }

}

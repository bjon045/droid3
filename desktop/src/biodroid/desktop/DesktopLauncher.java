package biodroid.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import bioroid.BioroidGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
	DesktopMini2DxConfig config = new DesktopMini2DxConfig(BioroidGame.GAME_IDENTIFIER);
	config.vSyncEnabled = true;
	new DesktopMini2DxGame(new BioroidGame(), config);
    }
}

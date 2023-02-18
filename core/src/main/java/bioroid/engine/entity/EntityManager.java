package bioroid.engine.entity;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import bioroid.Constants;
import bioroid.GameMode;
import bioroid.engine.entity.ui.BackgroundPanel;
import bioroid.engine.entity.ui.BorderedPanel;
import bioroid.engine.entity.ui.StartMenuPanel;
import bioroid.engine.entity.ui.main.CharacterPanel;
import bioroid.engine.entity.ui.main.MapPanel;
import bioroid.engine.entity.ui.partygeneration.PartyGenerationPanel;
import bioroid.engine.entity.ui.raceselection.RaceSelectionPanel;
import bioroid.engine.entity.ui.skillselection.SkillSelectionPanel;
import bioroid.model.location.Location;
import bioroid.utils.StringUtils;

public final class EntityManager {

    private static BackgroundPanel backgroundPanel;

    private static StartMenuPanel startMenuPanel;

    private static MapPanel mapPanel;

    private static CharacterPanel characterPanel;

    private static PartyGenerationPanel partyGenerationPanel;

    private static RaceSelectionPanel raceSelectionPanel;

    private static SkillSelectionPanel skillSelectionPanel;

    private static BorderedPanel popupPanel;

    private static String currentScreenSize = "";

    public static List<Entity> getEntities(GameMode mode) {
	switch (mode) {
	case START_MENU:
	    return Arrays.asList(getBackgroundPanel(), getStartMenuPanel());
	case MAIN_GAME:
	    return Arrays.asList(getBackgroundPanel(), getMapPanel(), getCharacterPanel(), getPopupPanel());
	case PARTY_GENERATION:
	    return Arrays.asList(getBackgroundPanel(), getPartyGenerationPanel());
	case RACE_SELECTION:
	    return Arrays.asList(getBackgroundPanel(), getRaceSelectionPanel(), getPopupPanel());
	case SKILL_SELECTION:
	    return Arrays.asList(getBackgroundPanel(), getSkillSelectionPanel(), getPopupPanel());
	case INVENTORY_SCREEN:
	    // TODO: requirement
	    break;
	case SKILL_SCREEN:
	    // TODO: requirement
	    break;
	default:
	    break;
	}
	return null;
    }

    public static BackgroundPanel getBackgroundPanel() {
	if (backgroundPanel == null) {
	    currentScreenSize = Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight();
	    backgroundPanel = new BackgroundPanel(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	return backgroundPanel;
    }

    public static BorderedPanel getPopupPanel() {
	if (popupPanel == null) {
	    popupPanel = new BorderedPanel(0, 0, 0, 0);
	    popupPanel.setActive(false);
	    popupPanel.setBackground(Color.GRAY);
	}

	return popupPanel;
    }

    public static StartMenuPanel getStartMenuPanel() {
	if (startMenuPanel == null) {
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();
	    startMenuPanel = new StartMenuPanel(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());
	}
	return startMenuPanel;
    }

    public static MapPanel getMapPanel() {
	if (mapPanel == null) {
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();
	    mapPanel = new MapPanel(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMapWidth(), getBackgroundPanel().getMapHeight());
	}
	return mapPanel;
    }

    public static CharacterPanel getCharacterPanel() {
	if (characterPanel == null) {
	    characterPanel = new CharacterPanel(Gdx.graphics.getWidth() - Constants.CHAR_PANEL_WIDTH, 0,
		    Constants.CHAR_PANEL_WIDTH, Gdx.graphics.getHeight());
	}
	return characterPanel;
    }

    public static PartyGenerationPanel getPartyGenerationPanel() {
	if (partyGenerationPanel == null) {
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();
	    partyGenerationPanel = new PartyGenerationPanel(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());
	}
	return partyGenerationPanel;
    }

    public static RaceSelectionPanel getRaceSelectionPanel() {
	if (raceSelectionPanel == null) {
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();
	    raceSelectionPanel = new RaceSelectionPanel(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());
	}
	return raceSelectionPanel;
    }

    public static SkillSelectionPanel getSkillSelectionPanel() {
	if (skillSelectionPanel == null) {
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();
	    skillSelectionPanel = new SkillSelectionPanel(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());
	}
	return skillSelectionPanel;
    }

    public static void resizeEntities() {
	String newScreenSize = Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight();
	if (StringUtils.isNotBlank(currentScreenSize) && StringUtils.notEquals(currentScreenSize, newScreenSize)) {

	    getBackgroundPanel().updatePositionAndSize(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    Location mainPanelLocation = getBackgroundPanel().getMainStart();

	    getStartMenuPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
		    getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());

	    if (mapPanel != null) {
		getMapPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
			getBackgroundPanel().getMapWidth(), getBackgroundPanel().getMapHeight());
	    }

	    if (characterPanel != null) {
		getCharacterPanel().updatePositionAndSize(Gdx.graphics.getWidth() - Constants.CHAR_PANEL_WIDTH, 0,
			Constants.CHAR_PANEL_WIDTH, Gdx.graphics.getHeight());
	    }

	    if (partyGenerationPanel != null) {
		getPartyGenerationPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
			getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());

	    }

	    if (raceSelectionPanel != null) {
		getRaceSelectionPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
			getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());
	    }

	    if (skillSelectionPanel != null) {
		getSkillSelectionPanel().updatePositionAndSize(mainPanelLocation.getX(), mainPanelLocation.getY(),
			getBackgroundPanel().getMainWidth(), getBackgroundPanel().getMainHeight());

	    }
	    currentScreenSize = newScreenSize;
	}

    }

}

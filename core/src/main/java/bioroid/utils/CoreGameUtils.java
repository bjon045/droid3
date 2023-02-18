package bioroid.utils;

import java.io.File;

import bioroid.GameHolder;
import bioroid.GameMode;
import bioroid.engine.entity.EntityManager;
import bioroid.model.ModelResources;
import bioroid.model.character.GameCharacter;
import bioroid.model.game.SavedGame;
import bioroid.model.location.GameMap;
import bioroid.model.location.Maps;

public class CoreGameUtils {

    public static void loadGame(String name) {
	GameHolder.currentGame = ModelUtils.loadModelObject(SavedGame.class, "/saves/" + name + "/game.xml");
	GameHolder.activeCharacter = CharacterUtils.getMainCharacter();
	// on first load set the game view point to the main character location
	GameHolder.viewPoint = GameHolder.activeCharacter.getLocation().copy();

	// load any available maps
	Maps maps = ModelUtils.loadModelObject(Maps.class, "/maps/maps.xml");
	for (String map : maps.getMap()) {
	    GameMap mapObj;

	    // maps should be loaded from the SavedGame if available!!
	    File savedMap = new File("saves/" + name + "/" + map + "/map.xml");
	    if (savedMap.exists()) {
		mapObj = ModelUtils.loadModelObject(GameMap.class, savedMap);
	    } else {
		mapObj = ModelUtils.loadModelObject(GameMap.class, "/maps/" + map + "/map.xml");
	    }
	    GameHolder.maps.put(mapObj.getCode(), mapObj);

	    // TODO: requirement: preload events and maps
	    // NPC state needs to be adjusted based on game state i.e if npc is dead or
	    // moved.
	    for (GameCharacter character : ModelResources.characters.getCharacters()) {
		if (StringUtils.equals(character.getMapCode(), mapObj.getCode())) {
		    mapObj.getNpc().add(character);
		}
	    }
	}

	performMapTransistion();

	GameHolder.gameMode = GameMode.MAIN_GAME;

    }

    private static void performMapTransistion() {
	GameHolder.allCharactersOnMap.clear();
	GameHolder.allCharactersOnMap.addAll(GameHolder.currentGame.getPlayerCharacters());

	GameMap gameMap = GameHolder.maps.get(GameHolder.currentGame.getCurrentMapCode());
	GameHolder.currentMap = gameMap;
	EntityManager.getMapPanel().setGameMap(gameMap);

	GameHolder.allCharactersOnMap.addAll(GameHolder.currentMap.getNpc());
	// TODO: sort list based on order of action
	EntityManager.getMapPanel().reset();
    }

    public static void saveGame(String name) {
	ModelUtils.saveModelObject(GameHolder.currentGame, new File("saves/" + name + "/game.xml"));
    }

    public static void newGame() {
	GameHolder.currentGame = new SavedGame();
	GameHolder.currentAction = null;
	GameHolder.combatMode = false;
	GameHolder.currentMap = null;
	GameHolder.maps.clear();
	GameHolder.viewPoint = null;
    }
}

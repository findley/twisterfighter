package run;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.Effect;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

    public final static int GAME_HEIGHT = 600;
    public final static int GAME_WIDTH = 800;

    public Game() {
        super("Twister Fighter");
    }

    @SuppressWarnings("unchecked")
    public static UnicodeFont loadFont(String name, int style, int size,
            Color color) throws SlickException {
        UnicodeFont font = new UnicodeFont(new Font(name, style, size));
        font.addAsciiGlyphs();
        ((List<Effect>) font.getEffects()).add(new ColorEffect(color));
        font.loadGlyphs();
        return font;
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainMenuState());
        addState(new PlayGameState());
        addState(new InstructionsState());
        addState(new CreditsState());
        addState(new GameEndState());
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws SlickException {
        System.setProperty("org.lwjgl.librarypath",
                new File("lwjgl/native/all").getAbsolutePath());
        // String separator = System.getProperty("file.separator");
        // System.setProperty("org.lwjgl.librarypath",
        // System.getProperty("user.dir") + separator + "lwjgl"
        // + separator + "native" + separator + "all");
        AppGameContainer app = new AppGameContainer(new Game());
        app.setVerbose(false);
        app.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
        app.setVSync(true);
        app.setShowFPS(false);
        app.start();
    }

}

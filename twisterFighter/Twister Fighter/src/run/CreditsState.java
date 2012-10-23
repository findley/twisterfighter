package run;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditsState extends BasicGameState {

    public final static int STATE_ID = 3;

    private UnicodeFont main;
    private UnicodeFont subtitle;
    private UnicodeFont body;

    private int HEIGHT;
    private int WIDTH;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        main = Game.loadFont("Arial Monospaced", Font.BOLD, 40, Color.WHITE);
        // main = new UnicodeFont(new Font("Arial Monospaced", Font.BOLD, 40));
        // main.addAsciiGlyphs();
        // main.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        // main.loadGlyphs();

        subtitle = Game
                .loadFont("Arial Monospaced", Font.BOLD, 30, Color.WHITE);
        // subtitle = new UnicodeFont(new Font("Arial Monospaced", Font.BOLD,
        // 30));
        // subtitle.addAsciiGlyphs();
        // subtitle.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        // subtitle.loadGlyphs();

        body = Game.loadFont("Arial Monospaced", Font.PLAIN, 20,
                Color.LIGHT_GRAY);
        // body = new UnicodeFont(new Font("Arial Monospaced", Font.PLAIN, 20));
        // body.addAsciiGlyphs();
        // body.getEffects().add(new ColorEffect(java.awt.Color.LIGHT_GRAY));
        // body.loadGlyphs();

        HEIGHT = container.getHeight();
        WIDTH = container.getWidth();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setBackground(new org.newdawn.slick.Color(51, 51, 51));
        int first_width = main.getWidth("Credits");
        int second_width = subtitle.getWidth("Devs");
        int third_width = subtitle.getWidth("Resources");
        int last_width = body
                .getWidth("Press SPACE to return to the main menu");
        int first_height = main.getHeight("Credits");
        int last_height = body.getHeight("P");

        main.drawString(WIDTH / 2 - first_width / 2, HEIGHT / 30, "Credits");

        subtitle.drawString(WIDTH / 4 - second_width / 2, HEIGHT / 40 + 2
                * first_height, "Devs");
        subtitle.drawString(WIDTH * 3 / 4 - third_width / 2, HEIGHT / 40 + 2
                * first_height, "Resources");

        body.drawString(
                WIDTH / 8,
                HEIGHT / 40 + 4 * first_height,
                "Art/Code: Elizabeth Findley\n\nCode: Alex Goins\n\nCode: Mike Salvato\n\n"
                        + "Code: Nathan Arce\n\nCode: Todd Layton\n\nSound: Ben Greenberg");
        body.drawString(
                WIDTH * 5 / 8,
                HEIGHT / 40 + 4 * first_height,
                "Written in Java\n\nEngine: Slick,\nbased on LWJGL\n\nFor sound file,\nMusic, and other\nresource attribution,\nSee readme.txt");

        body.drawString(WIDTH / 2 - last_width / 2, HEIGHT - 2 * last_height,
                "Press SPACE to return to the main menu");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_SPACE)
                && container.getInput().isKeyDown(Input.KEY_SPACE)) {
            game.enterState(MainMenuState.STATE_ID);
        }
    }

    @Override
    public int getID() {
        return STATE_ID;
    }
}

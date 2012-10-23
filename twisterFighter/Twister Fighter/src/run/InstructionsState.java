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

public class InstructionsState extends BasicGameState {

    public final static int STATE_ID = 2;

    private UnicodeFont main;
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

        body = Game.loadFont("Arial Monospaced", Font.PLAIN, 25,
                Color.LIGHT_GRAY);

        // body = new UnicodeFont(new Font("Arial Monospaced", Font.PLAIN, 25));
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
        int first_width = main.getWidth("Instructions");
        int second_width = body
                .getWidth("Press SPACE to return to the main menu");
        int first_height = main.getHeight("Instructions");
        int second_height = body.getHeight("Type");
        main.drawString(WIDTH / 2 - first_width / 2, HEIGHT / 14,
                "Instructions");
        body.drawString(
                WIDTH / 6,
                HEIGHT / 16 + 2 * first_height,
                "\n"
                        + "Type the words above your player to\nattack your opponent\n\n"
                        + "Players type on the same keyboard\nat the same time\n\n"
                        + "Press ESC during gameplay to return\nto the main menu\n\n"
                        + "Not recommended for those with\npersonal space-related phobias");
        body.drawString(WIDTH / 2 - second_width / 2, HEIGHT
                - (int) ((1.5) * second_height),
                "Press SPACE to return to the main menu");
        ;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (container.getInput().isKeyDown(Input.KEY_SPACE)
                && container.getInput().isKeyPressed(Input.KEY_SPACE)) {
            game.enterState(MainMenuState.STATE_ID);
        }
    }

    @Override
    public int getID() {
        return STATE_ID;
    }

}

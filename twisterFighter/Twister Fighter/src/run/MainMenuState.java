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

public class MainMenuState extends BasicGameState {

    public final static int STATE_ID = 0;

    private UnicodeFont main;

    private int HEIGHT;
    private int WIDTH;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        main = Game.loadFont("Arial Monospaced", Font.BOLD, 50, Color.WHITE);
        // main = new UnicodeFont(new Font("Arial Monospaced", Font.BOLD, 40));
        // main.addAsciiGlyphs();
        // main.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        // main.loadGlyphs();

        HEIGHT = container.getHeight();
        WIDTH = container.getWidth();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.setBackground(new org.newdawn.slick.Color(51, 51, 51));
        int title_width = main.getWidth("TWISTER FIGHTER");
        int first_width = main.getWidth("Press SPACE to Start");
        int second_width = main.getWidth("Press i to View Instructions");
        int third_width = main.getWidth("Press c to View Credits");
        int first_height = main.getHeight("Press SPACE to Start");
        main.drawString(WIDTH / 2 - title_width / 2, (int) (HEIGHT / 3.2) - 2
                * first_height, "TWISTER FIGHTER");
        main.drawString(WIDTH / 2 - first_width / 2, (int) (HEIGHT / 3.2) + 1
                * first_height, "Press SPACE to Start");
        main.drawString(WIDTH / 2 - second_width / 2, (int) (HEIGHT / 3.2) + 3
                * first_height, "Press i to View Instructions");
        main.drawString(WIDTH / 2 - third_width / 2, (int) (HEIGHT / 3.2) + 5
                * first_height, "Press c to View Credits");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_SPACE)
                && container.getInput().isKeyDown(Input.KEY_SPACE)) {
            game.enterState(PlayGameState.STATE_ID);
        } else if (container.getInput().isKeyDown(Input.KEY_I)) {
            game.enterState(InstructionsState.STATE_ID);
        } else if (container.getInput().isKeyDown(Input.KEY_C)) {
            game.enterState(CreditsState.STATE_ID);
        }
    }

    @Override
    public int getID() {
        return STATE_ID;
    }

}

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

public class GameEndState extends BasicGameState {

    public final static int STATE_ID = 4;

    private UnicodeFont main;

    private int HEIGHT;
    private int WIDTH;

    private int winner;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        main = Game.loadFont("Arial Monospaced", Font.BOLD, 40, Color.WHITE);
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
        winner = PlayGameState.player_won;
        g.setBackground(new org.newdawn.slick.Color(51, 51, 51));
        String text;
        switch (winner) {
        case 0:
            text = "Draw!";
            break;
        case 1:
            text = "Player 1 Wins!";
            break;
        case 2:
            text = "Player 2 Wins!";
            break;
        default:
            text = "Unexpected Error!";
        }
        int first_width = main.getWidth(text);
        int second_width = main.getWidth("Press SPACE to Continue");
        int first_height = main.getHeight(text);
        main.drawString(WIDTH / 2 - first_width / 2, HEIGHT / 3, text);
        main.drawString(WIDTH / 2 - second_width / 2, HEIGHT / 3 + 2
                * first_height, "Press SPACE to Continue");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_SPACE)
                && container.getInput().isKeyDown(Input.KEY_SPACE)) {
            game.enterState(CreditsState.STATE_ID);
        }
    }

    @Override
    public int getID() {
        return STATE_ID;
    }

}
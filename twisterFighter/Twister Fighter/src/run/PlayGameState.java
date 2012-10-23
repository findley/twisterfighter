package run;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.GameRound;
import core.Player;

public class PlayGameState extends BasicGameState {

    public static final int STATE_ID = 1;

    public static int player_won;

    private int HEIGHT;
    private int WIDTH;

    private GameRound round = null;

    private UnicodeFont mainKey = null;
    private UnicodeFont subKey = null;

    private int HEALTH_WIDTH;

    private GradientFill healthFill = null;
    private Rectangle healthShape1 = null;
    private Rectangle healthShape2 = null;

    // private Image spriteSheet;
    // private Image[] p1Sprites = new Image[4];
    // private Image[] p2Sprites = new Image[4];
    private boolean winFlag = false;
    private int timer = 0;

    private Music backgroundMusic = null;

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        round = new GameRound();
        round.state = GameRound.State.RUNNING;

        WIDTH = container.getWidth();
        HEIGHT = container.getHeight();

        HEALTH_WIDTH = WIDTH / 2 - 50;

        mainKey = Game.loadFont("Arial Monospaced", Font.BOLD, 40,
                java.awt.Color.WHITE);

        subKey = Game.loadFont("Arial Monospaced", Font.PLAIN, 20,
                java.awt.Color.WHITE);

        healthShape1 = new Rectangle(30, 20, HEALTH_WIDTH, 50);
        healthShape2 = new Rectangle(WIDTH - HEALTH_WIDTH - 30, 20,
                HEALTH_WIDTH, 50);
        healthFill = new GradientFill(0, 25, new Color(255, 0, 0), 300, 25,
                new Color(25, 0, 0), true);
        round.player1.character.init();
        round.player2.character.init();

        backgroundMusic = new Music("sounds/background.ogg");
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game)
            throws SlickException {
        round = new GameRound();
        round.state = GameRound.State.RUNNING;
        backgroundMusic.loop();

    }

    @Override
    public void leave(GameContainer container, StateBasedGame game)
            throws SlickException {
        // backgroundMusic.stop();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        Image img = new Image("art/fireBackground2.png").getScaledCopy(WIDTH,
                HEIGHT);
        g.drawImage(img, 0, 0);
        g.fill(healthShape1, healthFill);
        g.fill(healthShape2, healthFill);
        g.setBackground(new Color(51, 51, 51));
        updateScreen();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        if (!winFlag) {
            int key = updatePlayer(container, game, delta, round.player1, -1);
            updatePlayer(container, game, delta, round.player2, key);

            if (round.state == GameRound.State.RUNNING) {
                round.timeRemaining -= delta;
                if (round.timeRemaining <= 0) {
                    winFlag = true;
                }
            }

            if (container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
                game.enterState(MainMenuState.STATE_ID);
            }
        } else {
            if (timer == 0) {
                if (round.player1.health == round.player2.health)
                    player_won = 0;
                else if (round.player1.health > round.player2.health)
                    player_won = 1;
                else
                    player_won = 2;
            }
            timer += delta;
            if (timer > 900) {
                winFlag = false;
                round.player1.health = Player.MAX_HEALTH;
                round.player2.health = Player.MAX_HEALTH;
                timer = 0; // don't ask me why these are necessary, the game was
                // bugging without them
                game.enterState(GameEndState.STATE_ID);
            }
        }
    }

    private static int getInputKeyCode(String keyName) {
        try {
            return Input.class.getField("KEY_" + keyName.toUpperCase()).getInt(
                    null);
        } catch (IllegalArgumentException e) {
            System.out.println("Illegel Argument!");
            e.printStackTrace();
        } catch (SecurityException e) {
            System.out.println("Security Exception!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Illegal Access!");
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("No Such Field!");
            e.printStackTrace();
        }
        return 0;
    }

    private static int getInputKeyCode(char keyName) {
        return getInputKeyCode(Character.toString(keyName));
    }

    private void updateScreen() {

        if (!winFlag) {
            drawKeys(WIDTH / 5, round.player1);
            drawKeys(WIDTH / 2 + WIDTH / 10, round.player2);
        }

        drawHealth(round.player1, healthShape1);
        drawHealth(round.player2, healthShape2);

        // drawSprite(round.player1);
        // drawSprite(round.player2);

        drawAttackSprite(round.player1, round.player1.letterQueue.getIndex(), 1);

        drawAttackSprite(round.player2, round.player2.letterQueue.getIndex(), 2);
    }

    private void drawKeys(int x, Player player) {
        int xLoc = x;
        for (int i = 0; i < player.letterQueue.getWord().length(); i++) {
            String letter = Character.toString(player.letterQueue.getWord()
                    .charAt(i));
            if (i == player.letterQueue.getLetterIndex()) {
                mainKey.drawString(xLoc, HEIGHT / 4, letter);
                xLoc += mainKey.getWidth(letter + " ");
            } else {
                subKey.drawString(xLoc,
                        HEIGHT / 4 + mainKey.getHeight("a") / 2, letter);
                xLoc += subKey.getWidth(letter + " ");
            }
        }
    }

    private void drawHealth(Player player, Rectangle healthShape) {
        // health.drawString(x, WIDTH / 40, String.valueOf(player.health));
        healthShape.setWidth(1 + (float) HEALTH_WIDTH * (float) player.health
                / (float) player.MAX_HEALTH);
        if (player.health == 0 && !winFlag) {
            backgroundMusic.fade(500, (float) .3, false);
            winFlag = true;
        }
    }

    // private void drawSprite(Player player) {
    // player.character.sprite.draw(player.location[0], player.location[1]);
    // }

    private void drawAttackSprite(Player player, int index, int id) {
        int xloc = player.location[0];
        int yloc = player.location[1];
        if (index < 3 && index != 0 && id == 1) {
            yloc -= 40;
        }
        if (index > 0 && id == 1) {
            xloc += 50;
        }
        if (index > 2 && id == 1) {
            xloc += 50;
        }
        if (index > 0 && id == 2) {
            xloc -= 100;
        }
        if (index == 4 && id == 2) {
            xloc -= 20;
        }
        player.character.attackSprites[index].draw(xloc, yloc);
    }

    private static boolean isKeyPressedNow(GameContainer container, int keyCode) {
        return container.getInput().isKeyDown(keyCode)
                && container.getInput().isKeyPressed(keyCode);
    }

    private int updatePlayer(GameContainer container, StateBasedGame game,
            int delta, Player player, int keyCode) {
        if (round.state == GameRound.State.RUNNING) {
            int newKey = getInputKeyCode(player.letterQueue.getLetter());
            if (isKeyPressedNow(container, newKey)
                    || (keyCode == newKey && container.getInput().isKeyDown(
                            keyCode))) {
                round.advanceAttack(player);
                return newKey;
            }
        }
        return -1;
    }

    @Override
    public int getID() {
        return STATE_ID;
    }

}

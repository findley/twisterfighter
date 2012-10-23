package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Character {

    private static final String spriteSheetPath = "art/basicposes.png";
    public Image[] attackSprites = new Image[5];
    private final String attackSoundPath;
    public Sound attackSound = null;

    private final int charNum;

    public Character(String attackSoundPath, int charNum) {
        this.attackSoundPath = attackSoundPath;
        this.charNum = charNum;
    }

    public void init() throws SlickException {
        attackSound = new Sound(attackSoundPath);
        setAttackSprites(charNum);
    }

    public void setAttackSprites(int charNum) throws SlickException {
        Image sprites = new Image(spriteSheetPath);
        if (charNum == 0) {
            this.attackSprites[0] = sprites.getSubImage(195, 152, 88, 124);
            this.attackSprites[1] = sprites.getSubImage(130, 290, 200,
                    414 - 290);
            this.attackSprites[2] = sprites.getSubImage(364, 488, 490 - 364,
                    621 - 488);
            this.attackSprites[3] = sprites.getSubImage(195, 152, 88, 124);
            this.attackSprites[4] = sprites.getSubImage(159, 424, 314 - 159,
                    560 - 424);
        } else if (charNum == 1) {
            this.attackSprites[0] = sprites.getSubImage(356, 8, 458 - 356,
                    134 - 8);
            this.attackSprites[1] = sprites.getSubImage(760, 172, 890 - 760,
                    399 - 172);
            this.attackSprites[2] = sprites.getSubImage(454, 136, 570 - 454,
                    316 - 136);
            this.attackSprites[3] = sprites.getSubImage(356, 8, 458 - 356,
                    134 - 8);
            this.attackSprites[4] = sprites.getSubImage(584, 178, 742 - 584,
                    315 - 178);
        }
    }

    public static final Character SOME_GUY = new Character(
            "sounds/maceHit.ogg", 1);
    public static final Character SOME_OTHER_GUY = new Character(
            "sounds/swordSlash.wav", 0);
}

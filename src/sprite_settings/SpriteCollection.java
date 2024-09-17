/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package sprite_settings;

import java.awt.Graphics;
import java.util.ArrayList;

/** .
 * The main class of SpriteCollection
 * Have list of Sprites
 */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;
    /** .
     * create GameEnvironment
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * @param s Sprite object to add to the sprites
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    };
    /**
     * @param s Sprite object to remove from the sprites
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    };
    /** .
     * call timePassed() on all sprites
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> currentSprites = new ArrayList<>(this.sprites);
        for (Sprite s: currentSprites) {
            s.timePassed();
        }
    };
    /**
     * @param g Graphics
     * call drawOn(g) on all sprites
     */
    public void drawAllOn(Graphics g) {
        ArrayList<Sprite> currentSprites = new ArrayList<>(this.sprites);
        for (Sprite s: currentSprites) {
            s.drawOn(g);
        }
    };
}

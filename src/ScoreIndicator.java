/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import sprite_settings.Sprite;

/** .
 * The main class of ScoreIndicator
 */
public class ScoreIndicator implements Sprite {
    private final ScoreTrackingListener scoreListener;
    private final Rectangle scoreRectngle;
    /**
     * @param scoreListener
     * @param scoreRectngle
     */
    public ScoreIndicator(ScoreTrackingListener scoreListener, Rectangle scoreRectngle) {
        this.scoreListener = scoreListener;
        this.scoreRectngle = scoreRectngle;
    };
    /**
     * @param g Graphics to draw the sprite to the screen
     */
    @Override
    public void drawOn(Graphics g) {
        scoreRectngle.drawOn(g, Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Score: " + this.scoreListener.getValue(), 380, 17);
    };
    /** .
     * Do nothing when time passed
     */
    @Override
    public void timePassed() {
    };
}

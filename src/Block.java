/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import sprite_settings.Sprite;

/** .
 * The main class of Block
 * Have rectangle and color
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private static double threshold = 0.0001;
    /**
     * @param startX upper left point x value
     * @param startY upper left point y value
     * @param width of rectangle
     * @param length of rectangle
     * @param color of block
     */
    public Block(double startX, double startY, double width, double length, Color color) {
        this.collisionRectangle = new Rectangle(new Point(startX, startY), width, length);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    };
    /**
     *  @param startX upper left point x value
     * @param startY upper left point y value
     * @param width of rectangle
     * @param length of rectangle
     */
    public Block(double startX, double startY, double width, double length) {
        this(startX, startY, width, length, Color.DARK_GRAY);
    };
    /**
     * @return the "collision shape" of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    };
    /**
     * @return the color of the object
     */
    public Color getColor() {
        return this.color;
    };
    /**
     * @param g Graphics to draw the rectangle on
     */
    @Override
    public void drawOn(Graphics g) {
        this.collisionRectangle.drawOn(g, this.color);
    };
    /**
     * @param g Game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    };
    /**
     * @param game to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    };
    /**
     * @param ball to match the color with
     * @return do the colors match
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    };
    /**
     * @param hl to add as a listener to hit events.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    };
    /**
     * @param hl to remove from the list of listeners to hit events
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    };
    /**
     * @param hitter ball that hit the Block
     * Make a copy of the hitListeners before iterating over them
     * Notify all listeners about a hit event
     */
    @Override
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    };
    /** .
     * Do nothing when time passed
     */
    @Override
    public void timePassed() {
    };
    /**
     * @param hitter ball that hit the Block
     * @param collisionPoint the object that we collided with it
     * @param currentVelocity given velocity to the collisionPoint
     * @param lineType the collidion line type (up, down, left, right)
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, String lineType) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        if (lineType.toLowerCase().equals("up")) {
            return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        }
        if (lineType.toLowerCase().equals("down")) {
            return new Velocity(currentVelocity.getDx(), Math.abs(currentVelocity.getDy()));
        }
        if (lineType.toLowerCase().equals("left")) {
            return new Velocity(-Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
        }
        if (lineType.toLowerCase().equals("right")) {
            return new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
        }
        return currentVelocity;
    };
    /**
     * @param collisionPoint the object that we collided with it
     * @param lineType the collidion line type (up, down, left, right)
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    @Override
    public Point getNextCenter(Point collisionPoint, String lineType) {
        if (lineType.toLowerCase().equals("up")) {
            return collisionPoint.add(0, -2 * threshold);
        }
        if (lineType.toLowerCase().equals("down")) {
            return collisionPoint.add(0, 2 * threshold);
        }
        if (lineType.toLowerCase().equals("left")) {
            return collisionPoint.add(-2 * threshold, 0);
        }
        if (lineType.toLowerCase().equals("right")) {
            return collisionPoint.add(2 * threshold, 0);
        }
        return collisionPoint;
    };
}

/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import sprite_settings.Sprite;

/** .
 * The main class of Paddle
 * Have rectangle, keyboard sensor, left border and right border
 */
public class Paddle implements Sprite, Collidable {
    private final Rectangle collisionRectangle;
    private final int leftBorder;
    private final int rightBorder;
    private final Color color = Color.ORANGE;
    private final int velocityDx = 25;
    private final int width = 150;
    private final int height = 20;
    private final int regions = 5;
    private static final double THRESHOLD = 0.0001;
    /**
     * @param leftBorder
     * @param rightBorder
     * @param heightPos y position of the paddles
     */
    public Paddle(int leftBorder, int rightBorder, int heightPos) {
        this.collisionRectangle = new Rectangle((rightBorder - leftBorder) / 2 - width / 2,
                                                heightPos - height, width, height);
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    };
    /** .
     * Move the paddle left
     */
    public void moveLeft() {
        // check border
        if (collisionRectangle.getUpperLeft().getX() + width / 2 < leftBorder) {
            collisionRectangle.setUpperLeft(collisionRectangle.getUpperLeft().add(rightBorder - leftBorder, 0));
        } else {
            collisionRectangle.setUpperLeft(collisionRectangle.getUpperLeft().add(-velocityDx, 0));
        }
    };
    /** .
     * Move the paddle right
     */
    public void moveRight() {
        if (collisionRectangle.getUpperLeft().getX() + width / 2 > rightBorder) {
            collisionRectangle.setUpperLeft(collisionRectangle.getUpperLeft().add(leftBorder - rightBorder, 0));
        } else {
            collisionRectangle.setUpperLeft(collisionRectangle.getUpperLeft().add(velocityDx, 0));
        }
    };
    /** .
     * time passed
     */
    @Override
    public void timePassed() { };
    /**
     * @param g Graphics to draw the rectangle on
     */
    @Override
    public void drawOn(Graphics g) {
        this.collisionRectangle.drawOn(g, this.color);
    };
    /** .
     * @return paddle rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
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
        if (lineType.toLowerCase().equals("up")) {
            switch ((int) (collisionPoint.getX() - this.collisionRectangle.getUpperLeft().getX())
                            / (this.width / this.regions)) {
                case 0 -> {
                    return currentVelocity.changeAngle(300);
                }
                case 1 -> {
                    return currentVelocity.changeAngle(330);
                }
                case 2 -> {
                    return new Velocity(currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
                }
                case 3 -> {
                    return currentVelocity.changeAngle(30);
                }
                case 4 -> {
                    return currentVelocity.changeAngle(60);
                }
                case 5 -> {
                    return currentVelocity.changeAngle(60);
                }
                default -> {
                }
            }
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
            return collisionPoint.add(0, -2 * THRESHOLD);
        }
        if (lineType.toLowerCase().equals("down")) {
            return collisionPoint.add(0, 2 * THRESHOLD);
        }
        if (lineType.toLowerCase().equals("left")) {
            return collisionPoint.add(-2 * THRESHOLD, 0);
        }
        if (lineType.toLowerCase().equals("right")) {
            return collisionPoint.add(2 * THRESHOLD, 0);
        }
        return collisionPoint;
    };
    /**
     * @param g Game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    };
}
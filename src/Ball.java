/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import sprite_settings.Sprite;

/** .
 * The main class of Ball
 * Have center point, radius, color, game environment and velocity
 */
public class Ball implements Sprite, HitListener {
    private Point center;
    private int r;
    private Color color;
    private GameEnvironment gameEnv;
    private Velocity velocity;
    /**
     * @param center point
     * @param r radius
     * @param color color
     * @param gameEnv Game Environment
     * @param velocity velocity
     * constructor - build Ball getting center point, radius and color
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnv, Velocity velocity) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnv = gameEnv;
        this.setVelocity(velocity);
    };
    /**
     * @param center point
     * @param r radius
     * @param color color
     * @param gameEnv Game Environment
     * constructor - build Ball getting center point, radius and color
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnv) {
        this(center, r, color, gameEnv, new Velocity(0, 0));
    };
    /**
     * @param x x value of point
     * @param y y value of point
     * @param r radius
     * @param color color
     * @param gameEnv game environment
     * constructor - build Ball getting x & y of point, radius and color
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gameEnv) {
        this(new Point(x, y), r, color, gameEnv);
    };
    /**
     * @return the x value of the center Point
     */
    public int getX() {
        return (int) this.center.getX();
    };
    /**
     * @return the y value of the center Point
     */
    public int getY() {
        return (int) this.center.getX();
    };
    /**
     * @return the radius of the Ball
     */
    public int getSize() {
        return this.r;
    };
    /**
     * @return the color of the Ball
     */
    public Color getColor() {
        return this.color;
    };
    /**
     * @param beingHit call this method is the beingHit object is hit
     * @param hitter is the Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.color = beingHit.getColor();
    };
    /**
     * @param g Game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    };
    /**
     * @param g Game to add the ball to
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    };
    /**
     * @param g surface to draw the Ball on
     * draw the ball on the given Graphics
     */
    @Override
    public void drawOn(Graphics g) {
        g.setColor(this.color);
        g.fillOval((int) this.center.getX(), (int) this.center.getY(), this.r, this.r);
        g.setColor(Color.BLACK);
        g.drawOval((int) this.center.getX(), (int) this.center.getY(), this.r, this.r);
    };
    /**
     * @param v velocity to set
     */
    private void setVelocity(Velocity v) {
        this.velocity = v;
    };
    /**
     * @param dx the dx of the velocity to set
     * @param dy the dy of the velocity to set
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    };
    /**
     * @return the velocity of the Ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    };
    /**
     * @param isPositiveX turn dx of velocity to positive or negative
     */
    public void setVelocityOppositeX(boolean isPositiveX) {
        if (isPositiveX) {
            this.setVelocity(Math.abs(this.getVelocity().getDx()), this.getVelocity().getDy());
        } else {
            this.setVelocity(-Math.abs(this.getVelocity().getDx()), this.getVelocity().getDy());
        }
    };
    /**
     * @param isPositiveY turn dy of velocity to positive or negative
     */
    public void setVelocityOppositeY(boolean isPositiveY) {
        if (isPositiveY) {
            this.setVelocity(this.getVelocity().getDx(), Math.abs(this.getVelocity().getDy()));
        } else {
            this.setVelocity(this.getVelocity().getDx(), -Math.abs(this.getVelocity().getDy()));
        }
    };
    /** .
     * Move the Ball to new position
     * Check if touch the borders and set new velocity
     */
    public void moveOneStep() {
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);
        CollisionInfo collInfo = gameEnv.getClosestCollision(trajectory);
        if (collInfo != null) {
            this.velocity = collInfo.collisionObject().hit(this, nextCenter, velocity, collInfo.lineType());
            nextCenter = collInfo.collisionObject().getNextCenter(collInfo.collisionPoint(), collInfo.lineType());
        }
        this.center =  nextCenter;
    };
    /** .
     * Move the Ball when time passed
     */
    @Override
    public void timePassed() {
        moveOneStep();
    };
}
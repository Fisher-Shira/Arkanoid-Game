/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;

/** .
 * The main interface of Collidable
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();
    /**
     * @param hitter ball that hit the Block
     * @param collisionPoint the object that we collided with it
     * @param currentVelocity given velocity to the collisionPoint
     * @param lineType the collidion line type (up, down, left, right)
     * @return the new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, String lineType);
    /**
     * @param collisionPoint the point of collision
     * @param lineType the collidion line type (up, down, left, right)
     * @return "almost" the collision point
     */
    Point getNextCenter(Point collisionPoint, String lineType);
}

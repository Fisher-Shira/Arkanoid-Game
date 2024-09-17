/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Point;

/** .
 * The main class of CollisionInfo
 * Have collision point, collision object and collision line type
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;
    private final String lineType;
    /**
     * @param collisionPoint point
     * @param collisionObject object
     * @param lineType up, down, left, right
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject, String lineType) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
        this.lineType = lineType;
    }
    /**
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    };
    /**
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    };
    /**
     * @return the collidion line type (up, down, left, right)
     */
    public String lineType() {
        return this.lineType;
    };
}
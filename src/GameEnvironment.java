/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Line;
import geometry_primitives.Point;
import java.util.ArrayList;

/** .
 * The main class of GameEnvironment
 * Have list of Collidable
 */
public class GameEnvironment {
    private final ArrayList<Collidable> collidables;
    /** .
     * create GameEnvironment
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }
    /**
     * @param c Collidable object to add to the collidables
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * @param c Collidable object to remove from the collidables
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * @return collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    /**
     * @param trajectory line to check collision with
     * @return closest Collidable object information
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Collidable> currentCollidables = new ArrayList<>(this.collidables);
        CollisionInfo cInfo = null;
        double distanceC = -1;
        for (Collidable collidable: currentCollidables) {
            Point closestP = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (closestP != null && (distanceC == -1 || trajectory.start().distance(closestP) < distanceC)) {
                distanceC = trajectory.start().distance(closestP);
                String lineType = collidable.getCollisionRectangle().intersectionLineType(closestP);
                cInfo = new CollisionInfo(closestP, collidable, lineType);
            }
        }
        return cInfo;
    }
}
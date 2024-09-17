/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package geometry_primitives;

/** .
 * The main class of Velocity
 * Have dx change and dy change in velocity
 */
public class Velocity {
    private final double dx;
    private final double dy;
    /**
     * @param dx double
     * @param dy double
     * constructor - build Velocity getting dx and dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    };
    /**
     * @return the dx of the Velocity
     */
    public double getDx() {
        return this.dx;
    };
    /**
     * @return the dy of the Velocity
     */
    public double getDy() {
        return this.dy;
    };
    /**
     * @param angle
     * @return new velocity with same speed and new angle (up = 0)
     */
    public Velocity changeAngle(double angle) {
        double speed = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        angle = (angle - 90) % 360;
        double angleRadians = Math.toRadians(angle);
        return new Velocity(speed * Math.cos(angleRadians), speed * Math.sin(angleRadians));
    }
    /**
     * @param p Point (x,y)
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return p.add(dx, dy);
    };
}
/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package geometry_primitives;

/** .
 * The main class of Point
 * Have x and y position
 */
public class Point {
    private double x;
    private double y;
    /**
     * @param x
     * @param y
     * constructor - build Point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @param x value of this point
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * @param y value of this point
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }
    /**
     * @param other point
     * @return the distance between the points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * @param other point
     * @return if points equal
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }
    /**
     * @param other point
     * @return point with added values
     */
    public Point add(Point other) {
        return new Point(this.x + other.getX(), this.y + other.getY());
    }
    /**
     * @param dx value
     * @param dy value
     * @return point with added values
     */
    public Point add(double dx, double dy) {
        return add(new Point(dx, dy));
    }
    /**
     * @param other point
     * @return point minus values
     */
    public Point minus(Point other) {
        return new Point(this.x - other.getX(), this.y - other.getY());
    }
    /**
     * @param other
     * @return cross points
     */
    public double cross(Point other) {
        return this.x * other.getY() - this.y * other.getX();
    }
    /**
     * @param line
     * @return distance from given line
     */
    public double distanceToLine(Line line) {
        double vx = line.end().getX() - line.start().getX();
        double vy = line.end().getY() - line.start().getY();
        double wx = this.getX() - line.start().getX();
        double wy = this.getY() - line.start().getY();
        double dotProduct = vx * wx + vy * wy;
        double lengthSquared = vx * vx + vy * vy;
        double t = Math.max(0, Math.min(1, dotProduct / lengthSquared));
        double projectionX = line.start().getX() + t * vx;
        double projectionY = line.start().getY() + t * vy;
        return Math.sqrt(Math.pow(this.getX() - projectionX, 2) + Math.pow(this.getY() - projectionY, 2));
    }
}
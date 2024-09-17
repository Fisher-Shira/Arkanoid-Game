/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package geometry_primitives;

import java.util.List;

/** .
 * The main class of Line
 * Have start and end point
 */
public class Line {
    private Point start;
    private Point end;
    private static double threshold = 0.0001;
    /**
     * @param start point
     * @param end point
     * constructor - build Line getting 2 points
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * @param x1 of first point
     * @param y1 of first point
     * @param x2 of second point
     * @param y2 of second point
     * constructor - build Line getting 2 x's and 2 y's
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * @return the length of the Line
     */
    public double length() {
        return start.distance(end);
    }
    /**
     * @return the middle point of the Line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }
    /**
     * @return the start point of the Line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }
    /**
     * @return the end point of the Line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }
    /**
     * @param leftUp position in matrix
     * @param leftDown position in matrix
     * @param rightUp position in matrix
     * @param rightDown position in matrix
     * @return the determinant of the matrix
     */
    public double determinant(double leftUp, double leftDown, double rightUp, double rightDown) {
        return leftUp * rightDown - leftDown * rightUp;
    }
    /**
     * @param other line
     * @return if lines equals
     */
    public boolean equals(Line other) {
        return this.start == other.start && this.end == other.end;
    }
    /**
     * @param other line
     * @return if lines collinear
     */
    public boolean collinear(Line other) {
        return other.start.minus(this.start).cross(this.end.minus(this.start)) == 0
                && other.start.minus(this.start).cross(this.end.minus(this.start)) == 0;
    }
    /**
     * @param other line
     * @return if Line intersect with other line
     */
    public boolean isIntersecting(Line other) {
        return this.collinear(other) || this.intersectionWith(other) != null;
    }
    /**
     * @param other1 line
     * @param other2 line
     * @return if Line intersect with other 2 lines and if other 2 lines intersect
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2) && other1.isIntersecting(other2);
    }
    /**
     * @param other line
     * @return the intersection point if the lines intersect, null otherwise
     * Check intersection by finding the equasion of the lines
     */
    public Point intersectionWith(Line other) {
        double firstY = 1;
        double firstM = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double firstC = this.start.getY() - (firstM * this.start.getX());
        double secondY = 1;
        double secondM = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        double secondC = other.start.getY() - (secondM * other.start.getX());
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.start.equals(other.start)) {
                return this.start;
            }
            return null;
        }
        if (this.start.equals(this.end)) {
            if (this.start.distance(other.start) + this.start.distance(other.end) == other.start.distance(other.end)) {
                return this.start;
            }
            return null;
        }
        if (other.start.equals(other.end)) {
            if (other.start.distance(this.start) + other.start.distance(this.end) == this.start.distance(this.end)) {
                return other.start;
            }
            return null;
        }
        if (this.start.getX() - this.end.getX() == 0) {
            firstM = -1;
            firstC = this.start.getX();
            firstY = 0;
        }
        if (other.start.getX() - other.end.getX() == 0) {
            secondM = -1;
            secondC = other.start.getX();
            secondY = 0;
        }
        double d = determinant(-firstM, -secondM, firstY, secondY);
        double dX = determinant(firstC, secondC, firstY, secondY);
        double dY = determinant(-firstM, -secondM, firstC, secondC);
        if (d != 0
            && dX / d >= Math.min(this.start.getX(), this.end.getX()) - threshold
            && dX / d <= Math.max(this.start.getX(), this.end.getX()) + threshold
            && dY / d >= Math.min(this.start.getY(), this.end.getY()) - threshold
            && dY / d <= Math.max(this.start.getY(), this.end.getY()) + threshold
            && dX / d >= Math.min(other.start.getX(), other.end.getX()) - threshold
            && dX / d <= Math.max(other.start.getX(), other.end.getX()) + threshold
            && dY / d >= Math.min(other.start.getY(), other.end.getY()) - threshold
            && dY / d <= Math.max(other.start.getY(), other.end.getY()) + threshold
            ) {
            return new Point(dX / d, dY / d);
        }
        return null;
    }
    /**
     * @param rect Rectangle to check intersection with
     * @return the closest (possibly empty) intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectP = rect.intersectionPoints(new Line(this.start, this.end));
        if (intersectP.isEmpty()) {
            return null;
        }
        Point closestP = intersectP.get(0);
        double distanceP = this.start.distance(closestP);
        for (Point p: intersectP) {
            if (this.start.distance(p) < distanceP) {
                distanceP = this.start.distance(p);
                closestP = p;
            }
        }
        return closestP;
    }
}
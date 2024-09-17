/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package geometry_primitives;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/** .
 * The main class of Rectangle
 * Have upperLeft point, width, height and lines
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line upperLine;
    private Line downLine;
    private Line leftLine;
    private Line rightLine;
    private static double threshold = 0.0001;
    /**
     * @param upperLeft point
     * @param width
     * @param height
     * constructor - Create a new rectangle with location and width/height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.upperLine = new Line(upperLeft, upperLeft.add(width, 0));
        this.downLine = new Line(upperLeft.add(0, height), upperLeft.add(width, height));
        this.leftLine = new Line(upperLeft, upperLeft.add(0, height));
        this.rightLine = new Line(upperLeft.add(width, 0), upperLeft.add(width, height));
    };
    /**
     * @param x value of upperLeft point
     * @param y value of upperLeft point
     * @param width
     * @param height
     * constructor - Create a new rectangle with location and width/height
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    };
    /**
     * @return the width of the Rectangle
     */
    public double getWidth() {
        return this.width;
    };
    /**
     * @return the height of the Rectangle
     */
    public double getHeight() {
        return this.height;
    };
    /**
     * @return the upper-left point of the Rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    };
    /**
     * @param point new upper-left
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
        this.upperLine = new Line(upperLeft, upperLeft.add(width, 0));
        this.downLine = new Line(upperLeft.add(0, height), upperLeft.add(width, height));
        this.leftLine = new Line(upperLeft, upperLeft.add(0, height));
        this.rightLine = new Line(upperLeft.add(width, 0), upperLeft.add(width, height));
    };
    /**
     * @param line to check intersection with
     * @return (possibly empty) List of intersection points
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectP = new ArrayList<>();
        Point p = line.intersectionWith(this.upperLine);
        if (p != null) {
            intersectP.add(upperLine.intersectionWith(line));
        }
        p = line.intersectionWith(this.downLine);
        if (p != null) {
            intersectP.add(downLine.intersectionWith(line));
        }
        p = line.intersectionWith(this.leftLine);
        if (p != null) {
            intersectP.add(leftLine.intersectionWith(line));
        }
        p = line.intersectionWith(this.rightLine);
        if (p != null) {
            intersectP.add(rightLine.intersectionWith(line));
        }
        return intersectP;
    };
    /**
     * @param closestP intersection point
     * @return (possibly empty) up, down, left or right
     */
    public String intersectionLineType(Point closestP) {
        if (closestP.distanceToLine(this.upperLine) <= threshold) {
            return "up";
        }
        if (closestP.distanceToLine(this.downLine) <= threshold) {
            return "down";
        }
        if (closestP.distanceToLine(this.leftLine) <= threshold) {
            return "left";
        }
        if (closestP.distanceToLine(this.rightLine) <= threshold) {
            return "right";
        }
        return null;
    };
    /**
     * @param g Graphics to draw the rectangle on
     * @param color to draw the rectangle in
     */
    public void drawOn(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(),
                    (int) this.width,
                    (int) this.height);
        g.setColor(Color.BLACK);
        g.drawRect((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(),
                    (int) this.width,
                    (int) this.height);
    };
}

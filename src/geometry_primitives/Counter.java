/**************
 * Shira Fisher
 * Computer Science Student
 **************/

package geometry_primitives;

/** .
 * The main class of Counter
 */
public class Counter {
    private int counter;
    /** .
     * Create new Counter
     */
    public Counter() {
        this.counter = 0;
    };
    /**
     * @param number to add to current count
     */
    public void increase(int number) {
        this.counter += number;
    };
    /**
     * @param number to subtract from current count
     */
    // subtract number from current count.
    public void decrease(int number) {
        this.counter -= number;
    };
    /**
     * @return current count
     */
    public int getValue() {
        return counter;
    };
}
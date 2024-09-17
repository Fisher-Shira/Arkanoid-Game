/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import sprite_settings.Sprite;
import sprite_settings.SpriteCollection;

/** .
 * The main class of Game
 * Have Sprite collection, game environment and gui
 */
public class Game extends JPanel implements ActionListener, KeyListener {
    private final Timer timer;
    private Paddle paddle;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    /** .
     * constructor - build Game
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        setBackground(Color.CYAN);
        setFocusable(true);
        this.addKeyListenerToGame();
        this.timer = new Timer(10, this);
        this.timer.start();
    };
    /** .
     * Add key listener to game
     */
    private void addKeyListenerToGame() {
        addKeyListener(this);
    };
    /**
     * @return env
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    };
    /**
     * @param p paddle to set
     */
    public void setPaddle(Paddle p) {
        this.paddle = p;
    };
    /**
     * @param c new collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    };
    /**
     * @param c collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    };
    /**
     * @param s new sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    };
    /**
     * @param s sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    };
    /**
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.paddle.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.paddle.moveRight();
        }
    };
    /**
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e) { };
    /**
     * @param e key event
     */
    @Override
    public void keyTyped(KeyEvent e) { };
    /**
     * @param g graphic
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sprites.drawAllOn(g);
    };
    /**
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.sprites.notifyAllTimePassed();
        repaint();
    };
}


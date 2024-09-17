/**************
 * Shira Fisher
 * Computer Science Student
 **************/

import geometry_primitives.Counter;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/** .
 * The main class of Ass3Game
 * Draw Arkanoid Game
 */
public class ArkanoidGame {
    /**
     * @param args
     * Initialize a new game: create the Blocks, Ball and Paddle and add them to the game
     */
    public static void main(String[] args) {
        int width = 815, height = 635, closeBarHeight = 40, closeBarWidth = 35,
            extraBorder = 30, shownBorder = 20, blockHeight = 25, blockWidth = 50;
        Counter ballCounter = new Counter(), blockCounter = new Counter(), scoreCounter = new Counter();
        ArrayList<Color> colors = new ArrayList<>(
            Arrays.asList(Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN));
        ArrayList<Ball> balls = new ArrayList<>();
        Game game = new Game();
        JFrame frame = new JFrame("Arkanoid Game");
        frame.add(game);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add BallRemover, BlockRemover and Counters
        BallRemover ballRemover = new BallRemover(game, ballCounter);
        BlockRemover blockRemover = new BlockRemover(game, blockCounter);
        // Add Balls
        Ball ball = new Ball(new Point(400, 450), 20, Color.WHITE, game.getEnvironment(), new Velocity(3, -3));
        balls.add(ball);
        ball = new Ball(new Point(400, 450), 20, Color.WHITE, game.getEnvironment(), new Velocity(-4, -3));
        balls.add(ball);
        for (Ball currentBall : balls) {
            currentBall.addToGame(game);
            ballCounter.increase(1);
        }
        // Add Down Border
        Block b = new Block(-extraBorder, height - shownBorder - closeBarHeight, width + 2 * extraBorder,
                            extraBorder + shownBorder); //down
        b.addToGame(game);
        b.addHitListener(ballRemover);
        // Add Paddle
        Paddle paddle = new Paddle(shownBorder, width - shownBorder, height - shownBorder - closeBarHeight);
        paddle.addToGame(game);
        game.setPaddle(paddle);
        // Add Borders
        b = new Block(-extraBorder, -extraBorder, extraBorder + shownBorder, height + 2 * extraBorder); //left
        b.addToGame(game);
        b = new Block(width - closeBarWidth, -extraBorder, extraBorder + shownBorder, height + 2 * extraBorder); //right
        b.addToGame(game);
        b = new Block(-extraBorder, -extraBorder, width + 2 * extraBorder, extraBorder + shownBorder); //up
        b.addToGame(game);
        // Add Score
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreListener, new Rectangle(0, 0, width, shownBorder));
        game.addSprite(scoreIndicator);
        // Add Blocks
        for (int row = 1; row <= 6; row++) {
            int blockY = 100 + blockHeight * row;
            for (int block = row; block <= 12; block++) {
                int blockX = 130 + block * blockWidth;
                b = new Block(blockX, blockY, blockWidth, blockHeight, colors.get(row - 1));
                b.addToGame(game);
                b.addHitListener(blockRemover);
                b.addHitListener(scoreListener);
                for (Ball currentBall : balls) {
                    b.addHitListener(currentBall);
                }
                blockCounter.increase(1);
            }
        }
    };
}
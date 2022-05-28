package A2;
//@name Vicki Young
//@date/version 2022.03.31
//CS245 Assignment02: Roll Bounce

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

public class RollBounce extends JPanel implements ActionListener {

    protected Timer tm;
    protected int gravity;
    protected int friction;
    protected List<RollBall> list;
    protected int minSpeed;
    protected int maxSpeed;
    protected int timerDelay;
    protected int balls;
    //NOTE: windowAdjustment variable is to keep balls visible within given window dimensions
    protected int windowAdjustment;
    protected int windowHeight;
    protected int windowWidth;
    protected int ballRadius;

    /**
     * RollBounce constructor to get key-values from property file.
     * With given values information, creates balls and adds them to list.
     * @param propertyFileName property file to read key-values from
     */
    public RollBounce (String propertyFileName) {
        windowAdjustment = 75;
        Properties prop = new Properties();
        try {
            //create classLoader
            ClassLoader classLoader = RollBounce.class.getClassLoader();
            //check that property file exists
            URL res = Objects.requireNonNull(classLoader.getResource(propertyFileName), "Can't find configuration file RollBounce.prop");
            InputStream is = new FileInputStream(res.getFile());
            //load property file
            prop.load(is);

            //get corresponding values stored in property file
            gravity = Integer.parseInt(prop.getProperty("gravity"));
            friction = Integer.parseInt(prop.getProperty("friction"));
            if (prop.getProperty("list").equals("arraylist")) {
                list = new ArrayList<>();
            } else {
                list = new LinkedList<>();
            }
            minSpeed = Integer.parseInt(prop.getProperty("minspeed"));
            maxSpeed = Integer.parseInt(prop.getProperty("maxspeed"));
            timerDelay = Integer.parseInt(prop.getProperty("timerDelay"));
            balls = Integer.parseInt(prop.getProperty("balls"));
            windowHeight = Integer.parseInt(prop.getProperty("window_height")) - windowAdjustment;
            windowWidth = Integer.parseInt(prop.getProperty("window_width")) - windowAdjustment;
            ballRadius = Integer.parseInt(prop.getProperty("ball_radius"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tm = new Timer(timerDelay, this);

        //creates ball with random (x,y) coordinates, random speed, random color, random direction
        //adds balls to list
        Random random = new Random();
        for (int i = 0; i < balls; i++) {
            int x = random.nextInt(windowWidth + 1);
            int y = random.nextInt(windowHeight + 1);
            int hSpeed = random.nextInt(maxSpeed + 1) + minSpeed;
            RollBall ball = new RollBall(x, y, hSpeed);
            ball.setColor(chooseColor());
            ball.setDirection(chooseDirection());
            list.add(ball);
        }
        //FOR TESTING: testing ball with zero horizontal velocity (up and down bounce)
        //RollBall ball = list.get(0);
        //ball.setHSpeed(0);
    }

    /**
     * chooseColor() function chooses a random pastel color and returns it.
     * pastel colors from: https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
     * @return Color random pastel color
     */
    public Color chooseColor() {
        Random random = new Random();
        float hue = random.nextFloat();
        // Saturation between 0.1 and 0.3
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.9f;
        return Color.getHSBColor(hue, saturation, luminance);
    }

    /**
     * chooseDirection() function chooses a random direction (left or right) for a ball.
     * @return int random direction (-1 = left or 1 = right)
     */
    public int chooseDirection() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * paintComponent() function paints each ball in list using its respective color, coordinates
     * and given ballRadius.
     * @param g Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.
        //paints each ball
        for (int i = 0; i < balls; i++) {
            RollBall ball = list.get(i);
            g.setColor(ball.getColor());
            g.fillOval(ball.getX(), ball.getY(), ballRadius, ballRadius);
        }
        // Recommend you leave the next line as is
        tm.start();
    }

    /**
     * actionPerformed() function animates the movement of each ball.
     * Uses ball respective coordinates, direction, and horizontal velocity.
     * Also uses gravity, friction, windowHeight and windowWidth.
     * @param actionEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //for loop to control movement of each ball
        for (int i = 0; i < balls; i++) {
            RollBall ball = list.get(i);
            //ball vertical velocity increases by gravity increment
            ball.setVSpeed(ball.getVSpeed() + gravity);
            //ball x-coordinate changes by horizontal velocity in given direction
            ball.setX(ball.getX() + (ball.getDirection() * ball.getHSpeed()));
            //ball y-coordinate changes by vertical velocity
            ball.setY(ball.getY() + ball.getVSpeed());

            //FOR TESTING: ball coordinates and velocity
            //System.out.println(i + " : (" + ball.getX() + ", " + ball.getY() + ")");
            //System.out.println(i + " H&V velocity: " + ball.getHSpeed() + " " + ball.getVSpeed());

            //if ball reaches left or right side of window, bounces off wall and goes in other direction
            if (ball.getX() <= 0 || ball.getX() >= windowWidth) {
                ball.setDirection(ball.getDirection() * -1);
            }
            //if ball reaches the bottom of the canvas, it bounces off the bottom and heads in the
            //opposite vertical direction; “up” with the same velocity
            //also stops ball from moving at the bottom if vertical velocity < 0
            if (ball.getY() >= windowHeight) {
                ball.setY(windowHeight);
                ball.setHSpeed(ball.getHSpeed() - friction);
                ball.setVSpeed(-1 * (ball.getVSpeed() - friction));
            }
            //if ball horizontal velocity goes below 0 (but NOT if HSpeed = 0)
            //ball is stopped at bottom of canvas and no longer moves
            if (ball.getHSpeed() < 0) {
                ball.setHSpeed(0);
                ball.setY(windowHeight);
            }
        }
        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        RollBounce rb = new RollBounce(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Roll Bounce");
        jf.setSize(rb.windowWidth + rb.windowAdjustment, rb.windowHeight + rb.windowAdjustment);
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


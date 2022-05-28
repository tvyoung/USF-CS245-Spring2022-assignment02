package A2;
//@name Vicki Young
//@date/version 2022.03.31
//CS245 Assignment02: Roll Bounce

import java.awt.*;

public class RollBall {
    Color color;
    int x;
    int y;
    int direction;
    int horizontalSpeed;
    int verticalSpeed;

    /**
     * RollBall() constructor, initializes ball with given coordinates and horizontal velocity.
     * Also initializes direction to 1 (right) and vertical velocity to 0.
     * @param givenX initial x-coordinate for ball
     * @param givenY initial y-coordinate for ball
     * @param hSpeed initial horizontal velocity for ball
     */
    public RollBall(int givenX, int givenY, int hSpeed) {
        x = givenX;
        y = givenY;
        direction = 1;
        horizontalSpeed = hSpeed;
        verticalSpeed = 0;
    }

    /**
     * setColor() function sets ball to a given color.
     * @param newColor new given color
     */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /**
     * getColor() function returns ball color.
     * @return Color ball color
     */
    public Color getColor() {
        return color;
    }

    /**
     * setX() function sets ball to a given x-coordinate.
     * @param newX new x-coordinate
     */
    public void setX(int newX) {
        x = newX;
    }

    /**
     * getX() function returns ball x-coordinate.
     * @return int ball x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * setY() function sets ball to a given y-coordinate.
     * @param newY new y-coordinate
     */
    public void setY(int newY) {
        y = newY;
    }

    /**
     * getY() function returns ball y-coordinate.
     * @return int ball y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * setDirection() function sets the ball direction to left or right.
     * @param point new ball direction (should be -1 = left or 1 = right).
     */
    public void setDirection(int point) {
        direction = point;
    }

    /**
     * getDirection() function returns ball direction (-1 = left or 1 = right).
     * @return int ball direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * setHSpeed() function sets the ball horizontal velocity.
     * @param speed new horizontal speed
     */
    public void setHSpeed(int speed) {
        horizontalSpeed = speed;
    }

    /**
     * getHSpeed() function returns the ball horizontal velocity.
     * @return int ball horizontal speed
     */
    public int getHSpeed() {
        return horizontalSpeed;
    }

    /**
     * setVSpeed() function sets the ball vertical velocity.
     * @param speed new ball vertical speed
     */
    public void setVSpeed(int speed) {
        verticalSpeed = speed;
    }

    /**
     * getVSpeed() function returns the ball vertical velocity.
     * @return int ball vertical speed
     */
    public int getVSpeed() {
        return verticalSpeed;
    }
}

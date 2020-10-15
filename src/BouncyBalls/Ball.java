package BouncyBalls;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private Double bounce;
    Circle ball;
    private Double dy;
    private Double dx;

    public Ball(Circle circle, Double deltaY, Double deltaX) {
        ball = circle;
        bounce = 0.7;
        dy = deltaY;
        dx = deltaX;
    }


    public Double getDy() {
        return dy;
    }

    public void setDy(Double dY) {
        this.dy = dY;
    }

    public Double getDx() {
        return dx;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }

    public Double getBounce() {
        return bounce;
    }

    public void setBounce(Double d) {
        bounce = d;
    }

    private boolean atRight(Bounds b) {
        if (ball.getLayoutX() >= (b.getMaxX() - ball.getRadius())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean atLeft(Bounds b) {
        if (ball.getLayoutX() <= (b.getMinX() + ball.getRadius())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean atBottom(Bounds b) {
        if (ball.getLayoutY()  >= (b.getMaxY() - ball.getRadius())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean atTop(Bounds b) {
        if (ball.getLayoutY() <= (b.getMinY() + ball.getRadius())) {
            return true;
        } else {
            return false;
        }
    }


    public void validMove(Bounds b) {
        Double posY = ball.getLayoutY() + dy;
        Double posX = ball.getLayoutX() + dx;
        if (posX < b.getMinX()) {
            ball.setLayoutX(b.getMinX() + ball.getRadius());
        } else if (posX > b.getMaxX()) {
            ball.setLayoutX(b.getMaxX() - ball.getRadius());
        } else {
            ball.setLayoutX(posX);
        }


        if (posY < b.getMinY()) {
            ball.setLayoutY(b.getMinY() + ball.getRadius());
        } else if (posY > b.getMaxY()) {
            ball.setLayoutY(b.getMaxY() - ball.getRadius());
        } else {
            ball.setLayoutY(posY);
        }

    }

    public void move(Bounds b, Double gravity) {

        validMove(b);

        if (atRight(b) || atLeft(b)) {
            dx = dx * -1 * bounce;
        }

        if (atBottom(b) || atTop(b)) {
            if (dy <= 0.2) {
                dy = 0.0;
                dx = dx * 0.99;

            } else {
                dy = dy * -1 * bounce;
            }

        } else {
            dy = dy + gravity;
        }

        if (ball.getLayoutY() < 15) {
            if (dy == 0.0) {
                dy = 3.0;
            }
        }


    }
}

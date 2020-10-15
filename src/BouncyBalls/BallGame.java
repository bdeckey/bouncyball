package BouncyBalls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;



public class BallGame {



    private Pane canvas;
    private Double gravity = 0.8;
    public ArrayList<Ball> balls;


    public BallGame(Pane pane) {

        canvas = pane;
        final Scene scene = new Scene(canvas, 800, 600);

        setGame(10);

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(20), new TimeHandler()));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    private void setGame(int numBalls) {
        balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) {

            int radius = (int)(Math.random() * 10) + 5;
            Double dx = ((Math.random() * 8) - 4);
            Double dy = ((Math.random() * 6) + 2);
            Ball temp = new Ball(new Circle(radius, Color.BLUE), dx, dy);
            int x = (int)(Math.random() * 300) + 30;
            int y = 50;
            temp.ball.relocate(x,y);
            canvas.getChildren().addAll(temp.ball);
            temp.ball.setOnMouseEntered(new MouseHandler(temp));

            balls.add(temp);
        }
    }

    public int addBall() {
        if (balls.size() == 20) {
            return 20;
        }
        int radius = (int)(Math.random() * 10) + 5;
        Double dx = ((Math.random() * 8) - 4);
        Double dy = ((Math.random() * 6) + 2);
        Ball temp = new Ball(new Circle(radius, Color.BLUE), dx, dy);
        int x = (int)(Math.random() * 300) + 30;
        int y = 50;
        temp.ball.relocate(x,y);
        canvas.getChildren().addAll(temp.ball);
        temp.ball.setOnMouseEntered(new MouseHandler(temp));

        balls.add(temp);
        return balls.size();

    }

    public int removeBall() {
        if (balls.size() == 1) {
            return 1;
        }
        Ball toBeRemove = balls.get(0);
        canvas.getChildren().remove(toBeRemove.ball);
        balls.remove(0);
        return balls.size();
    }

    public Double changeGravity(Double d) {
        gravity += d;
        return gravity;
    }

    public Double changeBounce(Double d) {
        for(Ball b: balls) {
            b.setBounce(b.getBounce() + d);
        }
        return balls.get(0).getBounce();
    }

    public void handleCollision(Ball a, Ball b) {
        Double ady = a.getDy();
        Double adx = a.getDx();

        Double bdy = b.getDy();
        Double bdx = b.getDx();

        a.setDx(bdx  * 1.05);
        a.setDy(bdy  * 1.05);

        b.setDx(adx  * 1.05);
        b.setDy(ady  * 1.05);

        Bounds bounds = canvas.getLayoutBounds();

        a.validMove(bounds);
        b.validMove(bounds);


    }


    private class TimeHandler implements EventHandler<ActionEvent> {


        public TimeHandler() {

        }

        @Override
        public void handle(ActionEvent e) {

            final Bounds bounds = canvas.getLayoutBounds();

            for (Ball b: balls) {
                  b.move(bounds, gravity);
            }
            for (int i = 0; i < balls.size() - 1; i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    Shape inter = Shape.intersect(balls.get(i).ball, balls.get(j).ball);
                    if (inter.getBoundsInLocal().getWidth() != -1) {
                        handleCollision(balls.get(i), balls.get(j));

                    }

                }
            }
        }

    }

    private class MouseHandler implements EventHandler<MouseEvent> {

        private Ball temp;

        MouseHandler(Ball t) {
            temp = t;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            Double randDy = (Math.random() * 10) - 25.00;
            Double randDx = (Math.random() * 10) - 5.00;
            Color rand = Color.color(Math.random(), Math.random(),Math.random());
            temp.ball.setFill(rand);
            if (temp.ball.getLayoutY() > 300) {
                temp.setDy(randDy);
            } else {
                temp.setDy(-randDy);
            }

            temp.setDx(randDx);
        }
    }

}

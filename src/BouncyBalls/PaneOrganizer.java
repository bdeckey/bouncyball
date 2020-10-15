package BouncyBalls;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javax.swing.*;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

/**
 * This PaneOrganizer class contains the root of the program, which is a border
 * pane that holds the exit button and instructions along with the game itself.
 * The pane organizer contains methods that set up the buttons and also sets out
 * the instructions.
 */
public class PaneOrganizer {

    private BorderPane _root;
    private BallGame game;
    private Label numBalls;
    private Label curGravity;
    private Label bounciness;

    /*
     * The constructor contains instances of the DoodleGame class which holds
     * all the game mechanics, and also calls on a create button pane method.
     */
    public PaneOrganizer() {
        _root = new BorderPane();
        Pane BouncyPane = new Pane();
        game = new BallGame(BouncyPane);
        _root.setCenter(BouncyPane);
        this.createButtonPane();
    }

    /**
     * This method is an accessor for the App class to get the root.
     */
    public Pane getRoot() {
        return _root;
    }

    /**
     * Creates the exit button and labels which explain the game to the user.
     * Also adds the nodes to the main pane.
     */
    private void createButtonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setPrefHeight(50);
        buttonPane.setStyle("-fx-background-color: peru");



        Button removeBall = new Button("-");
        removeBall.setOnAction(new RemoveBallHandler());
        removeBall.setFocusTraversable(false);
        buttonPane.getChildren().add(removeBall);

        numBalls = new Label("  Balls: 10  ");
        numBalls.setFont(new Font(20));
        buttonPane.getChildren().add(numBalls);

        Button addBall = new Button("+");
        addBall.setOnAction(new AddBallHandler());
        addBall.setFocusTraversable(false);
        buttonPane.getChildren().add(addBall);

        Label space = new Label("       ");
        space.setFont(new Font(10));
        buttonPane.getChildren().add(space);


        Button lowerGravity = new Button("-");
        lowerGravity.setOnAction(new LowerGravityHandler());
        lowerGravity.setFocusTraversable(false);
        buttonPane.getChildren().add(lowerGravity);

        curGravity = new Label("  Gravity: 0.8  ");
        curGravity.setFont(new Font(20));
        buttonPane.getChildren().add(curGravity);

        Button addGravity = new Button("+");
        addGravity.setOnAction(new RaiseGravityHandler());
        addGravity.setFocusTraversable(false);
        buttonPane.getChildren().add(addGravity);

        Label space1 = new Label("       ");
        space1.setFont(new Font(10));
        buttonPane.getChildren().add(space1);

        Button lowerBounciness = new Button("-");
        lowerBounciness.setOnAction(new LowerBounceHandler());
        lowerBounciness.setFocusTraversable(false);
        buttonPane.getChildren().add(lowerBounciness);

        bounciness = new Label("  Bounciness: 0.7  ");
        bounciness.setFont(new Font(20));
        buttonPane.getChildren().add(bounciness);

        Button addBounciness = new Button("+");
        addBounciness.setOnAction(new RaiseBounceHandler());
        addBounciness.setFocusTraversable(false);
        buttonPane.getChildren().add(addBounciness);
        



        buttonPane.setAlignment(Pos.CENTER);
        _root.setBottom(buttonPane);

    }

    public String truncateDecimal(Double val) {
        return new DecimalFormat("#.#").format(val);
    }

    /**
     * The Event Handler that makes the quit button exit the application!
     */
    private class QuitButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    private class AddBallHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            int nBalls = game.addBall();
            numBalls.setText("  Balls: " + Integer.toString(nBalls) + "  ");
        }
    }

    private class RemoveBallHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            int nBalls = game.removeBall();
            numBalls.setText("  Balls: " + Integer.toString(nBalls) + "  ");
        }
    }

    private class RaiseGravityHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Double cGrav = game.changeGravity(0.1);
            curGravity.setText("  Gravity: " + truncateDecimal(cGrav) + "  ");
        }
    }

    private class LowerGravityHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            Double cGrav = game.changeGravity(-0.1);
            curGravity.setText("  Gravity: " + truncateDecimal(cGrav) + "  ");
        }
    }

    private class RaiseBounceHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            Double cB = game.changeBounce(0.1);
            bounciness.setText("  Bouciness: " + truncateDecimal(cB) + "  ");
        }
    }

    private class LowerBounceHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            Double cB = game.changeBounce(-0.1);
            bounciness.setText("  Bouciness: " + truncateDecimal(cB) + "  ");
        }
    }

}
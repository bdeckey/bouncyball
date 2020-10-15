// In App.java
package BouncyBalls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This application is a copy of the famous Doodle Jump game. The app is
 * organized in four main classes: a PainOrganizer class, a DoodleGame class, a
 * Platform class, and a Doodle class. The Platform and Doodle classes contain
 * the specification for their respective shapes and stores the pertinent data
 * of the class. The DoodleGame class contains and organizes the shapes and has
 * the game mechanics organized into a time line. The DoodleGame also contains a
 * arraylist which stores the platforms and effectively creates and deletes new
 * and old platforms as the game runs.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Bouncy Balls");
        stage.show();
    }

    public static void main(String[] argv) {
        // launch is a static method inherited from Application.
        launch(argv);
    }
}

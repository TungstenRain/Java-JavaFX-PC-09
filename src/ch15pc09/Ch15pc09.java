package ch15pc09;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Digital Picture Frame to display 'n' number of pictures (n between 1 and maxValue)
 * Pictures to cycle through will need be jpg files numbered 1 through maxValue
 * (e.g., 5.jpg) in a folder called pictures.
 * @author frank
 */
public class Ch15pc09 extends Application {
    // Variables
    private int index = 0;
    private final int maxValue = 10;   // Variable to adjust
    
    /**
     * Method to start the application
     * @param primaryStage Stage The stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        // Set the title of the application
        primaryStage.setTitle("Digital Picture Frame");
        
        // Instantiate List of images
        final List<Image> imageList = new ArrayList<>();
        
        // Import the pictures
        for (int i = 0; i < maxValue; i++) {
            try {
                String file = "file:pictures/" + Integer.toString(i + 1) + ".jpg";
                imageList.add(new Image(file));
            } catch (Exception e){
                System.out.print(e.getStackTrace());
            }
        }
        
        // Instantiate StackPane and ImageView
        StackPane stackPane = new StackPane();
        ImageView imageView = new ImageView();
        imageView.setImage(imageList.get(index));
        
        // EventHandler to handle transition
        EventHandler<ActionEvent> handler = event -> {
            imageView.setImage(imageList.get(index++));
            if (index >= maxValue) {
                index = 0;
            }
        };
        
        // Instantiate the KeyFrame
        Duration duration = new Duration(4000);
        KeyFrame keyFrame = new KeyFrame(duration, handler);
        
        // Instantiate the Timeline animation
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        // Add the ImageView to the StackPane
        stackPane.getChildren().add(imageView);
        
        // Instantiate the Scene
        Scene scene = new Scene(stackPane, 500, 300);
        
        // Set and display the Stage and start the Timeline
        primaryStage.setScene(scene);
        primaryStage.show();
        timeline.playFromStart();
    }

    /**
     * Main method to launch the program
     * @param args String[] The command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

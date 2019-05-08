package View;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    int nums = 0;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        FruitFactory fruitFactory = new FruitFactory();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        Group group = new Group();
        Scene scene = new Scene(group, bounds.getWidth(), bounds.getHeight());
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
        Image imagecur = new Image("Assets/blade.png");
    	scene.setCursor(new ImageCursor(imagecur));
        fruitFactory.setBounds(bounds);
        FruitInterfaceFactory strawberry = fruitFactory.getFruit("strawberry");
        FruitInterfaceFactory apple = fruitFactory.getFruit("apple");
        FruitInterfaceFactory grape = fruitFactory.getFruit("grape");
        Circle cursor = new Circle(1,1,1,Color.GREEN);

        strawberry.move();
        apple.move();
        grape.move();
        
        scene.setOnMouseDragged( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                cursor.setCenterX(event.getSceneX());
                cursor.setCenterY(event.getSceneY());
                
                if (cursor.getBoundsInParent().intersects(((Strawberry) strawberry).circle.getBoundsInParent())) {
                    ((Strawberry) strawberry).circle.setFill(new ImagePattern(new Image("Assets/sheep2.png")));
                    //group.getChildren().remove(strawberry.circle);
                }
                if (cursor.getBoundsInParent().intersects(((Apple) apple).circle.getBoundsInParent())) {
                    group.getChildren().remove(((Apple) apple).circle);
                }
                if (cursor.getBoundsInParent().intersects(((Grape) grape).circle.getBoundsInParent())) {
                    group.getChildren().remove(((Grape) grape).circle);
                }
              
            }
        });

        Line l = new Line(0, bounds.getMaxY(), bounds.getMaxX(), bounds.getMaxY());
        l.setStroke(Color.AQUA);
        group.getChildren().addAll(((Strawberry) strawberry).circle,((Apple) apple).circle,l,cursor,((Grape) grape).circle);
        Image image4 = new Image("Assets/background.png");
       // BackgroundImage backgroundImage = new BackgroundImage(image4);
        scene.setFill(new ImagePattern(image4));
        }

    public static void main(String[] args) {
        launch(args);
    }

}
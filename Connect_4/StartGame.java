package Connect_4;

import javafx.application.Application;
import javafx.stage.Stage;

public class StartGame extends Application {

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        firstMenu start = new firstMenu();
    }
}

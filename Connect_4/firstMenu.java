package Connect_4;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class firstMenu extends BorderPane {

    //get the width and height of the pane
    final int width = 565;
    final int height = 501;

    Stage menuStage;
    Text title;
    VBox button;

    public firstMenu(){
        //create the stage
        menuStage = new Stage();
        //create a title
        title = MenuTitle();
        //create buttons
        button = button();

        //center buttons
        button.setAlignment(Pos.CENTER);
        this.setCenter(button);

        //place title at top
        this.setAlignment(title, Pos.TOP_CENTER);
        this.setTop(title);

        //get a background
        this.getStylesheets().add("Style/Effects.css");
        this.getStyleClass().add("menuBackGround");

        //create a scene
        Scene scene = new Scene(this,width,height);

        //set the title
        menuStage.setTitle("Starting Screen");
        //set scene
        menuStage.setScene(scene);
        //don't allow the user tp resize the window
        menuStage.setResizable(false);
        //show stage
        menuStage.show();

    }

    //method which create a title
    public Text MenuTitle(){
        Text title = new Text(75,75,"Connect 4");
        title.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD,45));
        title.setFill(Color.BLUEVIOLET);
        title.setEffect(reflection());

        return title;
    }

    //method which adds a reflection to the title
    public Reflection reflection(){
        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOffset(-20);

        return reflection;
    }

    //method which adds a shadow to a Menu_Linked_List_Options
    public DropShadow ds(){
        DropShadow ds = new DropShadow();
        ds.setOffsetX(5.0);
        ds.setOffsetY(5.0);
        ds.setColor(Color.GRAY);
        return ds;
    }

    //method which creates buttons
    public VBox button(){

        VBox position = new VBox();

        Button play = new Button("Start");

        //add effects
        play.setEffect(ds());
        play.getStyleClass().add("start");

        Button quit = new Button("Quit");

        quit.setEffect(ds());
        quit.getStyleClass().add("exit");

        position.setSpacing(20);

        position.getChildren().addAll(play,quit);

        play.setOnAction(e ->{
            menuStage.close();
            Connect_Four start = new Connect_Four();
        });

        quit.setOnAction(e ->{
            System.exit(0);
        });

        return position;
    }
}

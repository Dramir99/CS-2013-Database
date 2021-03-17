package Connect_4;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Connect_Four extends BorderPane {
    //get the width and height of the Connect 4 pane
    private final int width = 490;
    private final int height = 774;
    //get a grid for te back-end of the circles
    int[][] coordinate = new int[6][7];

    Stage menuStage;
    Text title;
    HBox button;
    //check whose the player
    private int player;


    public Connect_Four(){

        //set player one to be One
        this.player = 1;
        //create a stage
        menuStage = new Stage();
        //create a title
        title = MenuTitle();
        //create the board game
        GridPane board = Create_Board();

        //make the game be in the center
        board.setAlignment(Pos.CENTER);
        this.setCenter(board);

        //set the title to be at the top
        this.setAlignment(title, Pos.TOP_CENTER);
        this.setTop(title);

        //get a background
        this.getStylesheets().add("Style/Effects.css");
        this.getStyleClass().add("Connect4BackGround");

        //create a scene using width and height
        Scene scene = new Scene(this,width,height);

        //dont allow the user to resize the window
        menuStage.setResizable(false);
        //set the title
        menuStage.setTitle("Play");
        //set te scene
        menuStage.setScene(scene);
        //show stage
        menuStage.show();
    }

    //method which create a title
    public Text MenuTitle(){
        Text title = new Text(75,75,"Connect 4");
        title.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD,45));
        title.setFill(Color.WHITESMOKE);
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
    public HBox button(){

        HBox position = new HBox();

        Button quit = new Button("Quit");

        quit.setEffect(ds());
        quit.getStyleClass().add("start");

        position.setSpacing(20);

        position.getChildren().addAll(quit);

        quit.setOnAction(e -> System.exit(0));

        return position;
    }

    //create a board game
    public GridPane Create_Board(){

        GridPane grid_Pane = new GridPane();

        //space the grid from the window
        Insets separateWindow = new Insets(10,10,10,10);

        //add padding for the circles
        grid_Pane.setPadding(separateWindow);

        //make the GridPane be center
        grid_Pane.setAlignment(Pos.CENTER);

        //set spacing for the node
        grid_Pane.setHgap(10);

        grid_Pane.setVgap(10);

        //create a grid using the circle class
        Pieces[][] connect4Board = new Pieces[6][7];

        for (int row = 0; row < coordinate.length; row++){
            for(int column = 0; column < coordinate[0].length; column++){

                //place an individual disc in an x and y coordinate system
                Pieces Coin = new Pieces(row+1,column+1);

                //set the values for the circle
                Coin.setRadius(20);
                Coin.setCenterX(20);
                Coin.setCenterY(20);
                Coin.setFill(Color.WHITE);
                Coin.setStroke(Color.BLUE);

                //add the circle into the grid first
                connect4Board[row][column] = Coin;

                //add the grid into the GridPane
                grid_Pane.add(Coin,column,row);

                //create a temp value to use in the lambda expression
                Pieces piecesCoin = Coin;

                Coin.setOnMouseClicked(e -> {

                    //get the column of the circles without it going out of bounds
                    int getcol = piecesCoin.getyCoordinate()-1;

                    //get the row using the method
                    int getRow = stackDiscs(getcol,coordinate);

                    //check to not allow the circles to change colors when its in the top row
                    if (coordinate[0][getcol] == 0){

                        //check who the player is
                        if (this.player == 1 ){

                            //update the grid from the 2-D int
                            coordinate[getRow][getcol] = 2;

                            //update the GUI to show the user
                            connect4Board[getRow][getcol].setFill(Color.YELLOW);

                            //change the player to be player two
                            this.player = 2;

                            //check if the user won
                            if(checkVerticalWin(getRow,coordinate,2)){
                                winScreen();
                            }

                        }

                        else{

                            //update the grid from the 2-D int
                            coordinate[getRow][getcol] = 1;

                            //update the GUI to show the user
                            connect4Board[getRow][getcol].setFill(Color.CADETBLUE);

                            //change the player to be player one
                            this.player = 1;

                            //check if the user won
                            if(checkVerticalWin(getRow,coordinate,1)){
                                winScreen();
                            }

                        }
                    }

                });

            }
        }

        //return the grid to the GUI
        return grid_Pane;

    }

    //allow the discs to stack on top
    public int stackDiscs(int  column, int[][] number){
        //return the column
        int val = 0;
        //get the length of the board
        int getLength = number.length - 1;
        //see if the space is available
        int free = 0;
        //check if the space is going to placed by player one or two
        int playerOne = 1;
        int playerTwo = 2;

        for (int i = 0; i < number.length; i++){

            if (i < number.length - 1){

                if (number[i + 1][column] == playerOne || number[i + 1][column] == playerTwo){

                    val = i;

                    break;
                }
            }
            else if(number[i][column] == free){

                val = i;

            }
        }
    //return the value
    return val;

    }

    //check if the user won vertically
    public boolean checkVerticalWin(int column, int[][] coordinate,  int playerTurn){

        //a counter
        int counter = 0;

        for (int i = 0; i < 6; i++ ){
            //check each player if the won
            if (coordinate[i][column] == playerTurn){
                //increase counter
                counter ++;

            }

            //check if someone won
            if (counter >= 4){
                return true;
            }


        }
        //return false if no one has won yet
        return false;

    }

    //method which shows a win screen
    public Stage winScreen(){
        Stage winPage = new Stage();

        BorderPane pane = new BorderPane();

        pane.getStylesheets().add("Style/Effects.css");
        pane.getStyleClass().add("winBackground");

        Text title = new Text(75,75,"Winner");
        title.setFont(Font.font("Lucida Handwriting", FontWeight.BOLD,45));
        title.setFill(Color.WHITE);
        title.setEffect(reflection());
        button = button();

        pane.setAlignment(title, Pos.CENTER);
        pane.setCenter(title);

        button.setAlignment(Pos.BOTTOM_CENTER);
        pane.setBottom(button);

        Scene scene = new Scene(pane,1280,720);

        winPage.setResizable(false);
        winPage.setTitle("Winner");
        winPage.setScene(scene);
        winPage.show();

        return winPage;

    }

}
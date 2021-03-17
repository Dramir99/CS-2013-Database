package Connect_4;

import javafx.scene.shape.Circle;

public class Pieces extends Circle {

    int xCoordinate;
    int yCoordinate;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public Pieces(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }


}

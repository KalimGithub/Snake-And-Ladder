package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {    //Player Class creation

    private String name;      //Attribute
    private int position;     //Attribute
    private Circle coin;      //Attribute

    private static Board gameBoard = new Board();  // gameBoard Instantiating with Board class

    public Player(String name, Color coinColor,int coinSize)  //Constructor of Player.While creating this coins will be placed in the home position
    {
        this.name =name;
        position=0;
        coin = new Circle(coinSize);
        coin.setFill(coinColor);
        movePlayer(1);
    }

    public void setstart()   //Method to get the coins to home position
    {
        position=0;
        movePlayer(1);
    }

    public void movePlayer(int diceValue)   //Method--Used to move the player coins
    {
        if(position+diceValue<=100)
        {
            position=position+diceValue;
            TranslateTransition firstMove =translatePlayer();  //first move for dice value position movement
            TranslateTransition secondMove=null;               //second move - If there is any ladder or snake bite movement
//            move.play();
//            coin.setTranslateX(gameBoard.getXCoord(position));
//            coin.setTranslateY(gameBoard.getYCoord(position));
            int newPos = gameBoard.getSnakebiteAndladder(position);
            if(newPos!=position)
            {
                position=newPos;
                secondMove=translatePlayer();
            }
            if(secondMove==null)
            {
                firstMove.play();   //Created animation will be played when we call .play() method.
            }
            else {
                SequentialTransition seq= new SequentialTransition(firstMove,new PauseTransition(Duration.millis(
                        500)),secondMove);    //When there are two moves like moving to position & climbing the ladder or snake bite as second move
                //this function is used.
                seq.play();
            }
        }
    }

    private TranslateTransition translatePlayer()      //Method - this is used to create an animation move to the new position (x,Y).But it will not be played until we call .play() function
    {
        TranslateTransition move = new TranslateTransition(Duration.millis(1000),coin);   //given time frame to move and the animation object as Circle Coin.
        move.setToX(gameBoard.getXCoord(position));  //gets the coordinates from pair arraylist
        move.setToY(gameBoard.getYCoord(position));
        move.setAutoReverse(false);
        return move;
    }

    public boolean checkWinner()   //Method -- It is used to check the if the game the completed
    {
        if(position==100)
            return true;
        else
            return false;
    }


    //Getter And Setter Methods -- Used to access the private values in another class

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Circle getCoin() {
        return coin;
    }

    public void setCoin(Circle coin) {
        this.coin = coin;
    }
}
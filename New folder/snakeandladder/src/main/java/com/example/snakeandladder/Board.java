package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {    //Board Class Creation

    private ArrayList<Pair<Integer,Integer>> positionCoordinates;   //Attribute Arraylist to store pair (X,Y) values -- It is similar to Hashmap(Key,Value) but with index search

    private ArrayList<Integer>ladderandSnakeBite;   //Attribute Arraylist at reaching points of - Ladder and Snakebites

    public Board()  //Constructor
    {
        populateCoordinates();
        setSnakeLadder();
    }

    private void populateCoordinates()  //Method  to add all the possible coordinates of the coin movements to
    //Arraylist pair (positionCoordinates ArrayList.
    {
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<>(0,0));

        for(int i=0;i<SnakeAndLadder.height;i++)
        {
            for(int j=0;j<SnakeAndLadder.width;j++)
            {
                int Xcord;
                if(i%2==0)
                {
                    Xcord = j*SnakeAndLadder.tileSize+SnakeAndLadder.tileSize/2;
                }
                else
                {
                    Xcord=SnakeAndLadder.tileSize*SnakeAndLadder.height-j*SnakeAndLadder.tileSize-SnakeAndLadder.tileSize/2;
                }
                int Ycord = SnakeAndLadder.tileSize*SnakeAndLadder.height-i*SnakeAndLadder.tileSize-SnakeAndLadder.tileSize/2;

                positionCoordinates.add(new Pair<>(Xcord,Ycord));

            }
        }


    }

    private void setSnakeLadder()    //Method to Add Arraylist at reaching points of - Ladder and Snakebites
    {
        ladderandSnakeBite=new ArrayList<>();

        for (int i = 0; i < 101; i++) {     //Inserting arraylist with consecutive numbers from 0 to 100
            ladderandSnakeBite.add(i);
        }

        ladderandSnakeBite.set(4,25);        //  .set(index,value) is used to replace the value at specified index
        ladderandSnakeBite.set(13,46);       //Here we are inserting the "Ladder and Snake Positions" into arraylist
        ladderandSnakeBite.set(27,5);
        ladderandSnakeBite.set(33,49);
        ladderandSnakeBite.set(40,3);
        ladderandSnakeBite.set(42,63);
        ladderandSnakeBite.set(43,18);
        ladderandSnakeBite.set(50,69);
        ladderandSnakeBite.set(54,31);
        ladderandSnakeBite.set(62,81);
        ladderandSnakeBite.set(66,45);
        ladderandSnakeBite.set(76,58);
        ladderandSnakeBite.set(89,53);
        ladderandSnakeBite.set(99,41);
    }

    public int getXCoord(int position)    //Method to get the values of X coordinates
    {
        if(position>0 && position<=100)
        {
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    public int getYCoord(int position)     //Method to get the values of Y coordinates
    {
        if(position>0 && position<=100)
        {
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }

    public int getSnakebiteAndladder(int position)   //Method to get the value of Ladder and Snake positions coordinates

    {
        return ladderandSnakeBite.get(position);
    }

    public static void main(String[] args)   //Main Method
    {

        Board board = new Board ();
        for (int i = 0; i < board.positionCoordinates.size(); i++) {

            System.out.println("X-"+board.positionCoordinates.get(i).getKey()+" Y-"+
                    board.positionCoordinates.get(i).getValue());

        }

//        System.out.println("Hello");


    }
}
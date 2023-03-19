package com.example.snakeandladder;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class SnakeAndLadder extends Application {      //Snake And Ladder Class
    public static final int tileSize=40,width=10,height=10,buttonline=tileSize*height+50,infoline=tileSize*height+30;  //Attributes

    Player player1st,player2nd;     //Attributes

    boolean firstplayerturn=true, gamestart=false;   //Attributes
    private int diceValue;     //Attributes


    private Pane createContent()    //Method  ---Creating Pane(UI) Inserting All the things related to UI.
    {
        Pane root = new Pane();
        root.setPrefSize(400,500);

        //100 tiles insertion

        for (int i = 0; i <height ; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().addAll(tile);    //Inserting into root instance of Pane Object
//                System.out.println(j*tileSize+","+i*tileSize);
            }
        }

        //Inserting image into the board

        Image img =new Image("C:\\Users\\kalim shaikh\\IdeaProjects\\snakeandladder\\src\\main\\resources\\download.jpeg");
        ImageView boardimg = new ImageView();
        boardimg.setImage(img);
        boardimg.setFitWidth(width*tileSize);
        boardimg.setFitHeight(height*tileSize);
        root.getChildren().addAll(boardimg);

        //Buttons Insertion

        Button startButton=new Button("START");
        startButton.setTranslateX(180);
        startButton.setTranslateY(buttonline);

        Button player1Button=new Button("PLAYER-1");
        player1Button.setTranslateX(10);
        player1Button.setTranslateY(buttonline);

        Button player2Button=new Button("PLAYER-2");
        player2Button.setTranslateX(320);
        player2Button.setTranslateY(buttonline);


        //Label(Comment) Insertion

        Label dicelabel = new Label("Start The Game");
        dicelabel.setTranslateX(165);
        dicelabel.setTranslateY(infoline);

        //Player Coins Creation by instantiating as Player1st and Player2nd

        player1st=new Player("Kiran", Color.BLACK,tileSize/2);
        player2nd=new Player("Ram", Color.WHITE,tileSize/2-5);

        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(firstplayerturn)
                    {
                        diceValue = rollDice();
                        dicelabel.setText("Dice Number "+diceValue);
                        player1st.movePlayer(diceValue);
                        firstplayerturn=!firstplayerturn;
                        if(player1st.checkWinner())
                        {
                            dicelabel.setText("Winner is "+player1st.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstplayerturn=true;
                            gamestart=false;
                        }
                    }
                }

            }
        });

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestart)
                {
                    if(!firstplayerturn)
                    {
                        diceValue = rollDice();
                        dicelabel.setText("Dice Number "+diceValue);
                        player2nd.movePlayer(diceValue);
                        firstplayerturn=!firstplayerturn;
                        if(player2nd.checkWinner())
                        {
                            dicelabel.setText("Winner is "+player2nd.getName());
                            startButton.setText("Restart");
                            startButton.setDisable(false);
                            firstplayerturn=true;
                            gamestart=false;
                        }
                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {    //Start Button
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestart=true;
                startButton.setDisable(true);
                player1st.setstart();   //to get the coins to home position
                player2nd.setstart();
            }
        });

        root.getChildren().addAll(startButton,player1Button,player2Button,dicelabel,player1st.getCoin(),player2nd.getCoin());


        return root;
    }

    private int rollDice()
    {
        return (int) (Math.random()*6+1);
    }


    @Override
    public void start(Stage stage) throws IOException {       //Predefined JAVA FX Method

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake And Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
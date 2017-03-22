package com.doun.chapter9interface;

import java.util.Random;

/**
 * Created by Doun on 2017/3/23.
 */

public interface MyGame {
    String play();
}
interface MyGameFactory {
    MyGame getGame();
}

class CoinGame implements MyGame {
    private Random rand = new Random(47);
    @Override
    public String play() {
        if (rand.nextInt(2)==1)
            return "CoinGame result zheng";
        else
            return "CoinGame result fan";
    }
}

class CoinGameFactory implements MyGameFactory {
    public MyGame getGame() { return new CoinGame(); }
}

class DiceGame implements MyGame {
    private Random rand = new Random(47);
    @Override
    public String play() {
        return "DiceGame result " + rand.nextInt(6);
    }
}

class DiceGameFactory implements MyGameFactory {
    public MyGame getGame() { return new DiceGame(); }
}


//
//
//
//public class Games {
//    public static void playGame(MyGameFactory factory) {
//        MyGame s = factory.getGame();
//        while(s.move())
//            ;
//    }
//    public static void main(String[] args) {
//        playGame(new CheckersFactory());
//        playGame(new ChessFactory());
//    }
//}
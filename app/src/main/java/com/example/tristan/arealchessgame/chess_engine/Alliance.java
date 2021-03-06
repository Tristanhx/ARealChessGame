package com.example.tristan.arealchessgame.chess_engine;

import com.example.tristan.arealchessgame.GameController;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.PlayerBlack;
import com.example.tristan.arealchessgame.chess_engine.player.PlayerWhite;

/**
 * Created by Tristan on 05/06/2017.
 */

public enum Alliance {
    WHITE {
        @Override
        public int getDir() {
            return -1;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public Player chooseNextPlayer(final PlayerWhite whitePlayer, final PlayerBlack blackPlayer) {
            GameController.currentPlayer = whitePlayer;
            return whitePlayer;
        }

        @Override
        public String toString(){
            return "W";
        }
    },
    BLACK {
        @Override
        public int getDir() {
            return 1;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public Player chooseNextPlayer(final PlayerWhite whitePlayer, final PlayerBlack blackPlayer) {
            GameController.currentPlayer = blackPlayer;
            return blackPlayer;
        }

        @Override
        public String toString(){
            return "B";
        }
    },
    NONE {
        @Override
        public int getDir() {
            return 0;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public Player chooseNextPlayer(PlayerWhite whitePlayer, PlayerBlack blackPlayer) {
            return null;
        }
    };

    public abstract int getDir();

    public abstract boolean isBlack();

    public abstract boolean isWhite();

    public abstract Player chooseNextPlayer(PlayerWhite whitePlayer, PlayerBlack blackPlayer);
}
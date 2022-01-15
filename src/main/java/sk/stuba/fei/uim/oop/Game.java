package sk.stuba.fei.uim.oop;

// utils
import java.util.List;
import java.util.Iterator;

public class Game {
    Deck deck = new Deck();
    Field[] gameDeck = deck.declareDeck();

    GameInitialization gameFunctions = new GameInitialization();
    List<Player> gamePlayers = gameFunctions.playersInitialization();
    Iterator<Player> it;

    protected Game() {
        while (gamePlayers.isEmpty()) {
            gamePlayers = gameFunctions.playersInitialization();
        }

        it = gamePlayers.iterator();

        while (!gamePlayers.isEmpty()) {
            Player currentPlayer = this.getCurrentPlayer();

            System.out.println();

            if (gamePlayers.size() == 1) {
                System.out.println("*** Koniec hry, vyhral hrac " + currentPlayer.getPlayerName() + " ***");

                return;
            }

            if (currentPlayer.roundsLeftInPrison() > 0) {
                if (currentPlayer.roundsLeftInPrison() == 1) {
                    currentPlayer.prisonHandler();
                }

                currentPlayer.prisonRoundDown();

                continue;
            }

            System.out.println("Hadze hrac " + currentPlayer.getPlayerName() + ", pre hodenie kocky stlac \"Enter\"");
            System.out.println("Ak chces ukoncit hru zadaj \"K\"");

            String inputKey = KeyboardInput.readString();
            if (inputKey.equals("K") || inputKey.equals("k")) {
                System.out.println("Ukoncil si hru!");

                return;
            }

            currentPlayer.playerThrow();
            gameFunctions.actualField(gameDeck, currentPlayer);

            if (currentPlayer.isDead()) {
                it.remove();
            }
        }
    }

    private Player getCurrentPlayer() {
        if (!it.hasNext()) {
            it = gamePlayers.iterator();
        }

        return it.next();
    }
}

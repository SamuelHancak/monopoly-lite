package sk.stuba.fei.uim.oop;

public class MoveCard extends Card {
    private int move = 1;

    protected MoveCard() {
        this.cardName = "Karta \"Pohyb\"";
    }

    // if constructor is called with "0" (which is index of the field Start on the
    // deck) player will be moved on the field start

    // if constructor is called with "18" (which is index of the field Police on the
    // deck) player will go to prison

    // in other cases, player will move the exact number of fields forward or
    // backward (based on the value with whom will be constructor called) from his
    // current position

    protected MoveCard(int move) {
        this.cardName = move == 0 ? "Karta \"Start\"" : move == 18 ? "Karta \"Vazenie\"" : "Karta \"Pohyb\"";
        this.move = move;
    }

    @Override
    protected void cardFunctionality(Player player) {
        System.out.println(this.cardName);
        if (move == 0) {
            System.out.println(player.getPlayerName() + " ide na start!");
            int positionFromStart = 24 - player.getCurrentPosition();
            player.setCurrentPosition(positionFromStart);
        } else if (this.move == 18) {
            System.out.println(player.getPlayerName() + " ide do vazenia!");
            player.goToPrison();
        } else {
            System.out.println(player.getPlayerName() + ((this.move > 0) ? " ide vpred o " : " ide vzad o ")
                    + Math.abs(this.move) + " pole/polia!");

            player.setCurrentPosition(this.move);
        }
    }

}

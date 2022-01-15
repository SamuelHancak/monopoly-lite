package sk.stuba.fei.uim.oop;

public class MoneyCard extends Card {
    private int value = 1000;

    protected MoneyCard() {
    }

    // based on value with whom is constructor called, will player either receive
    // money or will pay tax

    protected MoneyCard(int value) {
        this.value = value;
        this.cardName = this.value < 0 ? "Karta \"Dan\"" : "Karta \"Stastie\"";
    }

    @Override
    protected void cardFunctionality(Player player) {
        System.out.println(this.cardName);

        if (this.value < 0) {
            System.out.println(player.getPlayerName() + " plati dan vo vyske " + Math.abs(this.value));
            player.moneyHandler(-this.value);

            if (!player.isDead()) {
                player.moneyLeftString();
            }
        } else {
            System.out.println(player.getPlayerName() + " ma stastie, na zemi nasiel " + this.value);
            player.moneyHandler(this.value);
            player.moneyLeftString();
        }

    }

}

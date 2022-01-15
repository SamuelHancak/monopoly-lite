package sk.stuba.fei.uim.oop;

public class PropertiesCard extends Card {
    private int penalty = 3000;

    protected PropertiesCard() {
    }

    protected PropertiesCard(int penalty) {
        this.cardName = "Karta Nehnutelnosti";
        this.penalty = penalty;
    }

    @Override
    protected void cardFunctionality(Player player) {
        System.out.println(this.cardName);
        System.out.println("Za kazde pole ktore vlastni musi zaplatit pokutu vo vyske " + this.penalty);

        int fieldCount = player.getOwnedFieldsCount();
        if (fieldCount > 0) {
            int penaltyValue = fieldCount * this.penalty;
            System.out.println("Vlastni " + fieldCount + " pole(i)/polia, jeho pokuta teda bude " + penaltyValue);

            player.moneyHandler(-penaltyValue);
            player.moneyLeftString();
        } else {
            System.out.println("Nevlastni ziadne polia, takze neplati nic!");
        }
    }

}

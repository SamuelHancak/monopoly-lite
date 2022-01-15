package sk.stuba.fei.uim.oop;

// utils
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class GameInitialization {

    List<Card> gameCards = List.of(new MoveCard(), new PropertiesCard(5000), new MoneyCard(2000), new MoneyCard(-3000),
            new MoveCard(-2), new MoveCard(18), new MoneyCard(100), new PropertiesCard(), new MoveCard(2),
            new MoveCard(0), new MoveCard(-1), new MoneyCard(-1000));
    Iterator<Card> cardIt = gameCards.iterator();

    protected List<Player> playersInitialization() {
        System.out.print("Zadaj pocet hracov: ");

        String usersCountString = KeyboardInput.readString();
        usersCountString = usersCountString.replaceAll("[^\\d]", "");

        if (usersCountString.length() < 1 || usersCountString.equals("0")) {
            System.out.println("!!! Zadal si nepodporovany format poctu hracov !!!");
            System.out.println("--- Pre start hry, zadaj cele CISLO vacsie ako 0 ---");

            return List.of();
        }

        final int usersCount = Integer.parseInt(usersCountString);
        List<Player> players = new ArrayList<>();

        System.out.println("Hru bude hrat " + usersCount);
        System.out.println("Zadaj mena hracov, kazde meno oddel klavesou \"Enter\"");

        for (int i = 0; i < usersCount; ++i) {
            String name = KeyboardInput.readString();
            players.add(new Player(name));

            System.out.println((i + 1) + ". hrac s menom " + players.get(i).getPlayerName());
        }

        return players;
    }

    protected void actualField(Field[] deck, Player player) {
        int playerPosition = player.getCurrentPosition();
        Field playerCurrentField = deck[playerPosition];

        if (playerCurrentField.getFieldType() == FieldType.CHANCE) {
            var nextCard = this.getCard();
            ((ChanceField) playerCurrentField).setCard(nextCard);
            playerCurrentField.fieldFunction(player);

            if (nextCard.cardName.equals("Karta \"Pohyb\"")) {
                this.actualField(deck, player);
            }
        } else {
            playerCurrentField.fieldFunction(player);
        }
    }

    private Card getCard() {
        if (!this.cardIt.hasNext()) {
            this.cardIt = gameCards.iterator();
        }

        return this.cardIt.next();
    }

}

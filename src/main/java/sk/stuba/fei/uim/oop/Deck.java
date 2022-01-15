package sk.stuba.fei.uim.oop;

// utils
import java.util.Arrays;
import java.util.LinkedList;

public class Deck {

    protected Field[] declareDeck() {
        final int deckFieldsCount = 24;
        final Field[] deck = new Field[deckFieldsCount];

        LinkedList<FieldTypeSpecial> specialFieldsTypesArr = new LinkedList<>(Arrays.asList(FieldTypeSpecial.values()));

        for (int i = 0; i < deckFieldsCount; ++i) {

            if (((i % 6) != 0) && ((i % 3) == 0)) {
                Field field = new ChanceField();
                deck[i] = field;
            } else if ((i % 6) == 0) {
                Field field = new SpecialField(specialFieldsTypesArr.getFirst());
                deck[i] = field;
                specialFieldsTypesArr.removeFirst();
            } else {
                Field field = new BasicField("Pole " + i);
                deck[i] = field;
            }
        }

        return deck;
    }
}

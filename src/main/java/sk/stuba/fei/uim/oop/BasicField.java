package sk.stuba.fei.uim.oop;

public class BasicField extends Field {
    private int penalty = 50000; // penlaty in case foreign player stood on owned field
    private int fieldPrice = 10000; // field price
    private Player owner;
    private FieldType fieldTypeGeneral = FieldType.BASIC;

    protected BasicField() {
        super();
    }

    protected BasicField(String name) {
        super();
        this.fieldName = name;
    }

    protected void setFieldPenalty(int penalty) {
        this.penalty = penalty;
    }

    protected int getFieldPenalty() {
        return this.penalty;
    }

    protected void setFieldPrice(int price) {
        this.fieldPrice = price;
    }

    protected void setFieldOwner(Player newOwner) {
        this.owner = newOwner;
    }

    private boolean hasOwner() {
        return this.owner != null;
    }

    private boolean canAfford(Player player) {
        return player.moneyLeft() >= this.fieldPrice;
    }

    private boolean isFieldForeign(Player player) {
        return this.hasOwner() && !this.owner.equals(player);
    }

    private boolean isFieldMine(Player player) {
        return this.hasOwner() && this.owner.equals(player);
    }

    @Override
    protected FieldType getFieldType() {
        return this.fieldTypeGeneral;
    }

    @Override
    protected void fieldFunction(Player player) {
        if (isFieldForeign(player)) {
            System.out.println(player.getPlayerName() + " stoji na poli hraca " + this.owner.getPlayerName());
            System.out.println("Zaplati mu pokutu " + this.penalty);

            if (this.penalty > player.moneyLeft()) {
                this.owner.moneyHandler(player.moneyLeft());
                player.moneyHandler(-player.moneyLeft());
                player.playerDie();
            } else {
                this.owner.moneyHandler(this.penalty);
                player.moneyHandler(-this.penalty);
            }

            player.moneyLeftString();
        } else if (isFieldMine(player)) {
            System.out.println(player.getPlayerName() + " stoji na svojom poli \"" + this.fieldName + "\"");
        } else {
            System.out.println("Je na poli \"" + this.fieldName + "\" ktore sa da kupit za " + this.fieldPrice);
            player.moneyLeftString();
            if (this.canAfford(player)) {
                System.out.println("Ak chce pole \"" + this.fieldName
                        + "\" kupit, zadaj \"Y\". V opacnom pripade zadaj lubovolnu klavesu.");

                final char answer = KeyboardInput.readChar();

                if (answer == 'Y' || answer == 'y') {
                    this.setFieldOwner(player);
                    player.buyField(this);
                    player.moneyHandler(-this.fieldPrice);

                    System.out.println(player.getPlayerName() + " kupil pole " + this.fieldName);
                    player.moneyLeftString();
                } else {
                    System.out.println("Pole nekupil!");
                }

            } else {
                System.out.println("Cena pola je " + this.fieldPrice + ", takze si ho nemoze dovolit kupit!");
            }

        }
    }
}

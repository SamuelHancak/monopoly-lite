package sk.stuba.fei.uim.oop;

public class ChanceField extends Field {
    private FieldType fieldTypeGeneral = FieldType.CHANCE;
    private Card card;

    protected void setCard(Card card) {
        this.card = card;
    }

    @Override
    protected FieldType getFieldType() {
        return this.fieldTypeGeneral;
    }

    @Override
    protected void fieldFunction(Player player) {
        player.drawCard(this.card);
    }
}

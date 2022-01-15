package sk.stuba.fei.uim.oop;

public class SpecialField extends Field {
    private int tax = 10000;

    private FieldType fieldTypeGeneral = FieldType.SPECIAL;
    private FieldTypeSpecial fieldTypeSpecial;

    protected SpecialField() {
    }

    protected SpecialField(FieldTypeSpecial type) {
        switch (type) {
        case PRISON:
            this.prisonField();
            break;

        case TAX:
            this.taxField();
            break;

        case POLICE:
            this.policeField();
            break;

        default:
            this.startField();
            break;
        }
    }

    private SpecialField startField() {
        this.fieldName = "Start";
        this.fieldTypeSpecial = FieldTypeSpecial.START;

        return this;
    }

    private SpecialField prisonField() {
        this.fieldName = "Prison";
        this.fieldTypeSpecial = FieldTypeSpecial.PRISON;

        return this;
    }

    private SpecialField taxField() {
        this.fieldName = "Tax";
        this.fieldTypeSpecial = FieldTypeSpecial.TAX;

        return this;
    }

    private SpecialField policeField() {
        this.fieldName = "Police";
        this.fieldTypeSpecial = FieldTypeSpecial.POLICE;

        return this;
    }

    @Override
    protected FieldType getFieldType() {
        return this.fieldTypeGeneral;
    }

    @Override
    protected void fieldFunction(Player player) {

        switch (this.fieldTypeSpecial) {
        case START:
            System.out.println("Stoji na poli \"Start\"!");
            break;
        case PRISON:
            System.out.println("Je na navsteve vo vazeni!");
            break;
        case TAX:
            System.out.println("Stoji na poli \"Dan\" a musi zaplatit dan vo vyske " + this.tax + "!");
            player.moneyHandler(-this.tax);
            player.moneyLeftString();
            break;
        case POLICE:
            System.out.println("Stoji na poli \"Policia\" a ide do vazenia na 4 kola!");
            player.goToPrison();
            break;
        default:
            break;
        }

    }
}

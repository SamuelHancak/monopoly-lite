package sk.stuba.fei.uim.oop;

// utils
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;
    private int currentPosition = 0;
    private int money = 60000;
    private boolean isInPrison = false;
    private int roundsLeftInPrison = 0;
    private boolean isDead = false;
    private ArrayList<Field> ownsFields = new ArrayList<>();
    private Random rand = new Random();

    protected Player() {
    }

    protected Player(String name) {
        this.name = name;
    }

    protected String getPlayerName() {
        return this.name;
    }

    protected void prisonHandler() {
        this.isInPrison = !this.isInPrison;
    }

    protected void goToPrison() {
        this.prisonHandler();
        this.roundsLeftInPrison = 3; // in reality is user in prison for 4 rounds, round when he goes to the prison
                                     // is counted too
        this.currentPosition = 6; // "6" is fixed position of the Prison field
    }

    protected int roundsLeftInPrison() {
        return this.roundsLeftInPrison;
    }

    protected void prisonRoundDown() {
        System.out.println(this.getPlayerName() + " je vo vazeni este " + this.roundsLeftInPrison() + " kolo/a!");
        this.roundsLeftInPrison--;
    }

    protected void moneyLeftString() {
        if (!this.isDead()) {
            System.out.println("Zostatok na jeho ucte je " + this.moneyLeft());
        }
    }

    protected int moneyLeft() {
        return this.money;
    }

    protected void moneyHandler(int value) {
        if (value < 0 && (this.moneyLeft() + value) < 0) {
            this.playerDie();
        } else {
            this.money += value;
        }
    }

    protected void buyField(Field boughtfield) {
        this.ownsFields.add(boughtfield);
    }

    protected int getCurrentPosition() {
        return this.currentPosition;
    }

    protected void setCurrentPosition(int move) {
        int newPosition = move + this.getCurrentPosition();

        if (newPosition >= 24) {
            System.out.println("Presiel startom a inkasuje 5 000!");
            this.moneyHandler(5000);

            newPosition = newPosition % 24;
        }
        this.currentPosition = newPosition;
    }

    protected int getOwnedFieldsCount() {
        return this.ownsFields.size();
    }

    protected boolean isDead() {
        return this.isDead;
    }

    protected void playerDie() {
        System.out.println(this.name + " prisiel o vsetky svoje peniaze a hra sa pre neho konci!");
        this.isDead = true;
        for (var field : this.ownsFields) {
            ((BasicField) field).setFieldOwner(null);
        }
        this.ownsFields.clear();
    }

    protected void playerThrow() {
        int thrownNumber = rand.nextInt(6) + 1;
        System.out.println(this.getPlayerName() + " hodil cislo " + thrownNumber);
        this.setCurrentPosition(thrownNumber);
    }

    protected void drawCard(Card drawnCard) {
        System.out.println(this.name + " si taha kartu!");

        drawnCard.cardFunctionality(this);
    }
}

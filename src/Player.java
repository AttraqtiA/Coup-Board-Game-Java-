import java.util.*;

public class Player {
    private int coin;
    private ArrayList<InfluenceCard> influenceCards;
    private String name;
    private Random rand = new Random();

    public Player(String name) {
        this.name = name;
        this.coin = 2;
        this.influenceCards = new ArrayList<>();
        for(int a = 0; a < 2; a++) {
            int random = rand.nextInt(5) + 1;
            if (random == 1) {
                influenceCards.add(new Assassin());
            } else if (random == 2) {
                influenceCards.add(new Captain());
            } else if (random == 3) {
                influenceCards.add(new Contessa());
            } else if (random == 4) {
                influenceCards.add(new Duke());
            } else if (random == 5) {
                influenceCards.add(new Ambassador());
            }
        }
    }

    public boolean hasInfluence(String cardName) {
        for (InfluenceCard card : influenceCards) {
            if (card.getClass().getSimpleName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public void loseRandomInfluence() {
        if (influenceCards.size() == 1) {
            influenceCards.remove(0);
        } else {
            int random = rand.nextInt(2);
            influenceCards.remove(random);
        }
    }

    public void exchangeCard() {
        for (InfluenceCard card : influenceCards) {
            if (card.getClass().getSimpleName().equals("Ambassador")) {
                influenceCards.remove(card);
                break;
            }
        }
        int random = rand.nextInt(5) + 1;
        if (random == 1) {
            influenceCards.add(new Assassin());
        } else if (random == 2) {
            influenceCards.add(new Captain());
        } else if (random == 3) {
            influenceCards.add(new Contessa());
        } else if (random == 4) {
            influenceCards.add(new Duke());
        } else if (random == 5) {
            influenceCards.add(new Ambassador());
        }
    }

    public void addCoin(int amount) {
        this.coin += amount;
    }

    public void minusCoin(int amount) {
        this.coin -= amount;
    }

    public void Income() {
        this.coin++;
    }

    public void ForeignAid() {
        this.coin += 2;
    }

    public int getCoin() {
        return coin;
    }

    public ArrayList<InfluenceCard> getInfluenceCards() {
        return influenceCards;
    }

    public void setInfluenceCards(ArrayList<InfluenceCard> influenceCards) {
        this.influenceCards = influenceCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

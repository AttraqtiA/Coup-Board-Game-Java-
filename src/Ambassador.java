public class Ambassador extends InfluenceCard {
    public Ambassador() {
        super();
        blockSteal = true;
    }

    @Override
    public void Action(Player player, Player target) {
        System.out.println("\n" + player.getName() + " uses Exchange, exchanging cards with the Court Deck!");
        player.exchangeCard();
    }

    @Override
    public void Interrupt(Player player, Player target) {
        System.out.println("Duke: Block Foreign Aid");
    }
}
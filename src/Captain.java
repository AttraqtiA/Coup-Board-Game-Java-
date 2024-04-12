public class Captain extends InfluenceCard {
    public Captain() {
        super();
        blockSteal = true;
    }

    @Override
    public void Action(Player player, Player target) {
        System.out.println("\n" + player.getName() + " uses Steal, stealing 2 coins from " + target.getName());
        player.addCoin(2);
        target.minusCoin(2);
    }

    @Override
    public void Interrupt(Player player, Player target) {
        System.out.println("Duke: Block Foreign Aid");
    }
}
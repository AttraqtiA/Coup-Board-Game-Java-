public class Duke extends InfluenceCard {
    public Duke() {
        super();
        blockForeignAid = true;
    }

    @Override
    public void Action(Player player, Player target) {
        System.out.println("\n" + player.getName() + " uses Tax, gaining 3 coins");
        player.addCoin(3);
    }

    @Override
    public void Interrupt(Player player, Player target) {
        System.out.println("Duke: Block Foreign Aid");
    }
}

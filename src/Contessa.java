public class Contessa extends InfluenceCard {
    public Contessa() {
        super();
        blockAssassin = true;
    }

    @Override
    public void Action(Player player, Player target) {
        // Empty
    }

    @Override
    public void Interrupt(Player player, Player target) {
        System.out.println("Duke: Block Foreign Aid");
    }
}
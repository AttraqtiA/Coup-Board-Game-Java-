public class Assassin extends InfluenceCard {
    public Assassin() {
        super();
    }

    @Override
    public void Action(Player player, Player target) {
        System.out.println("\n" + player.getName() + " uses Assassinate against " + target.getName());
        if (target.hasInfluence("Contessa")) {
            System.out.println(target.getName() + " blocks the Assassinate with Contessa");
        } else {
            target.loseRandomInfluence();
        }
    }

    @Override
    public void Interrupt(Player player, Player target) {
        System.out.println("Duke: Block Foreign Aid");
    }
}
import com.sun.org.apache.xpath.internal.operations.Bool;

public abstract class InfluenceCard {
    protected Boolean blockAssassin, blockForeignAid, blockSteal;

    public InfluenceCard() {
    }

    public abstract void Action(Player player, Player target);

    public abstract void Interrupt(Player player, Player target);

    public Boolean getBlockAssassin() {
        return blockAssassin;
    }

    public Boolean getBlockForeignAid() {
        return blockForeignAid;
    }

    public Boolean getBlockSteal() {
        return blockSteal;
    }

}

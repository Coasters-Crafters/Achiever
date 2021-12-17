package nl.iobyte.achiever.addons.vault;

import nl.iobyte.achiever.generic.achievement.objects.AbstractAchievementType;

public class VaultAchievementType extends AbstractAchievementType<Double> {

    public VaultAchievementType(String type) {
        super("vault_"+type, Double.class);
    }

    /**
     * {@inheritDoc}
     */
    public void load() {
        VaultAddon.getInstance().load();
    }

}

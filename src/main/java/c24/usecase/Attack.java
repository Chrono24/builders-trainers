package c24.usecase;

import c24.plugin.SoundPlugin;
import c24.value.Unit;

import java.util.ArrayList;
import java.util.List;

public class Attack {
    private final SoundPlugin soundPlugin;

    private Unit attacker;
    private Unit[] targets;
    private List<String> results;

    public Attack(SoundPlugin soundPlugin, Unit attacker, Unit ... targets) {
        this.soundPlugin = soundPlugin;
        this.attacker = attacker;
        this.targets = targets;
    }

    public List<String> execute() {
        results = new ArrayList<>();
        validateInput();
        if (results.isEmpty()) {
            dealDamage();
        }

        return results;
    }

    private void dealDamage() {
        int damage = attacker.weapon.damage / targets.length;
        for (Unit target : targets) {
            if (canAttack(target)) {
                dealDamage(target, damage);
            }
        }

        if (attacker.weapon.soundEffect != null) {
            soundPlugin.playSoundEffect(attacker.weapon.soundEffect);
        }
    }

    private void dealDamage(Unit target, int damage) {
        target.health -= Math.min(target.health, damage);
        if (target.health > 0) {
            results.add(attacker.name + " hits " + target.name + " (-" + damage + " hp)");
        } else {
            results.add(attacker.name + " kills " + target.name);
        }
    }

    private boolean canAttack(Unit target) {
        if ("Morty".equals(attacker.name) && !"Robot".equals(target.name)) {
            results.add(attacker.name + " has issues attacking " + target.name);
            return false;
        }
        return true;
    }

    private void validateInput() {
        if (attacker == null) {
            throw new NullPointerException("Attacker must not be null!");
        }

        if (targets.length == 0) {
            results.add("There's noone to attack.");
        }

        if (attacker.weapon == null) {
            results.add(attacker.name + " has no weapon to attack with.");
        }
    }
}

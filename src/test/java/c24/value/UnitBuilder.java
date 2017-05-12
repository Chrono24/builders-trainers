package c24.value;

import org.jusecase.builders.Builder;

public class UnitBuilder implements Builder<Unit> {

    private Unit unit = new Unit();

    @Override
    public Unit build() {
        return unit;
    }

    public static UnitBuilder unit() {
        return new UnitBuilder();
    }

    public UnitBuilder rick() {
        unit.name = "Rick";

        Weapon weapon = new Weapon();
        weapon.name = "Portal Gun";
        weapon.damage = 10;
        unit.weapon = weapon;

        return this;
    }

    public UnitBuilder alien() {
        unit.health = 100;
        unit.name = "Alien Bureaucrat";
        return this;
    }
}
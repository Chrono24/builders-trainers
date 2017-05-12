package c24.value;

import org.jusecase.builders.Builder;

import static c24.value.WeaponBuilder.weapon;
import static org.jusecase.Builders.a;

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
        unit.weapon = a(weapon().portalGun());

        return this;
    }

    public UnitBuilder alien() {
        unit.health = 100;
        unit.name = "Alien Bureaucrat";
        return this;
    }
}
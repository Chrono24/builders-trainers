package c24.value;

import static c24.value.WeaponBuilder.weapon;
import static org.jusecase.Builders.a;

public class UnitBuilder implements UnitBuilderMethods<Unit, UnitBuilder> {

    private Unit unit = new Unit();

    public UnitBuilder() {
        unit.health = 100;
    }

    @Override
    public Unit getEntity() {
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
        unit.name = "Alien Bureaucrat";
        return this;
    }
}
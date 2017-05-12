package c24.value;

import org.jusecase.builders.Builder;

@javax.annotation.Generated(value="jusecase-builders-generator")
public interface UnitBuilderMethods<T extends Unit, B extends Builder> extends Builder<T> {
    @Override
    default T build() {
        return getEntity();
    }

    T getEntity();

    default B withHealth(int value) {
        getEntity().health = value;
        return (B)this;
    }

    default B withName(String value) {
        getEntity().name = value;
        return (B)this;
    }

    default B withWeapon(c24.value.Weapon value) {
        getEntity().weapon = value;
        return (B)this;
    }
}

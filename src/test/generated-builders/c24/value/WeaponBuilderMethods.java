package c24.value;

import org.jusecase.builders.Builder;

@javax.annotation.Generated(value="jusecase-builders-generator")
public interface WeaponBuilderMethods<T extends Weapon, B extends Builder> extends Builder<T> {
    @Override
    default T build() {
        return getEntity();
    }

    T getEntity();

    default B withDamage(int value) {
        getEntity().damage = value;
        return (B)this;
    }

    default B withName(String value) {
        getEntity().name = value;
        return (B)this;
    }

    default B withSoundEffect(String value) {
        getEntity().soundEffect = value;
        return (B)this;
    }
}

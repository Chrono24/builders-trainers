package c24.value;

import org.jusecase.builders.Builder;

public class WeaponBuilder implements Builder<Weapon> {
   private Weapon weapon = new Weapon();

   @Override
   public Weapon build() {
       return weapon;
   }

   public static WeaponBuilder weapon() {
       return new WeaponBuilder();
   }

    public WeaponBuilder portalGun() {
        weapon.name = "Portal Gun";
        weapon.damage = 10;
        return this;
    }
}
package c24.value;

public class WeaponBuilder implements WeaponBuilderMethods<Weapon, WeaponBuilder> {
   private Weapon weapon = new Weapon();

   @Override
   public Weapon getEntity() {
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
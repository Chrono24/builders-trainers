package c24.usecase;

import c24.plugin.SoundPlugin;
import c24.value.Unit;

import static c24.value.UnitBuilder.unit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.jusecase.Builders.a;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AttackTest {
    private SoundPlugin soundPlugin;

    private Unit attacker;
    private Unit[] targets;
    private List<String> results;

    @Before
    public void setUp() {
        soundPlugin = mock(SoundPlugin.class);

        attacker = a(unit().rick());

        givenTargets(a(unit().alien()));
    }

    @Test
    public void noAttacker() {
        attacker = null;
        Throwable throwable = catchThrowable(this::whenAttacking);
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Attacker must not be null!");
    }

    @Test
    public void noWeapon() {
        attacker.weapon = null;
        whenAttacking();
        thenResultIs("Rick has no weapon to attack with.");
    }

    @Test
    public void noTargets() {
        givenTargets();
        whenAttacking();
        thenResultIs("There's noone to attack.");
    }

    @Test
    public void healthIsReducedByWeaponDamage() {
        attacker.weapon.damage = 10;

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(90);
        thenResultIs("Rick hits Alien Bureaucrat (-10 hp)");
    }

    @Test
    public void healthIsReducedByWeaponDamage2() {
        attacker.weapon.damage = 20;

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(80);
        thenResultIs("Rick hits Alien Bureaucrat (-20 hp)");
    }

    @Test
    public void healthIsReducedByWeaponDamage_neverBelowZero() {
        attacker.weapon.damage = 200;

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(0);
        thenResultIs("Rick kills Alien Bureaucrat");
    }

    @Test
    public void multipleTargets_damageIsSplit() {
        givenTargets(
                a(unit().withName("Unit 1")),
                a(unit().withName("Unit 2")),
                a(unit().withName("Unit 3"))
        );

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(97);
        assertThat(targets[1].health).isEqualTo(97);
        assertThat(targets[2].health).isEqualTo(97);
        thenResultIs(
                "Rick hits Unit 1 (-3 hp)",
                "Rick hits Unit 2 (-3 hp)",
                "Rick hits Unit 3 (-3 hp)"
        );
    }

    @Test
    public void mortyCannotAttackAliens() {
        attacker = a(unit().morty());

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(100);
        thenResultIs("Morty has issues attacking Alien Bureaucrat");
    }

    @Test
    public void mortyCanAttackRobots() {
        attacker = a(unit().morty());
        targets[0] = a(unit().robot());

        whenAttacking();

        assertThat(targets[0].health).isEqualTo(90);
        thenResultIs("Morty hits Robot (-10 hp)");
    }

    @Test
    public void weaponSound_noTargets() {
        givenTargets();
        whenAttacking();
        thenNoSoundIsPlayed();
    }

    @Test
    public void weaponSound_weaponHasNoSound() {
        attacker.weapon.soundEffect = null;
        whenAttacking();
        thenNoSoundIsPlayed();
    }

    @Test
    public void weaponSound() {
        attacker.weapon.soundEffect = "zap.wav";
        whenAttacking();
        thenSoundIsPlayed("zap.wav");
    }

    private void thenNoSoundIsPlayed() {
        verify(soundPlugin, never()).playSoundEffect(any());
    }

    private void thenSoundIsPlayed(String soundEffect) {
        verify(soundPlugin).playSoundEffect(soundEffect);
    }

    private void givenTargets(Unit ... targets) {
        this.targets = targets;
    }

    private void whenAttacking() {
        Attack usecase = new Attack(soundPlugin, attacker, targets);
        results = usecase.execute();
    }

    private void thenResultIs(String ... expected) {
        assertThat(results).containsExactly(expected);
    }
}
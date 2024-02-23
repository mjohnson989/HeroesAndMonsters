package ham;

import misc.Misc;

public abstract class Monster extends Dungeon_Character implements iDC{
    private double chance_to_regen;
    private int min_regen;
    private int max_regen;

    public Monster(String name, int max_hp, int y_coord, int x_coord,
                   double base_chance, int do_damage_max,int do_damage_min,
                   int direction, int rows, int columns, double chance_to_regen,
                   int min_regen, int max_regen) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max,
                do_damage_min, direction, rows, columns);
        this.chance_to_regen = chance_to_regen;
        this.min_regen = min_regen;
        this.max_regen = max_regen;
    }

    @Override
    public void hit(int damage) {
        if(damage != 0) {
            super.hit(damage);
            regen();
        }

    }
    private void regen() {
        if(super.is_alive()&& super.percent_health() < 1.0) {
            if(super.successful_action(chance_to_regen)) {
                int regen = Misc.gen_random_int(min_regen, max_regen);
                System.out.println(super.get_name() + " regens for "
                        + regen + " hit points");
                super.hit(-regen);
            }
        }
    }
    @Override
    public int[] attack() {
        int[] solution = super.attack();
        regen();
        return solution;
    }
    @Override
    public void change_direction(int direction) {
        super.change_direction(direction);
        regen();
    }
    @Override
    public void move() {
        super.move();
        regen();
    }
    @Override
    public String toString(){
        String info = "Monster " + super.toString();
        return 	info;
    }
}
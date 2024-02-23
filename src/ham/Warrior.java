package ham;

import misc.Misc;

public class Warrior extends Hero{
    private int reflected;
    private double chance_to_reflect;

    public Warrior(String name, int max_hp, int y_coord, int x_coord,
                   double base_chance, int do_damage_max,int do_damage_min,
                   int direction, int rows, int columns, double block_chance
            ,double chance_to_reflect) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns,
                block_chance);
        this.chance_to_reflect = chance_to_reflect;
        this.reflected = 0;
    }
    public Warrior() {
        super();
        this.chance_to_reflect = .2;
        this.reflected = 0;
    }

    @Override
    public void hit(int damage) {
        if(super.successful_action(chance_to_reflect) && damage > 0) {
            int reflect_roll = Misc.gen_random_int(1, 20);
            if(reflect_roll == 1) {
                damage = damage * 2;
                this.reflected = 0;
                System.out.println(super.get_name() + " failed misserably and takes "
                        + damage + " damage");
            }else if(reflect_roll == 20) {
                this.reflected = damage * 2;
                damage = 0;
                System.out.println(super.get_name() + " did an incredible job and "
                        + "reflects " + this.reflected);
            }else{
                this.reflected = damage * (reflect_roll /5);
                damage /=2;
                System.out.println(super.get_name() + " takes "
                        + damage + " damage and reflects " + this.reflected);
            }
        }

        super.hit(damage);
    }

    @Override
    public int[] attack() {
        int[] solution = super.attack();
        int size = solution.length-1;
        if(this.reflected > 30) {
            this.reflected = 30;
        }
        solution[size] += this.reflected;
        this.reflected = 0;
        return solution;
    }

    @Override
    public String toString(){
        String info = "Warrior " + super.toString();
        return 	info;
    }
}
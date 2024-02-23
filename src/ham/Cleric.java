package ham;

import misc.Misc;

public class Cleric extends Hero {

    private double chance_to_heal;
    private int max_heal;
    private int min_heal;
    private double health_threshold;

    public Cleric(String name, int max_hp, int y_coord, int x_coord,
                  double base_chance, int do_damage_max,int do_damage_min, int direction,
                  int rows, int columns, double block_chance,double chance_to_heal,
                  int min_heal, int max_heal) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max,
                do_damage_min, direction, rows, columns,block_chance);
        this.chance_to_heal = chance_to_heal;
        this.min_heal = min_heal;
        this.max_heal = max_heal;
        this.health_threshold = .5;
    }
    public Cleric() {
        super();
        this.chance_to_heal = .4;
        this.min_heal = 10;
        this.max_heal = 20;
        this.health_threshold = .5;
    }
    /*
     * if the cleric falls bellow a certain percentage of health.
     * it will occasionally heal instead of attack;
     */
    @Override
    public int[] attack() {
        int[] solution = super.attack();
        int size = solution.length -1;
        double health_percent = super.percent_health();

        //if below 5 percent health,  Try to heal.
        if(health_percent <= this.health_threshold) {
            solution[size] = 0;
            if(super.successful_action(chance_to_heal)) {
                int healing = Misc.gen_random_int(min_heal, max_heal);
                System.out.println(super.get_name() + " heals for " + healing);
                super.hit(-healing);
            }
        }
        return solution;
    }


    @Override
    public String toString(){
        String info = "Cleric " + super.toString();
        return 	info;
    }
}
package ham;

public class Troll extends Monster {
    private double chance_to_roll;
    private int rows, columns;
    private double damage_percent;
    public Troll(String name, int max_hp, int y_coord, int x_coord,
                 double base_chance, int do_damage_max,int do_damage_min, int direction,
                 int rows, int columns, double chance_to_regen, int min_regen,
                 int max_regen,double chance_to_roll) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max,
                do_damage_min, direction, rows, columns,chance_to_regen, min_regen,
                max_regen);
        this.chance_to_roll = chance_to_roll;
        this.rows = rows;
        this.columns = columns;
        this.damage_percent = 1;
    }

    @Override
    public int[] attack() {

        int[] solution = super.attack();
        if(super.successful_action(chance_to_roll) && solution[2] > 0){
            System.out.println(super.get_name() +
                    " rolls a boulder at you!");
            int[] new_solution =
                    this.get_new_solution(solution);
            new_solution[4] = (int) (new_solution[4] * this.damage_percent);
            this.damage_percent = this.damage_percent/2;
            return new_solution;
        }else{
            return solution;
        }
    }


    private int[] get_new_solution(int[] solution){
        int facing = super.get_direction();
        int[] new_solution = new int[5];
        new_solution[0] = solution[0];
        new_solution[1] = solution[1];
        new_solution[4] = solution[2];
        switch(facing){
            case 1:
                new_solution[2] = super.set_vars(0, rows-1,(solution[0] - 1));
                new_solution[3] =  solution[1];
                break;
            case 2:
                new_solution[2] = solution[0];
                new_solution[3] = super.set_vars(0, columns-1,(solution[1] + 1));
                break;
            case 3:
                new_solution[2] = super.set_vars(0, rows-1,(solution[0] + 1));
                new_solution[3] = solution[1];
                break;
            case 4:
                new_solution[2] = solution[0];
                new_solution[3] = super.set_vars(0, columns-1,(solution[1] - 1));
                break;
        }
        return new_solution;
    }
    @Override
    public String toString(){
        String info = "Troll " + super.toString();
        return 	info;
    }

}
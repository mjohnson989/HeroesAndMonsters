package ham;

import misc.Misc;
import java.util.*;

public class Archer extends Hero{
    private int arrow_damage;
    private boolean is_human;
    private Scanner scan = new Scanner(System.in);
    private int rows, columns;
    private int max = 0;
    public Archer(String name, int max_hp, int y_coord, int x_coord,
                  double base_chance, int do_damage_max,int do_damage_min,
                  int direction, int rows, int columns,double block_chance, Scanner scan, boolean is_human) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max,
                do_damage_min, direction, rows, columns,block_chance);
        this.is_human = is_human;
        this.scan = scan;
        this.rows = rows;
        this.columns = columns;
    }
    private int[] arrow_solution(int[] solution){
        int choice = 0;
        if(is_human){
            System.out.println("how many squares away will you shoot? ");
            choice = Misc.get_user_int(scan, 2, max);
        }else{
            choice = Misc.gen_random_int(2, max);
            System.out.println("Computer will fire " + choice + " squares away");
        }
        switch(this.get_direction()){
            case 1:
                solution[0] -= choice;
                break;
            case 2:
                solution[1] += choice;
                break;
            case 3:
                solution[0] += choice;
                break;
            case 4:
                solution[1] -= choice;
                break;
        }
        int size = solution.length;
        solution[size] = this.arrow_damage;
        return solution;
    }

    private int get_choice() {
        int[] location = super.get_location();
        this.get_direction();
        int min = 1;
        switch (this.get_direction()) {
            case 1:
                max = location[0];
                break;
            case 2:
                max = columns - location[1] - 1;
                break;
            case 3:
                max = rows - location[0];
                break;
            case 4:
                max = location[1];
                break;

        }
        int choice = 2;
        if(max > min) {
            choice = 1;
        }
        return choice;
    }
}

package ham;

import misc.Misc;
import java.util.*;

public class Archer extends Hero{
    private int arrow_damage;
    private boolean is_human;
    private Scanner scan = new Scanner(System.in);
    private int rows, columns;
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
    private int[] arrow_location(){
        return null;
    }

    private int get_choice(){
        int[] location = super.get_location();


        int choice = 0;
        if(is_human){
            System.out.println("How many squares ahead would you like to fire your arrow? ");
            choice = Misc.get_user_int(scan, 2, );
        }else{
            choice = Misc.gen_random_int(1, 2);
        }

        if(choice == 1){
            System.out.println(super.get_name() + " has chosen to teleport ");
            if(is_human){
                this.move();
            }else{

            }
        }else{
            System.out.println(super.get_name() + " has chosen not to teleport and moves normally ");
        }
        return choice;
    }
}

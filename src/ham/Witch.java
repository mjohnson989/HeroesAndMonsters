package ham;

import misc.Misc;
import java.util.*;

public class Witch extends Monster{
    //witch can sometimes split itself into three, and only takes one third damage at this time.
    //witch can occasionally teleport, human may be able to choose or may be random.

    private boolean is_human;
    private boolean mirrored;
    private double chance_to_teleport;
    private double chance_to_mirror;
    private Scanner scan;
    private int rows;
    private int columns;
    public Witch(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_max, int do_damage_min, int direction, int rows, int columns,
                 double chance_to_regen, int min_regen, int max_regen, boolean is_human, boolean mirrored, double chance_to_teleport, double chance_to_mirror, Scanner scan) {

        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns, chance_to_regen, min_regen, max_regen);
        this.rows = rows;
        this.columns = columns;
        this.is_human = is_human;
        this.mirrored = mirrored;
        this.chance_to_teleport = chance_to_teleport;
        this.chance_to_mirror = chance_to_mirror;
        this.scan = scan;
    }

    private void teleport_human(){
        int[] new_location = new int[2];
        if(super.successful_action(chance_to_teleport)){
            System.out.println(super.get_name() + " successfully begins teleporting.");
            System.out.println("What row do you want to teleport to?");
            new_location[0] = Misc.get_user_int(scan, 0, this.rows-1);
            System.out.println("What column do you want to teleport to?");
            new_location[1] = Misc.get_user_int(scan, 0, this.columns-1);

            super.set_location(new_location);
        }else{
            System.out.println(super.get_name() + "'s teleportation spell fizzles out and does not work. ");
        }
    }

    private void teleport_computer(){
        int[] new_location = new int[2];
        if(super.successful_action(chance_to_teleport)){
            System.out.println(super.get_name() + " successfully begins teleporting");
            new_location[1] = Misc.gen_random_int(0, this.columns-1);
            new_location[0] = Misc.gen_random_int(0, this.rows-1);
        }else{
            System.out.println(super.get_name() + "'s teleportation spell fizzles out and does not work. ");
        }
    }

    private int get_choice(){
        int choice = 0;

        if(is_human){
            System.out.println("Do you want to teleport?\n Yes(1)\n No(2)");
            choice = Misc.get_user_int(scan, 1, 2);
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

    @Override
    public void move(){
        int choice = this.get_choice();
        if(choice == 2){
            super.move();
        }else{
            if(is_human){
                this.teleport_human();
            }else{
                this.teleport_computer();
            }
        }
    }

    @Override
    public void hit(int damage){
        if(super.successful_action(chance_to_mirror)){
            System.out.println("The witch produces 3 identical images before the attacker! ");
            damage /= 3;
            this.mirrored = true;
        }
        super.hit(damage);
    }

}

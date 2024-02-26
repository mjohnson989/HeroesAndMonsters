package ham;

import java.util.Scanner;

import misc.Misc;

public class Game {
    private Scanner scan;
    private boolean keep_playing = true, game_over = false;
    private int turn;
    private Dungeon_Character dc1,dc2;
    private Board board;
    private int max_hp = 20;
    private int x_coord = 0;
    private int y_coord = 0;
    private double base_chance = .6;
    private int do_damage_max = 10, do_damage_min = 2;
    private int direction = 4;
    private int rows, columns;

    public Game(Scanner scan,int rows, int columns) {
        this.scan = scan;
        this.rows = rows;
        this.columns = columns;
    }

    private void check_game_over() {
        if(!dc1.is_alive() || dc2.is_alive() == false){
            this.game_over = true;
            if(dc1.is_alive()){
                System.out.println(dc1.get_name() + " wins!");
            }else if(dc2.is_alive()){
                System.out.println(dc2.get_name() + " wins!");
            }else{
                System.out.println(" No one wins!");
            }
        }
    }
    private void combat(Dungeon_Character attack,Dungeon_Character defense) {
        int[] solution = attack.attack();
        int size = solution.length/2;
        boolean hit = false;
        if(solution[solution.length-1] > 0){
            for(int i = 0;i<size;i=i+2){
                if(defense.get_location()[0]== solution[i] &&
                        defense.get_location()[1]==solution[i+1]){
                    System.out.println(attack.get_name() + " hits and " +
                            "does " + solution[2] + " damage");
                    hit = true;
                }
            }
            if(hit){
                defense.hit(solution[solution.length-1]);
            }else{
                System.out.println("Missed");
            }
        }
    }
    private void computer_change_direction(){
        int choice = Misc.gen_random_int(1, 4);
        if(choice == 1){
            System.out.println(dc2.get_name() + " faces north.");
        }else if(choice == 2){
            System.out.println(dc2.get_name() + " faces east.");
        }else if(choice == 3){
            System.out.println(dc2.get_name() + " faces south.");
        }else{
            System.out.println(dc2.get_name() + " faces west.");
        }
        dc2.change_direction(choice);
    }

    private void display_info() {
        System.out.println(dc1.toString());
        System.out.println(dc2.toString());
    }
    private void human_change_direction(){
        String message ="Which direction do you wish to face? "+
                "1 for north, 2 for east, 3 for south, 4 for west";
        System.out.println(message);
        int choice = Misc.get_user_int(scan, 1, 4);
        dc1.change_direction(choice);
    }
    private void initialize_characters() {
        initialize_human_character();
        initialize_computer_character();
    }

    private void initialize_computer_character() {
        this.y_coord = this.rows -1;
        this.x_coord = this.columns - 1;
        String name = "Slag";
        int choice = Misc.gen_random_int(1, 4);
        if(choice == 1) {
            System.out.println(name + " is a Warrior.");
        }else if(choice == 2) {
            System.out.println(name + " is a Troll.");
        }
        else if(choice == 3){
            System.out.println(name + " is a cleric");
        }else if(choice ==4){
            System.out.println(name + " is a witch");
        }
        dc2 = chose_character(name,choice);
        board.mark_board(dc2.get_location()[0], dc2.get_location()[1],dc2.get_symbol());
    }
    private void initialize_game() {
        board = new Board(rows,columns);
        board.initialize_board();
        initialize_characters();
        board.display_board();
        this.turn = 0;
        this.game_over = false;
    }

    private void initialize_human_character() {
        System.out.println("Welcome human, enter your name");
        String name = scan.nextLine();
        System.out.println("Choose your character\n1: Warrior\n2: Troll\n3: Cleric");
        int choice = Misc.get_user_int(scan, 1, 3);
        dc1 = chose_character(name,choice);
        board.mark_board(dc1.get_location()[0], dc1.get_location()[1],dc1.get_symbol());
    }
    public Dungeon_Character chose_character( String name,int choice) {
        switch(choice) {
            case 1:
                return initialize_warrior(name);
            case 2:
                return initialize_troll(name);
            case 3:
                return initialize_cleric(name);
            case 4:
                return initialize_witch(name, is_human);
            default:
                return null;
        }
    }

    private Dungeon_Character initialize_witch(String name, boolean is_human){
        double chance_to_regen = 0.2;
        int min_regen = 2;
        int max_regen = 10;
        double chance_to_mirror = 0.2;
        double chance_to_teleport = 0.2;
        boolean mirrored = false;
        Dungeon_Character dc = new Witch(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns, chance_to_regen,
                min_regen, max_regen, is_human, mirrored, chance_to_teleport, chance_to_mirror, scan);
        return dc;
    }
    private Dungeon_Character initialize_warrior(String name) {
        double block_chance = .5;
        double chance_to_reflect = .3;
        Dungeon_Character dc = new Warrior(name,max_hp,y_coord,x_coord,base_chance,
                do_damage_max, do_damage_min, direction,rows,
                columns, block_chance,chance_to_reflect);
        return dc;
    }
    private Dungeon_Character initialize_troll(String name) {
        double chance_to_regen = .2;
        int min_regen = 2;
        int max_regen = 10;
        double chance_to_roll = .3;
        Dungeon_Character dc = new Troll(name,max_hp,y_coord,x_coord,base_chance,
                do_damage_max, do_damage_min, direction,rows,
                columns,chance_to_regen,min_regen,max_regen,chance_to_roll);
        return dc;
    }

    private Dungeon_Character initialize_cleric(String name){
        double chance_to_heal = 0.4;
        int min_heal = 2;
        int max_heal = 10;
        double block_chance = 0.5;
        Dungeon_Character dc;
        dc = new Cleric(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns, block_chance, chance_to_heal, min_heal, max_heal);
        return dc;
    }
    private void move(Dungeon_Character dc) {
        board.mark_board(dc.get_location()[0], dc.get_location()[1], ' ');
        dc.move();
        board.mark_board(dc1.get_location()[0], dc1.get_location()[1],
                dc1.get_symbol());
        board.mark_board(dc2.get_location()[0], dc2.get_location()[1],
                dc2.get_symbol());
    }
    public void play_game() {
        int choice = 0;
        while(keep_playing){
            initialize_game();
            while(!game_over){
                take_turns();
            }
            System.out.println("Enter 1 if you wish to play again. 2 if you don't");
            choice = Misc.get_user_int(scan, 1, 2);
            if(choice == 2){
                keep_playing = false;
            }
        }
    }
    private void take_computer_turn() {
        int choice = Misc.gen_random_int(1, 3);
        if(choice == 1){
            System.out.println(dc2.get_name() + " moves.");
            this.move(dc2);
        }else if(choice == 2){
            System.out.println(dc2.get_name() + " fights.");
            this.combat(dc2, dc1);
        }else{
            this.computer_change_direction();
        }
        board.display_board();
    }
    private void take_human_turn() {
        String message = "You have three options:  \n" +
                "Move (Enter 1)\n" +
                "Combat (Enter 2)\n" +
                "Change direction (Enter 3)";
        System.out.println(message);
        int choice = Misc.get_user_int(scan,1,3);

        if(choice == 1){
            this.move(dc1);
        }else if(choice == 2){
            this.combat(dc1, dc2);
        }else{
            this.human_change_direction();
        }
        board.display_board();
    }
    private void take_turns() {
        if(!this.game_over) {
            if((this.turn % 2) == 0) {
                this.display_info();
                this.take_human_turn();
            }else{
                this.display_info();
                this.take_computer_turn();
            }
            this.turn ++;
            this.check_game_over();
        }
    }

}
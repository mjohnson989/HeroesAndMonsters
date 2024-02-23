package ham;

import java.util.Random;

import misc.Misc;

public abstract class Dungeon_Character implements iDC {
    private String name;
    private char symbol;
    private int max_hp;
    private int current_hp;
    private int x_coord;
    private int y_coord;
    private double base_chance;
    private int do_damage_max, do_damage_min;
    private int direction;
    private int rows, columns;

    public Dungeon_Character(String name,int max_hp,
                             int y_coord, int x_coord, double base_chance,
                             int do_damage_max,int do_damage_min,
                             int direction, int rows, int columns){
        this.name = name;
        this.symbol = name.charAt(0);
        this.max_hp = max_hp;
        this.current_hp = max_hp;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.base_chance = base_chance;
        this.do_damage_max = do_damage_max;
        this.do_damage_min = do_damage_min;
        this.direction = direction;
        this.rows = rows;
        this.columns = columns;
    }
    public Dungeon_Character() {
        this.name = "Dummy";
        this.symbol = 'D';
        this.max_hp = 20;
        this.current_hp = 20;
        this.x_coord = 0;
        this.y_coord = 0;
        this.base_chance = .6;
        this.do_damage_max =  20;
        this.do_damage_min = 3;
        this.direction = 3;
        this.rows = 8;
        this.columns = 8;
    }

    public String get_name() {
        return name;
    }
    public char get_symbol(){
        return this.symbol;
    }
    public int get_direction() {
        return direction;
    }

    @Override
    public int[] get_location() {
        int[] location = {this.y_coord, this.x_coord};
        return location;
    }

    public void set_location(int[] location){
        this.x_coord = this.set_vars(0, this.rows-1, location[0]);
        this.y_coord = this.set_vars(0, this.columns-1, location[1]);
    }

    private int[] new_increment(){
        int[] increment_array = new int[2];
        switch(this.direction){
            case 1:
                increment_array = this.increment_north();
                break;
            case 2:
                increment_array = this.increment_east();
                break;
            case 3:
                increment_array = this.increment_south();
                break;
            case 4:
                increment_array = this.increment_west();
                break;
        }
        return increment_array;
    }


    private int[] increment_east(){
        int x = set_vars(0, columns - 1,this.x_coord+1);//add to x to go right
        int[] new_increment = {this.y_coord,x};
        return new_increment;
    }
    private int[] increment_west(){
        int x = set_vars(0, columns - 1,this.x_coord-1);//add to x to go right
        int[] new_increment = {this.y_coord,x};
        return new_increment;
    }
    private int[] increment_north(){
        int y = set_vars(0,rows-1,this.y_coord-1);//subtract from y to go up
        int[] new_increment = {y,this.x_coord};
        return new_increment;
    }
    private int[] increment_south(){
        int y = set_vars(0,rows-1,this.y_coord+1);//subtract from y to go up
        int[] new_increment = {y,this.x_coord};
        return new_increment;
    }

    @Override
    public void move() {
        int[] increment = this.new_increment();
        this.y_coord = increment[0];
        this.x_coord = increment[1];
    }

    @Override
    public int[] attack() {
        int[] fire_solution = new int[3];
        int[] increment = this.new_increment();
        fire_solution[0] = increment[0];
        fire_solution[1] = increment[1];
        if(this.successful_action(this.base_chance)){
            fire_solution[2] = Misc.gen_random_int(do_damage_min, do_damage_max);
        }else{
            System.out.println(this.name + " misses wildly!");
            fire_solution[2] = 0;
        }
        return fire_solution;
    }


    @Override
    public void change_direction(int direction) {
        this.direction = this.set_vars(1, 4, direction);
    }


    public double percent_health() {
        return (double)this.current_hp / this.max_hp;
    }

    @Override
    public void hit(int damage) {
        this.current_hp = set_vars(0,this.max_hp, this.current_hp - damage);
    }

    @Override
    public boolean is_alive() {
        return this.current_hp > 0;
    }

    @Override
    public int set_vars(int min, int max, int var) {
        if(var < min){
            var = min;
        }else if(var > max){
            var = max;
        }
        return var;
    }

    private String facing(){
        switch(this.direction){
            case 1:
                return "North";
            case 2:
                return "East";
            case 3:
                return "South";
            case 4:
                return "West";
            default:
                return "Error";
        }
    }

    @Override
    public boolean successful_action(double chance) {
        Random rand = new Random();
        return  rand.nextDouble() <= chance;
    }

    @Override
    public String toString(){
        String read_out = this.name + "\n";
        read_out += "Facing " + facing() + "\n";
        read_out += "Has " + this.current_hp + " hp\n";
        return read_out;
    }


}
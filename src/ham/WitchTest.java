package ham;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WitchTest extends Monster {
    private String name = "jake";
    private char symbol = 'j';
    private int max_hp = 20;
    private int current_hp = 20;
    private int x_coord = 0;
    private int y_coord = 0;
    private double base_chance = 0.8;
    private int do_damage_max = 2, do_damage_min = 1;
    private int direction = 2;
    private int rows = 9, columns = 9;
    private double chance_to_regen = 0.5;
    private int min_regen = 1;
    private int max_regen = 5;
    private boolean is_human = true;
    private boolean mirrored = false;
    private double chance_to_teleport = 1;
    private double chance_to_mirror = 1;
    private Scanner scan = new Scanner(System.in);
    Witch w1 = new Witch(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns, chance_to_regen,
            min_regen, max_regen, is_human, mirrored, chance_to_teleport, chance_to_mirror, scan);

    public WitchTest(String name, int max_hp, int y_coord, int x_coord, double base_chance, int do_damage_max, int do_damage_min, int direction, int rows, int columns, double chance_to_regen, int min_regen, int max_regen, boolean is_human, boolean mirrored, double chance_to_teleport, double chance_to_mirror, Scanner scan) {
        super(name, max_hp, y_coord, x_coord, base_chance, do_damage_max, do_damage_min, direction, rows, columns, chance_to_regen, min_regen, max_regen);
        this.rows = rows;
        this.columns = columns;
        this.is_human = is_human;
        this.mirrored = mirrored;
        this.chance_to_teleport = chance_to_teleport;
        this.chance_to_mirror = chance_to_mirror;
        this.scan = scan;
    }

    @Test
    void test_do_teleport(){
        fail("Not yet implemented");
        w1.move();//choose yes to teleport and try 6,7
        int[] location = w1.get_location();
        assertTrue(location[0] == 6 && location[1] == 7);
    }
    @Test
    void test_donot_teleport(){

    }
}
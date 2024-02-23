package ham;
import misc.Misc;
import java.util.*;
public class Ham_Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        Game g1 = new Game(scan, 3, 3);
        g1.play_game();
        scan.close();
    }
}

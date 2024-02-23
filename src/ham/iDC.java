package ham;

public interface iDC {
    public void move();
    public int[] attack();
    public void change_direction(int direction);
    public int[] get_location();
    public void hit(int damage);
    public boolean is_alive();
    public int set_vars(int min, int max,int var);
    public boolean successful_action(double chance);
}

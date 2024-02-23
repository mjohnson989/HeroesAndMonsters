package ham;

public abstract class Hero extends Dungeon_Character implements iDC{
    private double block_chance;
    public Hero(String name, int max_hp, int y_coord, int x_coord,
                double base_chance, int do_damage_max,int do_damage_min,
                int direction, int rows, int columns,double block_chance) {
        super(name, max_hp, y_coord, x_coord, base_chance,
                do_damage_max, do_damage_min, direction, rows, columns);
        this.block_chance = block_chance;
    }
    public Hero() {
        super();
        this.block_chance = .4;
    }
    @Override
    public void hit(int damage) {
        if(damage > 0) {
            if(super.successful_action(block_chance)) {
                System.out.println(super.get_name() +
                        " has successfuly blocked " + (damage-1) +" damage");
                damage = 1;
            }
        }
        super.hit(damage);
    }
    @Override
    public String toString(){
        String info = "Hero " + super.toString();
        return 	info;
    }
}
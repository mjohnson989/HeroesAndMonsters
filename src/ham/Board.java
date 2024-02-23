package ham;

public class Board {
    private char[][] board;
    private int rows, columns;

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.board = new char[rows][columns];
    }
    public void initialize_board(){
        for(int i = 0;i < rows;i++){
            for(int j = 0;j< columns;j++){
                board[i][j] = ' ';
            }
        }
    }
    public void mark_board(int y, int x, char symbol){
        this.board[y][x] = symbol;
    }
    public void display_board() {
        String horizontal_bar = "|";
        String main_line = "|";
        for(int i = 0; i < this.columns - 1; i++) {
            main_line += "===+";
        }
        main_line += "===|";
        System.out.println(main_line);
        for(int i = 0; i < this.rows;i++) {
            for(int j = 0; j < this.columns;j++) {
                System.out.print(horizontal_bar + " " + board[i][j] + " ");
            }
            System.out.print(horizontal_bar);
            System.out.println();
            System.out.println(main_line);
        }
        System.out.println();
    }

}
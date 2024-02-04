public class Board {
    private Tile[][] board;
    private int rows;
    private int cols;

    public Board(String b)
    {
        this.rows = (int)(b.chars().filter(ch -> ch == '\n').count());
        this.cols = (int)(b.split("\n")[0].chars().filter(ch -> ch == ',').count()) + 1;
        // System.out.println("rows: "+rows+", cols: "+cols);
        this.board = new Tile[rows][cols];

        String[] rows_content = b.split("\n");
        for (int i = 0; i < rows; i++) {
            
            String[] content = rows_content[i].split(",");
            for (int j = 0; j < cols; j++) {
                    board[i][j] = new Tile(content[j]);
            }
        }
    }

    @Override
    public String toString()
    {
        String ret = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                    ret += board[i][j].getContent();
                    if (j != cols-1) {
                        ret += ",";
                    }
            }
            ret += '\n';
        }
        return ret;
    }
}

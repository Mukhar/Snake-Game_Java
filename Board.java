public class Board {
    public final int total_row,total_col;
    public Cell[][] board;
    // coordinate for food
    public int foodx,foody;

    public Board(int row_count,int col_count){
        // board is initialized here
        this.foodx=this.foody=0;
    
        this.total_col=col_count;
        this.total_row=row_count;

        this.board=new Cell[row_count][col_count];
        try{
            for (int r=0;r<row_count;r++){
                for (int c=0;r<col_count;c++){
                    this.board[r][c]=new Cell(r,c);
                }    
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public Cell[][] getCells(){ 
        return board; 
    } 

    public void generateFood(){
        //generating food randomly
        this.foodx=(int)Math.random()*(this.total_row-1);
        this.foody=(int)Math.random()*(this.total_col-1);

        this.board[foodx][foody].celltype=CellType.FOOD;
    }

}
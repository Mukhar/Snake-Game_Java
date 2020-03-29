import javax.swing.*;
import java.awt.*;
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
        for (int r=0;r<this.total_row;r++){
            for (int c=0;r<this.total_col;c++){
                this.board[r][c]=new Cell(r,c);
            }    
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
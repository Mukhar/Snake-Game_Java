public class Cell{
    public int row,col;
    public CellType celltype;
    public Cell(int row, int col){
        this.row=row;
        this.col=col;
    }

    public void setCellType(CellType ctype){
        this.celltype=ctype;
    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Board {
    private int[][] cells;
    private Cell[][] cellObjects;
    private int countString;
    private int countColumns;
    private boolean changes;
    private int number_of_companies;

    public Board(int countString, int countColumns) {
        cells = new int[countString][countColumns];
        cellObjects = new Cell[countString][countColumns];
        this.countString = countString;
        this.countColumns = countColumns;
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumns; j++) {
                cellObjects[i][j] = new Cell(i, j);
            }
        }
        changes = false;
        number_of_companies = 0;
    }

    public String[][] getRepainting(){
        String[][] repainting = new String[countString][countColumns];
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumns; j++) {
                repainting[i][j] = cellObjects[i][j].getColor();
            }
        }
        return repainting;
    }

    public void setRepainting(String[][] repainting){
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumns; j++) {
                cellObjects[i][j].setBaseColor();
            }
        }
        ChangeColorAndCompanyRepainting(repainting);
    }

    public void ChangeColorAndCompanyRepainting(String[][] stringColor){
        number_of_companies = 0;
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumns; j++) {
                Cell cell = cellObjects[i][j];
                cell.setColor(stringColor[i][j]);
                cell.setCompany(0);
                if (cell.isMainColor()) defines_cell_company(i, j);
            }
        }
    }

    public boolean getChanges(){
        return changes;
    }

    public void resetChanges(){
        changes = false;
    }

    public void changesMade(){
        changes = true;
    }

    public int getCountString() {
        return countString;
    }

    public int getCountColumns() {
        return countColumns;
    }

    public int getCellValue(int row, int col) {
        return cells[row][col];
    }

    public void setCellValue(int row, int col, int value) {
        cells[row][col] = value;
        cellObjects[row][col].setValue(value);
    }

    public boolean isColoring(int row, int col) {
        return cellObjects[row][col].isColoring();
    }

    public void setColoring(int row, int col){
        cellObjects[row][col].setColoring();
        changesMade();
    }

    public boolean isMain(int row, int col) {
        return cellObjects[row][col].isMainColor();
    }

    public void setMain(int row, int col){
        cellObjects[row][col].setMainColor();
        changesMade();
        defines_cell_company(row, col);
    }

    public boolean isBase(int row, int col) {
        return cellObjects[row][col].isBaseColor();
    }

    public List<Cell> getAdjacentCells(int row, int col) {
        List<Cell> adjacentCells = new ArrayList<>();
        if (row > 0) adjacentCells.add(cellObjects[row - 1][col]);
        if (row < countString - 1) adjacentCells.add(cellObjects[row + 1][col]);
        if (col > 0) adjacentCells.add(cellObjects[row][col - 1]);
        if (col < countColumns - 1) adjacentCells.add(cellObjects[row][col + 1]);
        return adjacentCells;
    }

    public void defines_cell_company (int row, int col){
        List<Integer> companies = new ArrayList<>();
        for (Cell cell : getAdjacentCells(row, col)){
            if (cell.isMainColor() && !companies.contains(cell.getCompany())){
                companies.add(cell.getCompany());
            }
        }
        Collections.sort(companies);
        if (companies.isEmpty()) {
            number_of_companies++;
            cellObjects[row][col].setCompany(number_of_companies);
        } else {
            cellObjects[row][col].setCompany(companies.get(0));
            if (companies.size() > 1) company_change(companies);
        }
    }

    public void company_change(List<Integer> companies){
        Integer new_company = companies.get(0);
        companies.remove(0);
        for (int i = 0; i < countString; i++){
            for (int j = 0; j < countColumns; j++){
                Cell cell = cellObjects[i][j];
                if (companies.contains(cell.getCompany())) cell.setCompany(new_company);
            }
        }
    }

    public boolean only_company(){
        for (int i = 0; i < countString; i++){
            for (int j = 0; j < countColumns; j++){
                Cell cell = cellObjects[i][j];
                if (cell.getCompany() > 1) return false;
            }
        }
        return true;
    }

    public boolean betweenSame(int row, int col){
        if (0 < row && row < countString - 1 &&
                cells[row-1][col] == cells[row+1][col]) return true;
        else if (0 < col && col < countColumns - 1 &&
                cells[row][col-1] == cells[row][col+1]) return true;
        return false;
    }

    public List<Cell> getCompetitorCells(int row, int col) {
        List<Cell> adjacentCells = new ArrayList<>();
        for (int p = 0; p < countString; p++)
            if (row != p && (cells[p][col] == cells[row][col])) adjacentCells.add(cellObjects[p][col]);
        for (int q = 0; q < countColumns; q++)
            if (col != q && (cells[row][q] == cells[row][col])) adjacentCells.add(cellObjects[row][q]);
        return adjacentCells;
    }

    public void print() {
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumns; j++) {
                int value = getCellValue(i, j);
                if (isColoring(i, j)) {
                    System.out.print("- ");
                } else if (isMain(i, j)) {
                    System.out.print(value + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}

import java.util.List;

public class Checker {
    public static boolean neighbors(int i, int j, boolean run,
                                    Board originalField,
                                    boolean diagonal) {
        if (!run) {
            return run;
        }
        for (Cell cell : originalField.getAdjacentCells(i, j)){
            if (cell.isColoring()){
                run = false;
                return run;
            } else if (cell.isBaseColor()){
                cell.setMainColor();
                originalField.defines_cell_company(cell.getRow(), cell.getCol());
            }
        }
        return run;
    }

    public static boolean Checking_adjacent_red_cell_is_not_blocked(int row, int col, boolean run,
                                                             Board originalField,
                                                             boolean diagonal) {
        if (!run) return false;
        for (Cell cell : originalField.getAdjacentCells(row, col)) {
            int i = cell.getRow();
            int j = cell.getCol();
            if (originalField.isMain(i, j)) {
                int counter = 0;
                Cell freePath = null;
                List<Cell> adjacents = originalField.getAdjacentCells(i, j);
                for (Cell adj : adjacents) {
                    if (adj.isColoring()) counter++;
                    else freePath = adj;
                }
                if (counter == adjacents.size()) {
                    run = false;
                    return false;
                } else if (counter == (adjacents.size() - 1) && !(freePath == null)) {
                    freePath.setMainColor();
                    originalField.defines_cell_company(freePath.getRow(), freePath.getCol());
                }
            }
        }
        return run;
    }
}

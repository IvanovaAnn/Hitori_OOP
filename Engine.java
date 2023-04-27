import java.util.List;

public class Engine {
    boolean run;
    int countColumn, countString;
    Board originalField;
    boolean diagonal;

    public Engine(boolean run,
                  Board originalField, boolean diagonal) {
        this.run = run;
        this.countColumn = originalField.getCountColumns();
        this.countString = originalField.getCountString();
        this.originalField = originalField;
        this.diagonal = diagonal;
    }

    public boolean paintingRepeatsInBlack() {
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumn; j++) {
                if (!run) {
                    return false;
                }
                else if (! originalField.isMain(i, j)) {
                    continue;
                }
                for (Cell cell : originalField.getCompetitorCells(i, j)) {
                    if (run && cell.isBaseColor()) {
                        cell.setColoring();
                        run = Checker.neighbors(cell.getRow(), cell.getCol(), run, originalField, diagonal);
                        run = Checker.Checking_adjacent_red_cell_is_not_blocked(
                                cell.getRow(), cell.getCol(), run, originalField, diagonal);
                    } else if (!cell.isColoring()) {
                        run = false;
                        return false;
                    }
                }
            }
        }
        return run;
    }

    public boolean paintingInRedCellsAmongTheSameCells() {
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumn; j++) {
                if (!run) {
                    return false;
                }
                if (originalField.betweenSame(i,j)){
                    if (originalField.isColoring(i,j)){
                        run = false;
                        return false;
                    } else if (originalField.isBase(i, j)) originalField.setMain(i, j);
                }
            }
        }
        return run;
    }

    public boolean checkForTightnessOfTheRedCell() {
        for (int i = 0; i < countString; i++) {
            for (int j = 0; j < countColumn; j++) {
                if (!run) {
                    return run;
                }
                if (originalField.isMain(i, j)){
                    int counter = 0;
                    Cell freePath = null;
                    List<Cell> adjacents = originalField.getAdjacentCells(i, j);
                    for (Cell adj : adjacents){
                        if (adj.isColoring()) counter++;
                        else freePath = adj;
                    }
                    if (counter == adjacents.size()) {
                        run = false;
                        return false;
                    }
                    else if (counter == (adjacents.size() - 1) && !(freePath == null)){
                        freePath.setMainColor();
                        originalField.defines_cell_company(freePath.getRow(), freePath.getCol());
                    }
                }
            }
        }
        return run;
    }
}

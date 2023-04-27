import java.util.ArrayList;

public class Solvme {
    public static void solve( Board originalField, boolean diagonal) {
        ArrayList<String[][]> solutionsPending =
                new ArrayList<String[][]>();
        solutionsPending.add(originalField.getRepainting());
        int countString = originalField.getCountString();
        int sl = originalField.getCountColumns();
        while (solutionsPending.size() > 0) {
            boolean run = true;
            originalField.setRepainting(solutionsPending.get(solutionsPending.size() - 1));
            solutionsPending.remove(solutionsPending.size() - 1);
            for (int i = 0; i < countString; i++) {
                if (!run) {
                    break;
                }
                for (int j = 0; j < sl; j++) {
                    if (!run) {
                        break;
                    }
                    do {
                        originalField.resetChanges();
                        Engine eng = new Engine(run, originalField, diagonal);
                        run = eng.paintingInRedCellsAmongTheSameCells();
                        run = eng.paintingRepeatsInBlack();
                        run = eng.checkForTightnessOfTheRedCell();
                    } while ((originalField.getChanges()) && run);
                    if (!originalField.isBase(i, j) || !run) {
                        continue;
                    }
                    for (Cell cell : originalField.getCompetitorCells(i, j)){
                        if (!cell.isColoring()) originalField.setColoring(i,j);
                    }
                    if (!originalField.isColoring(i, j)) {
                        originalField.setMain(i,j);
                    }
                    else {
                        String[][] repainting = originalField.getRepainting();
                        repainting[i][j] = "Main";
                        solutionsPending.add(repainting);
                        run = Checker.neighbors(i, j, run, originalField, diagonal);
                    }
                }
            }
            if (run && originalField.only_company()) {
                System.out.println("ответ");
                originalField.print();
            }
        }
    }
}

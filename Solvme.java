import java.util.ArrayList;

public class Solvme {
    public static void solve( Board originalField, boolean diagonal) {
        ArrayList<String[][]> solutionsPending =
                new ArrayList<String[][]>();
        //ArrayList<Board> allSolutions =
        //        new ArrayList<Board>();
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
                        //System.out.println("do");
                        originalField.resetChanges();
                        Engine eng = new Engine(run, originalField, diagonal);
                        //System.out.println("до" + run);
                        run = eng.paintingInRedCellsAmongTheSameCells();
                        //System.out.println("1" + run);
                        run = eng.paintingRepeatsInBlack();
                        //System.out.println("2" + run);
                        run = eng.checkForTightnessOfTheRedCell();
                        //System.out.println("3" + run);;
                        //originalField.print();
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
                        //ArrayList<ArrayList<String>> otherOpinion =
                        //        new ArrayList<ArrayList<String>>();
                        //for (int ni = 0; ni < newList.size(); ni++) {
                        //    otherOpinion.add(new ArrayList<String>());
                        //    for (int nj = 0; nj < newList.get(ni).size(); nj++) {
                        //        otherOpinion.get(ni).add(newList.get(ni).get(nj));
                        //    }
                        //}
                        //otherOpinion.get(i).set(j, originalField.get(i).get(j).toString());
                        //solutionsPending.add(otherOpinion);
                        run = Checker.neighbors(i, j, run, originalField, diagonal);
                    }
                }
            }
            if (run && originalField.only_company()) {
                System.out.println("ответ");
                originalField.print();
                //ArrayList<ArrayList<String>> new_hit = new ArrayList<ArrayList<String>>();
                //for (int ni = 0; ni < newList.size(); ni++) {
                //    ArrayList<String> hit_str = new ArrayList<String>();
                //    for (int nj = 0; nj < newList.get(ni).size(); nj++) {
                //        hit_str.add(newList.get(ni).get(nj));
                //    }
                //    new_hit.add(hit_str);
                //}


                //allSolutions.add(new_hit);
            }
        }
        //return allSolutions;
    }
}

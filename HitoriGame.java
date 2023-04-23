import java.util.Scanner;

public class HitoriGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arg[] = scanner.nextLine().split(" ");
        int countStringArg = Integer.parseInt(arg[0]);
        int countColumnsArg = Integer.parseInt(arg[1]);
        Board board = new Board(countStringArg, countColumnsArg);
        for (int i = 0; i < countStringArg; i++) {
            String row = scanner.nextLine();
            String[] cells = row.split(" ");
            for (int j = 0; j < countColumnsArg; j++) {
                int value = Integer.parseInt(cells[j]);
                board.setCellValue(i, j, value);
            }
        }
        Solvme solvme = new Solvme();
        solvme.solve(board, false);
    }
}

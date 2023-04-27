import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testGetCurrentColor() {
        Color color = new Color();
        String currentColor = color.getCurrentСolor();

        assertEquals("\u001B[37m", currentColor);
    }

    @Test
    public void testSetMainColor() {
        Color color = new Color();
        color.setMainСolor();

        assertTrue(color.isMainColor());
        assertFalse(color.isBaseColor());
        assertFalse(color.isColoringColor());
    }

    @Test
    public void testSetColoringColor() {
        Color color = new Color();
        color.setColoringСolor();

        assertTrue(color.isColoringColor());
        assertFalse(color.isBaseColor());
        assertFalse(color.isMainColor());
    }

    @Test
    public void testSetBaseColor() {
        Color color = new Color();
        color.setBaseСolor();

        assertTrue(color.isBaseColor());
        assertFalse(color.isMainColor());
        assertFalse(color.isColoringColor());
    }

    @Test
    public void testGetCompany() {
        Cell cell = new Cell(0, 0);
        int company = cell.getCompany();

        assertEquals(0, company);
    }

    @Test
    public void testSetCompany() {
        Cell cell = new Cell(0, 0);
        cell.setCompany(1);

        assertEquals(1, cell.getCompany());
    }

    @Test
    public void testGetColor() {
        Cell cell = new Cell(0, 0);

        assertEquals("Base", cell.getColor());

        cell.setMainColor();
        assertEquals("Main", cell.getColor());

        cell.setColoring();
        assertEquals("Coloring", cell.getColor());
    }

    @Test
    public void testSetColor() {
        Cell cell = new Cell(0, 0);

        cell.setColor("Base");
        assertTrue(cell.isBaseColor());

        cell.setColor("Main");
        assertTrue(cell.isMainColor());

        cell.setColor("Coloring");
        assertTrue(cell.isColoring());
    }

    @Test
    public void testGetRow() {
        Cell cell = new Cell(2, 3);
        int row = cell.getRow();

        assertEquals(2, row);
    }

    @Test
    public void testGetCol() {
        Cell cell = new Cell(2, 3);
        int col = cell.getCol();

        assertEquals(3, col);
    }

    @Test
    public void testGetValue() {
        Cell cell = new Cell(0, 0);
        cell.setValue(5);

        assertEquals(5, cell.getValue());
    }

    @Test
    public void testGetRepainting() {
        Board board = new Board(3, 3);
        String[][] expectedRepainting = {
                {"Base", "Base", "Base"},
                {"Base", "Base", "Base"},
                {"Base", "Base", "Base"}
        };

        assertArrayEquals(expectedRepainting, board.getRepainting());
    }

    @Test
    public void testSetRepainting() {
        Board board = new Board(3, 3);
        String[][] repainting = {
                {"Base", "Base", "Base"},
                {"Main", "Main", "Main"},
                {"Coloring", "Coloring", "Coloring"}
        };

        board.setRepainting(repainting);
        assertArrayEquals(repainting, board.getRepainting());
    }

    @Test
    public void testGetChanges() {
        Board board = new Board(3, 3);

        assertFalse(board.getChanges());

        board.changesMade();
        assertTrue(board.getChanges());
    }

    @Test
    public void testResetChanges() {
        Board board = new Board(3, 3);
        board.changesMade();

        board.resetChanges();
        assertFalse(board.getChanges());
    }

    @Test
    public void testGetCountString() {
        Board board = new Board(5, 3);
        int countString = board.getCountString();

        assertEquals(5, countString);
    }

    @Test
    public void testGetCountColumns() {
        Board board = new Board(5, 3);
        int countColumns = board.getCountColumns();

        assertEquals(3, countColumns);
    }

    @Test
    public void testGetCellValue() {
        Board board = new Board(3, 3);

        board.setCellValue(0, 0, 5);
        assertEquals(5, board.getCellValue(0, 0));

        board.setCellValue(1, 2, 3);
        assertEquals(3, board.getCellValue(1, 2));
    }

    @Test
    public void testIsColoring() {
        Board board = new Board(3, 3);

        assertFalse(board.isColoring(0, 0));

        board.setColoring(1, 1);
        assertTrue(board.isColoring(1, 1));
    }

    @Test
    public void testSetMain() {
        Board board = new Board(3, 3);

        board.setMain(0, 0);
        assertTrue(board.isMain(0, 0));

        board.setMain(2, 2);
        assertTrue(board.isMain(2, 2));
    }

    @Test
    public void testIsBase() {
        Board board = new Board(3, 3);

        assertTrue(board.isBase(0, 0));

        board.setMain(1, 1);
        assertFalse(board.isBase(1, 1));
    }

    @Test
    public void testGetAdjacentCells() {
        Board board = new Board(3, 3);
        board.setCellValue(0, 0, 1);
        board.setCellValue(1, 1, 2);
        board.setCellValue(0, 2, 1);
        board.setCellValue(0, 1, 3);
        board.setCellValue(1, 0, 4);
        board.setCellValue(2, 1, 5);
        board.setCellValue(1, 2, 6);
        assertEquals(2, board.getAdjacentCells(0, 0).size());
        assertEquals(4, board.getAdjacentCells(1, 1).size());
    }

    @Test
    public void testGetCompetitorCells() {
        Board board = new Board(3, 3);
        board.setCellValue(0, 0, 1);
        board.setCellValue(1, 1, 2);
        board.setCellValue(0, 2, 1);
        List<String> expectedCompetitor = new ArrayList<>();
        List<Cell> competitorCells = board.getCompetitorCells(0, 0);
        for (Cell cell : competitorCells) expectedCompetitor.add(cell.getRow()+ " " + cell.getCol());
        assertTrue(expectedCompetitor.contains("0 2"));
    }

    @Test
    public void testOnlyCompany() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 5);
        board.setCellValue(1, 2, 6);
        assertTrue(board.only_company());
        board.setMain(1, 0);
        board.setMain(1, 2);
        assertFalse(board.only_company());
        board.setCellValue(1, 1, 3);
        board.setMain(1,1);
        assertTrue(board.only_company());
    }

    @Test
    public void testBetweenSame() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 5);
        board.setCellValue(1, 2, 5);
        assertTrue(board.betweenSame(1, 1));
    }

    @Test
    void testPaintingRepeatsInBlack() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 1);
        board.setCellValue(1, 2, 1);
        board.setCellValue(1, 1, 5);
        board.setCellValue(0, 0, 7);
        board.setCellValue(0, 1, 8);
        board.setCellValue(0, 2, 9);
        board.setCellValue(2, 2, 6);
        board.setCellValue(2, 0, 2);
        board.setCellValue(2, 2, 3);
        board.setMain(1, 0);
        Engine engine = new Engine(true, board, false);
        assertTrue(engine.paintingRepeatsInBlack());
        assertTrue(board.isColoring(1, 2));
    }

    @Test
    void testPaintingInRedCellsAmongTheSameCells() {
        Board board = new Board(3, 3);

        board.setCellValue(1, 0, 1);
        board.setCellValue(1, 2, 1);
        board.setCellValue(1, 1, 5);
        board.setCellValue(0, 0, 7);
        board.setCellValue(2, 2, 6);
        Engine engine = new Engine(true, board, false);
        assertTrue(engine.paintingInRedCellsAmongTheSameCells());
        assertTrue(board.isMain(1, 1));
    }

    @Test
    void testCheckForTightnessOfTheRedCell() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 1);
        board.setCellValue(1, 2, 11);
        board.setCellValue(1, 1, 5);
        board.setCellValue(0, 0, 7);
        board.setCellValue(0, 1, 8);
        board.setCellValue(0, 2, 9);
        board.setCellValue(2, 2, 6);
        board.setCellValue(2, 0, 2);
        board.setCellValue(2, 2, 3);
        board.setColoring(0, 1);
        board.setColoring(1, 0);
        board.setColoring(1, 2);
        board.setMain(1, 1);
        Engine engine = new Engine(true, board, false);
        assertTrue(engine.checkForTightnessOfTheRedCell());
        assertTrue(board.isMain(2, 1));
    }

    @Test
    void testNeighbors() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 1);
        board.setCellValue(1, 2, 11);
        board.setCellValue(1, 1, 5);
        board.setCellValue(0, 0, 7);
        board.setCellValue(0, 1, 8);
        board.setCellValue(0, 2, 9);
        board.setCellValue(2, 2, 6);
        board.setCellValue(2, 0, 2);
        board.setCellValue(2, 2, 3);
        board.setColoring(1, 0);
        boolean run = true;
        run = Checker.neighbors(1, 0, run, board, false);
        assertTrue(board.isMain(0, 0));
        assertTrue(board.isMain(1, 1));
        assertTrue(board.isMain(2, 0));
        assertTrue(run);
    }

    @Test
    void testCheckingAdjacentRedCellIsNotBlocked() {
        Board board = new Board(3, 3);
        board.setCellValue(1, 0, 1);
        board.setCellValue(1, 2, 11);
        board.setCellValue(1, 1, 5);
        board.setCellValue(0, 0, 7);
        board.setCellValue(0, 1, 8);
        board.setCellValue(0, 2, 9);
        board.setCellValue(2, 2, 6);
        board.setCellValue(2, 0, 2);
        board.setCellValue(2, 2, 3);
        board.setColoring(0, 1);
        board.setColoring(1, 0);
        board.setColoring(1, 2);
        board.setMain(1, 1);
        boolean run = true;
        run = Checker.Checking_adjacent_red_cell_is_not_blocked(0, 1, run, board, false);
        assertTrue(board.isMain(2, 1));
        assertTrue(run);
    }
}

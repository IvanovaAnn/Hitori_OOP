public class Cell {
    private int value;
    private Color color;
    private int company;
    private boolean isolated;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.color = new Color();
        //this.isBlack = false;
        //this.isRed = false;
        //this.isWhite = true;
        this.isolated = false;
        company = 0;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company){
        this.company = company;
    }

    public String getColor() {
        if (color.isBaseColor()) return "Base";
        else if (color.isMainColor()) return "Main";
        else return "Coloring";
    }

    public void setColor(String stringColor) {
        if (stringColor.equals("Base")) color.setBaseСolor();
        else if (stringColor.equals("Main")) color.setMainСolor();
        else color.setColoringСolor();
    }

    public int getRow() { return row; }

    public int getCol() { return col; }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isColoring() {
        return this.color.isColoringColor();
    }

    public void setColoring() {
        this.color.setColoringСolor();
    }

    public boolean isMainColor() {
        return this.color.isMainColor();
    }

    public void setMainColor() {
        //System.out.println("setMain " + row + " " + col);
        this.color.setMainСolor();
    }

    public boolean isBaseColor() {
        return this.color.isBaseColor();
    }

    public boolean isIsolated() {
        return this.isolated;
    }

    public void setIsolated(boolean isolated) {
        this.isolated = isolated;
    }
}

public class Color {
    private String baseСolor;
    private String mainСolor;
    private String coloringСolor;
    private String currentСolor;

    public Color(){
        baseСolor = "\u001B[37m";
        mainСolor = "\u001B[31m";
        coloringСolor = "\u001B[30m";
        currentСolor = baseСolor;
    }

    public String getCurrentСolor() {
        return currentСolor;
    }

    public void setMainСolor() {
        this.currentСolor = mainСolor;
    }

    public void setColoringСolor() {
        this.currentСolor = coloringСolor;
    }

    public void setBaseСolor() {
        this.currentСolor = baseСolor;
    }

    public Boolean isBaseColor(){
        return currentСolor.equals(baseСolor);
    }

    public Boolean isMainColor(){
        return currentСolor.equals(mainСolor);
    }

    public Boolean isColoringColor(){
        return currentСolor.equals(coloringСolor);
    }
}

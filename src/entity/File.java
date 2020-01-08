package entity;

public class File {
    private String fileName;
    private String selectedAbsolutePath;

    public File(){

    }

    public File(String fileName, String selectedAbsolutePath) {
        this.fileName = fileName;
        this.selectedAbsolutePath = selectedAbsolutePath;
    }

    public String getSelectedAbsolutePath() {
        return selectedAbsolutePath;
    }

    public void setSelectedAbsolutePath(String selectedAbsolutePath) {
        this.selectedAbsolutePath = selectedAbsolutePath;
    }

    public String getFileName() {
        return fileName;
    }



    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

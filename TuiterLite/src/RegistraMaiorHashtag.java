public class RegistraMaiorHashtag {

    private final int ID;
    private int Occurrance;

    public RegistraMaiorHashtag(int ID, int occurrance) {
        this.ID = ID;
        Occurrance = occurrance;
    }

    public int getID() {
        return ID;
    }


    public int getOccurrance() {
        return Occurrance;
    }

    public void setOccurrance(int occurrance) {
        Occurrance = occurrance;
    }
}

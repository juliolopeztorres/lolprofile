package oob.lolprofile.DetailsComponent.Data;

public class NoChampionsFoundDBException extends Exception {
    private final static String MESSAGE = "No champions were found in DB!";

    public NoChampionsFoundDBException() {
        super(MESSAGE);
    }
}

package repositories;

public class IdCounter {
    public static IdCounter Instance = new IdCounter();

    private int _id = 0;

    public int getNextId() {
        return ++_id;
    }
}

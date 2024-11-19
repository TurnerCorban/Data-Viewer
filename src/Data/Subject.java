package src.Data;

public interface Subject {
    void addObserver(src.Data.Observer o);
    void notifyObservers();
}

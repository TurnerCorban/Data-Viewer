package src.main;

public interface Subject {
    void addObserver(src.main.Observer o);
    void removeObserver(src.main.Observer o);
    void notifyObservers();
}

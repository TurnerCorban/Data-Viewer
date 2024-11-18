package src.Data;

import java.util.Observer;

public interface Subject {
    public void addObserver(src.Data.Observer o);
    public void removeObserver(src.Data.Observer o);
    public void notifyObservers();
}

Lab 4 is based off of Lab 3. The Design Patterns used are the Observer and Builder patterns. 

An Observer pattern is used to update the JFreeChart in the program without calling the class directly. The TablePanel implements the subjet interface, and ChartDisplayPanel implements the Observer interface.
The additional interfaces and methods required are: 
Interfaces: 
  1. Observer Interface (Observers of the subject implement this so they can be updated when a subject class notifies observers)
  2. Subject Interface (Subjects implement this so Observers can be updated)
Methods: 
  Subject:
    1. addObserver(Observer o). Tbe Observing class calls this in order to add itself to the list of observers the Subject notifies
    2. notifyObservers(). When the subject class calls this, each Observer class that added itself to the list at runtime is notified. This and the update method in Observer classes can be changed to pass values.  
  Observer:
    1. update(). Is called when a Subject calls notifyObservers.

I used a StringBuilder for my StatsPanel to reduce complexity when adding text to a JTextArea. StringBuilder is a built in method in java that contains some very useful functions. Since it's a built in function, no additional methods are required. 

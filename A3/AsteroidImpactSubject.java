import java.util.ArrayList;
import java.util.ListIterator;

public class AsteroidImpactSubject implements ISubject
{
    private ArrayList<BoardComponent> observers;

    public AsteroidImpactSubject()
    {
        observers = new ArrayList<BoardComponent>();
    }

    @Override
    public void Attach(BoardComponent observer)
    {
        observers.add(observer);
    }

    @Override
    public void Detach(BoardComponent observer)
    {
        observers.remove(observer);
    }

    @Override
    public void Notify()
    {
        ListIterator<BoardComponent> iter = observers.listIterator();
        while (iter.hasNext())
        {
            iter.next().Update();
        }
    }
}

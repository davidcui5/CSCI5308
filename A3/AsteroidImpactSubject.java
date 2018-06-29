import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//Concrete Subject class for Asteroid impacts
public class AsteroidImpactSubject implements ISubject
{
    //Observers include everything in the composite, squares, shields, buildings, asteroids
    // this to respect the Composite pattern
    private List<BoardComponent> observers;

    public AsteroidImpactSubject()
    {
        observers = new ArrayList<BoardComponent>();
    }

    //Attaching and Detaching Observers happens at these times:
    //  Building: Detach when shielded, and when destroyed. Attach when spawned and when shield is gone.
    //  Asteroid: Detach when impact ground. Attach when spawned.
    //  Square: Detach only when shielded. Attach when spawned and when shield is gone.
    //  Shield: Detach when destroyed. Attach when spawned.
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

    //Notify the Subject that Asteroid impacted at x, y
    // Subject tells all Observers that Asteroid impacted at x, y
    @Override
    public void Notify(int x, int y)
    {
        //Used a copy of the list of Observers to iterate because when Updating,
        // some Observers can detach themselves from the Subject, so that can break the ieration
        // without using a copy
        List<BoardComponent> observersCopy = new ArrayList<BoardComponent>(observers);

        ListIterator<BoardComponent> iter = observersCopy.listIterator();
        while(iter.hasNext())
        {
            iter.next().Update(x,y);
        }
    }
}

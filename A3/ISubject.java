//The Subject interface in Observer pattern
public interface ISubject {

    public void Attach(BoardComponent observer);

    public void Detach(BoardComponent observer);

    //Notify parameter x, y are grid position, meaning Asteroid at x, y impacted
    public void Notify(int x, int y);
}

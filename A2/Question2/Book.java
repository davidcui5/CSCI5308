public class Book implements IBook
{
    public String getAuthor()
    {
        return "Hemingway";
    }

    public String getTitle()
    {
        return "For Whom The Bell Tolls";
    }

    public boolean isDigitalOnly()
    {
        return false;
    }
}
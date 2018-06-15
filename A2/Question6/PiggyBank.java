public class PiggyBank
{
    private int numPennies;
    private int numDimes;
    private int numNickels;
    private int numQuarters;

    public PiggyBank()
    {
        numPennies = 0;
        numDimes = 0;
        numNickels = 0;
        numQuarters = 0;
    }

    public void addPenny()
    {
        numPennies += 1;
    }

    public void addDime()
    {
        numDimes += 1;
    }

    public void addNickel()
    {
        numNickels += 1;
    }

    public void addQuarter()
    {
        numQuarters += 1;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }

    public int getNumPennies(){
    	return numPennies;
	}

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public int getNumDimes() {
		return numDimes;
	}

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public int getNumNickels() {
		return numNickels;
	}

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }

    public int getNumQuarters() {
		return numQuarters;
	}
}
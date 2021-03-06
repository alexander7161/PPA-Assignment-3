import java.util.List;
import java.util.Random;

/**
 * Class that represents a disease.
 * A disease keeps track of how long it has existed.
 * A disease will not always kill a host.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Disease
{
    // Constants representing configuration information for diseases.
    private static final int STEPS_BEFORE_DEATH = 8;
    private static final double CHANCE_OF_DEATH = 0.5;
    
    // A shared random number generator.
    private static final Random rand = Randomizer.getRandom();
    
    // Tracks how long an animal has been infected.
    private int counter;
    

    /**
     * Constructor for objects of class Disease
     */
    public Disease()
    {
        counter = 0;
    }

    /**
     * @return true if disease has expired.
     */
    public boolean getDiseaseFinished()
    {
        counter++;
        if(counter >= getSTEPS_BEFORE_DEATH())
        {
            //System.out.println("disease done");
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * @return true if disease killed animal.
     */
    public boolean getDiseaseDeath()
    {
        if(rand.nextDouble() <= getCHANCE_OF_DEATH())
        {
            return true;
        }
        else {
            //System.out.println("disease survive");
            return false;
        }
    }
    
    protected int getSTEPS_BEFORE_DEATH()
    {
        return STEPS_BEFORE_DEATH;
    }
    
    protected double getCHANCE_OF_DEATH()
    {
        return CHANCE_OF_DEATH;
    }
}

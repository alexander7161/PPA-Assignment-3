import java.util.List;
import java.util.Iterator;
import java.util.Random;
/**
 * Write a description of class Tiger here.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Tiger extends Predator
{
    // Characteristics shared by all tigers (class variables).

    // The age at which a tiger can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a tiger can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a tiger breeding.
    private static final double BREEDING_PROBABILITY = 0.8;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // This is the number of steps a tiger can go before it has to eat initially.
    private static final int INITIAL_HUNGER_VALUE = 13;
    // This is the food level which a Tiger cannnot eat after because it's full.
    private static final int MAX_FOOD_LEVEL = 30;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a Tiger. A Tiger can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the Tiger will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Tiger(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt( INITIAL_HUNGER_VALUE );
        }
        else {
            age = 0;
            foodLevel =  INITIAL_HUNGER_VALUE ;
        }
    }

    /**
     * @param randomAge If true, the Tiger will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    protected Animal getNewAnimal(boolean randomAge, Field field, Location loc)
    {
        Animal young;
        return young = new Tiger(false, field, loc);
    }

    /**
     * Tiger sleeps between 0 and 6 hours.
     * @param current time.
     * @return true false if tiger is sleeping.
     */
    protected boolean isNotAsleep(int time)
    {
        if( (0 <= time) && (time <= 6)) {
            return false;
        }
        else {
            return true;
        }
    }
    
    protected int getMAX_AGE()
    {
        return MAX_AGE;
    }

    protected int getBREEDING_AGE()
    {
        return BREEDING_AGE;
    }

    protected int getMAX_LITTER_SIZE()
    {
        return MAX_LITTER_SIZE;
    }
    
    /**
     * @return maximum food level.
     */
    protected int getMAX_FOOD_LEVEL()
    {
        return MAX_FOOD_LEVEL;
    }

    protected double getBREEDING_PROBABILITY()
    {
        return BREEDING_PROBABILITY;
    }
}

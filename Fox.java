import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat prey, and die.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Fox extends Predator
{
    // Characteristics shared by all foxes (class variables).

    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a fox can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.4;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;
    // This is the number of steps a fox can go before it has to eat initially.
    private static final int INITIAL_HUNGER_VALUE = 13;
    // This is the food level which a fox cannnot eat after because it's full.
    private static final int MAX_FOOD_LEVEL = 26;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     *
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(INITIAL_HUNGER_VALUE);
        }
        else {
            age = 0;
            foodLevel =  INITIAL_HUNGER_VALUE ;
        }
    }

    /**
     * Creates a new fox object.
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @return animal of type fox.
     */
    protected Animal getNewAnimal(boolean randomAge, Field field, Location loc)
    {
        Animal young;
        return young = new Fox(false, field, loc);
    }

    /**
     * Fox can only eat rabbits and mice.
     * Checks if location contains rabbits or mice.
     * @param location.
     * @return Edible object containing rabbit or mouse if in the location.
     */
    protected Edible getEdible(Location where)
    {
      Object animal = getField().getObjectAt(where);
      Edible prey = null;
      if(animal instanceof Rabbit)
      {
          prey = (Rabbit) animal;
      }
      else if(animal instanceof Mouse)
      {
          prey = (Mouse) animal;
      }
      
      return prey;
    }
    
    /**
     * @return false if it is foggy and fox cannot see.
     */
    protected boolean isFogImmune()
    {
        if(Simulator.getWeather().getFog())
        {
            return false;
        }
        else
        {
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

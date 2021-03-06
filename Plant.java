import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a plant.
 * plants age and die.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Plant extends Actor implements Edible
{
    // Characteristics shared by all plants (class variables).
    private static final int MAX_AGE = 5;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a new plant. A plant may be created with age
     * zero (a new plant) or with a random age.
     *
     * @param randomAge If true, the plant will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Plant(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
           age = rand.nextInt(MAX_AGE);
        }
    }

    /**
     * Plants don't move. They stay stationary and occasionally 
     * die and are replaced by new seedlings.
     * @param newActors A list to return newly created plants.
     */
    public void act(List<Actor> newActors)
    {
        if(Simulator.getWeather().getRaining()) {
            incrementAge();
        }
    }
    
    /**
     * Plants do not die they are replaced by new seedlings
     * in the same position.
     */
    protected void setDead()
    {
        age = 0;
    }
    
    /**
     * @return Maximum age a plant can grow to
     * before dying.
     */
    protected int getMAX_AGE()
    {
        return MAX_AGE;
    }
    
    /**
     * When plants grow they gain nutritional content.
     * The older the plant the more nutritious.
     * @return nutrition value of plant when eaten.
     */
    public int getFOOD_VALUE()
    {
        return age;
    }
    

}

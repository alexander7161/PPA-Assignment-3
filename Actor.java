import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * Abstract class Actor - Entities that act with each step.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public abstract class Actor
{
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Whether the actor is alive or not.
    private boolean alive;
    // Represents how old an actor is.
    protected int age;
    
    // The actor's field.
    private Field field;
    // The actor's position in the field.
    protected Location location;
      
    public Actor(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Check whether the actor is alive or not.
     * @return true if the actor is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }
    
    
    /**
     * Indicate that the actor is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Return the actor's location.
     * @return The actor's location.
     */
    protected Location getLocation()
    {
        return location;
    }

    /**
     * Place the actor at the new location in the given field.
     * @param newLocation The actor's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * @return actor's current field.
     */
    protected Field getField()
    {
        return field;
    }
    
    /**
     * Increase the age.
     * This could result in the actor's death.
     */
    protected void incrementAge()
    {
        age++;
        if(age > getMAX_AGE()) {
            setDead();
        }
    }
    
    /**
     * @return Maximum age of actor.
     */
    protected abstract int getMAX_AGE();
    
    /**
    * Make this actor act - that is: make it do
    * whatever it wants/needs to do.
    * @param newActors A list to receive newly created actors.
    */
    abstract public void act(List<Actor> newActors);
    
}

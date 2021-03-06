
/**
 * Plague is a more deadly disease.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Plague extends Disease
{
    // Constants representing configuration information for plague.
    private static final int STEPS_BEFORE_DEATH = 4;
    private static final double CHANCE_OF_DEATH = 0.9;
    
    protected int getSTEPS_BEFORE_DEATH()
    {
        return STEPS_BEFORE_DEATH;
    }
    
    protected double getCHANCE_OF_DEATH()
    {
        return CHANCE_OF_DEATH;
    }

}

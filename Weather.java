import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * Class that keeps track of the weather.
 * Weather is random
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Weather
{
    // Constants representing configuration information for weather probability.
    private static final double FOG_PROPABILITY = 0.1;
    private static final double RAIN_PROPABILITY = 0.7;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    //True if it is raining.
    private boolean rain;
    //True if it is foggy.
    private boolean fog;

    /**
     * Updates weather based on probability.
     */
    public void step()
    {
        rain= false;
        fog = false;
        
        if(rand.nextDouble() <= FOG_PROPABILITY) {
            fog = true;
        }
        if(rand.nextDouble() <= RAIN_PROPABILITY) {
            rain = true;
        }
    }
    
    /**
     * @return string with current weather conditions.
     */
    public String getWeatherString()
    {
        if(fog && rain)
        {
            return "Fog & Rain";
        }
        else if(fog)
        {
            return "Fog";
        }
        else if (rain)
        {
            return "Rain";
        }
        else {
            return "";
        }
    }
    
    /**
     * @return true if weather is rain.
     */
    public boolean getRaining()
    {
        return rain;
    }
    
    /**
     * @return true if weather is foggy.
     */
    public boolean getFog()
    {
        return fog;
    }
}


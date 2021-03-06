import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A predator-prey simulator, based on a rectangular field
 * containing different animals.
 *
 * @author Alexander Davis and Ans Mohamed .
 * @version 20.2.2018
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 240;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 160;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.05;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.1;
    // The probability that a tiger will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.05;
    // The probability that a squirrel will be created in any given grid position.
    private static final double SQUIRREL_CREATION_PROBABILITY = 0.1;
    // The probability that a mouse will be created in any given grid position.
    private static final double MOUSE_CREATION_PROBABILITY = 0.1;
    
    //Weather object for simulation.
    private static Weather weather = new Weather();
    //the current time of the day in hours.
    private static int time;
    
    // List of actors in the field.
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation. Each step represents 1 hour.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();



    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    public static void main(String args[])
    {
        Simulator simulator = new Simulator();
        simulator.runLongSimulation();
    }

    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        actors = new ArrayList<>();
        field = new Field(depth, width);
        field.createPlantField();

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);
        view.setColor(Tiger.class, Color.RED);
        view.setColor(Squirrel.class, Color.GREEN);
        view.setColor(Mouse.class, Color.YELLOW);
        
        //Exit the simulation when close button pressed.
        view.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
        });    
        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {            
            simulateOneStep();      
            //delay(60);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * actor.
     */
    public void simulateOneStep()
    {
        incrementStep();

        // Provide space for newborn actors.
        List<Actor> newActors = new ArrayList<>();
        
        // Let all actors act.
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            if(actor instanceof Animal)
            {
                Animal animal = (Animal) actor;
                if((rand.nextDouble() <= animal.getPROBABILITY_OF_INFECTION_RANDOM()) && !animal.isDiseased())
                {
                    animal.giveDisease();
                }
            }
            
            actor.act(newActors);
            
            if(! actor.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born actors to the main list.
        actors.addAll(newActors);
        // Update GUI.
        view.showStatus(step, time, weather.getWeatherString(), field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();

        // Show the starting state in the view.
        view.showStatus(step,time, weather.getWeatherString(), field);
    }

    /**
     * Randomly populate the field with animals. 
     * Populate the plantField with plants.
     */
    private void populate()
    {
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    actors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    actors.add(rabbit);
                }
                else if(rand.nextDouble() <= TIGER_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Tiger tiger = new Tiger(true, field, location);
                    actors.add(tiger);
                }
                else if(rand.nextDouble() <= SQUIRREL_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Squirrel squirrel = new Squirrel(true, field, location);
                    actors.add(squirrel);
                }
                else if(rand.nextDouble() <= MOUSE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Mouse mouse = new Mouse(true, field, location);
                    actors.add(mouse);
                }
                // else leave the location empty.
                
                //Create plant for corresponding plantField location.
                Location location = new Location(row, col);
                Plant plant = new Plant(true, field.getPlantField(), location);
                actors.add(plant);
            }
        }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }

    /**
     * Increments step and updates the time. Each step is 1 hour.
     * Also updates the weather.
     *
     */
    private void incrementStep()
    {
        step++;
        time = step%24;
        weather.step();
    }
    
    /**
     * @return current time in hours.
     */
    public static int getTime()
    {
        return time;
    }
    
    /**
     * @return weather object with the current weather conditions.
     */
    public static Weather getWeather()
    {
        return weather;
    }
    
}

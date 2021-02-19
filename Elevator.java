/**
 *
 */

/**
 * @author: Joseph Anyia, Amith Kumar Das Orko, Tolu Ajisola,
 *          Israel Okonkwo, Mehdi Khan
 *
 */


import java.util.HashSet;
import java.util.Set;

public class Elevator {
    public enum State {
        MOVING_UP, MOVING_DOWN, STOPPED
    }

    private int floor;
    private State state;
    private boolean buttonPressed;
    private boolean directionLamp;
    private boolean elevatorLamp;
    private boolean startMotor;
    private boolean openDoors;
    private boolean elevatorNotified;
    private boolean hasArrived;

    @SuppressWarnings({ "unchecked" })
    private Set<Integer> pressedButtons = (Set<Integer>) new HashSet<Integer>();
    private int directionButton;
    private int floorButton;

    public Elevator() {

        state = State.STOPPED;
        buttonPressed = false;
        directionLamp = false;
        elevatorLamp = false;
        startMotor = false;
        openDoors = false;
        elevatorNotified = false;
        hasArrived = false;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
        pressedButtons.remove(floor);
    }

    public void setDirectionButton(int directionButton){
        this.directionButton = directionButton;
    }

    public void setElevatorButton(int floorButton){
        this.floorButton = floorButton;
    }

    public State getState() {
        return state;
    }

    public void setState(State s) {
        state = s;
    }
    public boolean isMoving() {
        return state == State.MOVING_UP || state == State.MOVING_DOWN;
    }

    public void buttonPressed(int i) {
        pressedButtons.add(i);
    }

    public Set<Integer> getButtons() {
        return pressedButtons;
    }

    public synchronized void openDoors(){
        openDoors = true;
        System.out.println("Doors Opening........");
    }

    public synchronized void closeDoors(){
        openDoors = false;
        System.out.println("Doors Closing........");
    }

    public synchronized boolean checkIfArrived(){
        return this.hasArrived;
    }


    public synchronized void turnOnLamps(int destination, int direction){
        this.directionLamp = true;
        this.elevatorLamp = true;
        if(direction == 1){
            System.out.println("lamps "+destination + " and UP are on");
        }
        else{
            System.out.println("lamps "+destination + " and DOWN are on");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void turnOffLamps(int destination, int direction){
        this.directionLamp = false;
        this.elevatorLamp = false;
        System.out.println(destination + " and "+ direction+" lamps are off");

    }

    public synchronized void startMotor(int destination, int direction, int currentElevatorFloor, int floorNumber) {
        System.out.println("Elevator is currently at floor "+currentElevatorFloor);
        while(currentElevatorFloor != floorNumber) {
            if (direction == 1) {
                if (currentElevatorFloor > floorNumber) {
                    this.state = State.MOVING_DOWN;
                    int nextFloor = currentElevatorFloor - 1;
                    System.out.println("elevator is going DOWN to floor"+nextFloor);
                    currentElevatorFloor--;
                }
                if (currentElevatorFloor < floorNumber) {
                    this.state = State.MOVING_UP;
                    int nextFloor = currentElevatorFloor + 1;
                    System.out.println("elevator is going UP to floor"+nextFloor);
                    currentElevatorFloor++;
                }
            } else {
                if (currentElevatorFloor < floorNumber) {
                    this.state = State.MOVING_UP;
                    int nextFloor = currentElevatorFloor + 1;
                    System.out.println("elevator is going UP to floor"+nextFloor);
                    currentElevatorFloor++;
                }
                if (currentElevatorFloor > floorNumber) {
                    this.state = State.MOVING_DOWN;
                    int nextFloor = currentElevatorFloor - 1;
                    System.out.println("elevator is going DOWN to floor"+nextFloor);
                    currentElevatorFloor--;
                }
            }
        }
        if(currentElevatorFloor == floorNumber){
            System.out.println("Elevator has arrived at floor "+floorNumber);
            hasArrived = true;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public String toString() {
        return  "Floor: " + floor + "\n" + "\t State: " + state + "\n";
    }

}


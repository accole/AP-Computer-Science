/*
 * The stop watch class will allow a program to start and stop a stopwatch as
 * well as return the stopwatch as a string, a total ms's, or the hour, min, and 
 * seconds separately.
 * 
 * The System.currentTimeMillis() returns the number of milliseconds since 
 * 2/6/17
 * Adam Cole
 */


public class Stopwatch {

    private long myStarttime;    // in milliseconds
    private long myStoptime;
    private boolean isTiming;

    public Stopwatch() {
        myStarttime = 0;
        myStoptime = 0;
        isTiming = false;
    }           // Create a new stop watch - does not start it

    public void Start() {
        // starts the clock by getting system time
        myStarttime = System.currentTimeMillis();
        isTiming = true;
    }          // Starts the stop watch

    public void Stop() {
        // stops the clock by getting the system time
        if (isTiming) {
            myStoptime = System.currentTimeMillis();
            isTiming = false;
        }
    }           // Stops the stop watch

    public int getHours() {
        // returns the hours of the current time (if not timing)
        return elapsedtime() / 1000 / 60 / 60;
    }       // Gets the current # of Hours elasped

    public int getMinutes() {
        // returns the minutes of the current time
        return elapsedtime() / 1000 / 60 % 60;
    }     // Gets the current # of Minutes elapsed

    public int getSeconds() {
        // returns the seconds of the current time
        return elapsedtime() / 1000 % 60;
    }     // Gets the current # of Seconds elapsed

    public int getMillis() {
        // returns the millis of the current time
        return elapsedtime() % 1000;
    }      // Gets the current # of milliseconds elapsed

    public String toString() {
        // returns a string of the time in hours:minutes:seconds.millis
//        System.out.println("stopwatch start:" + myStarttime + " end:" + myStoptime);
        int millis = getMillis();
        int hrs = getHours();
        int mins = getMinutes();
        int secs = getSeconds();
        return "" + hrs / 10 + hrs % 10 + ":" + mins / 10 + mins % 10 + ":"
                + secs / 10 + secs % 10 + "." + millis / 100 + millis / 10 % 10 + millis % 10;
    }    // returns the time elapsed in HH:MM:SS.MS format

    private int elapsedtime() {
        // returns the time that has elapsed on the stop watch as an int
        if (isTiming) {
            return (int) (System.currentTimeMillis() - myStarttime);
        } else {
            return (int) (myStoptime - myStarttime);
        }
    }
    
    public long totaltime() {
        //returns the total number of milliseconds elapsed
        return (long) elapsedtime();
    }
}

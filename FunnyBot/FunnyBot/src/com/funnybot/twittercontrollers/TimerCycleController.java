/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funnybot.twittercontrollers;

public class TimerCycleController 
{
    private final int convertSecond = 1000;
    private final int convertMinute = 60000;
    private final int convertHour = 3600000;
    
    private int _timerCycle;
    
    private int _seconds;
    private int _minutes;
    private int _hours;
    
    private static TimerCycleController _instance;
    
    /**
     * This constructor makes sure the class can not be an
     * instance and is only instantiated from with in.
     */
    private TimerCycleController()
    {
        _timerCycle = 0; // Resetting the timer
        SetTimerMinute(30); // Default timer set to 30 minutes
    }
    
    /**
     * This method sets the timer in seconds.
     * 
     * @param timer The second timer to set, of type int
     */
    private void SetTimerSecond(int timer)
    {
        _timerCycle += timer * convertSecond;
        _seconds = timer; // Setting the current seconds
    }
    
    /**
     * This method sets the timer in minutes.
     * 
     * @param timer The minute timer to set, of type int
     */
    private void SetTimerMinute(int timer)
    {
        _timerCycle += timer * convertMinute;
        _minutes = timer; // Setting the current minutes
    }
    
    /**
     * This method sets the timer in hours.
     * 
     * @param timer The hour timer to set, of type int
     */
    private void SetTimerHour(int timer)
    {
        _timerCycle += timer * convertHour;
        _hours = timer; // Setting the current hours
    }
    
    /**
     * This method sets the timer for the cycle.
     * 
     * @param seconds The second timer, of type int
     * @param minutes The minute timer, of type int
     * @param hours The hour timer, of type int
     */
    public void SetTimer(int seconds, int minutes, int hours)
    {
        _timerCycle = 0; // Resetting the timer
        SetTimerSecond(seconds); // Setting the seconds
        SetTimerMinute(minutes); // Setting the minutes
        SetTimerHour(hours);     // Setting the hours
    }
    
    /**
     * This method gets the cycle timer value.
     * 
     * @return The cycle timer value, of type int
     */
    public int GetTimer(){ return _timerCycle; }
    
    /**
     * This method gets the current time for seconds.
     * 
     * @return The number of seconds in the timer, of type int
     */
    public int GetSeconds(){ return _seconds; };
    
    /**
     * This method gets the current time for minutes.
     * 
     * @return The number of minutes in the timer, of type int
     */
    public int GetMinutes(){ return _minutes; };
    
    /**
     * This method gets the current time for hours.
     * 
     * @return The number of hours in the timer, of type int
     */
    public int GetHours(){ return _hours; };
    
    /**
     * This method shows the cycle timer.
     * 
     * @return The cycle timer, of type 
     */
    @Override
    public String toString()
    {
        return "Cycle Timer: " + _hours + ":" 
                + _minutes + ":" + _seconds;
    }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new TimerCycleController(); 
    }
    
    /**
     * This method returns the singleton, if the singleton
     * is null then initializes the singleton.
     * 
     * @return The instance of TimeCycleController, of type
     *         TimeCycleController
     */
    public static TimerCycleController GetInstance()
    {
        // Condition to check if singleton is null
        // then initializing the singleton
        if(_instance == null) Initialize();
        
        return _instance;
    }
}

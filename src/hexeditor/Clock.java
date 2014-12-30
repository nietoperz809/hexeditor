/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexeditor;

/**
 *
 * @author Administrator
 */
public class Clock extends Thread
{
    private final Runnable callback;
    private final Object lock = new Object();
    private boolean pauseFlag = true;
    private final int delay;
    private long ticks = 0;
    
    public Clock (Runnable r, int d)
    {
        delay = d;
        callback = r;
        start();
    }
    
    public long getTicks()
    {
        return ticks;
    }
    
    public void doPause()
    {
        synchronized (lock)
        {
            pauseFlag = true;
        }
    }
    
    public void endPause()
    {
        synchronized (lock)
        {
            pauseFlag = false;
            lock.notify();
        }
    }
    
    @Override
    public void run()
    {
        for(;;)
        {   
            try
            {
                if (pauseFlag == true)
                {
                    synchronized (lock)
                    {
                        lock.wait();
                    }
                }
                Thread.sleep (delay);
            }
            catch (InterruptedException ex)
            {
                
            }
            ticks++;
            callback.run();
        }
    }
}

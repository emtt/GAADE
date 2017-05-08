package gaade.mobilize.com.aaade;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Morfo on 07/05/2017.
 */

public class Calculator {

    public long suma(long a, long b){
        return a+b;
    }

    public long resta(long a, long b){
        return a-b;
    }

    public long division(long a, long b){
        return a/b;
    }

    public double multi(double a, double b){
        return a*b;
    }

    @Test
    public void sumOk(){
        assertEquals(10, suma(5,5));
    }

    @Test
    public void restaOk(){
        assertEquals(5, resta(10,5));
    }

    @Test
    public void divOk(){
        assertEquals(2, division(10,5));
    }

    @Test
    public void multOk(){
        assertEquals(5, division(25,5));
    }

}

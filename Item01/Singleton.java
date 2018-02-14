/**
 * 
 */
package Item01;

/**
 * @author : Juno Hwang (sbukkk)
 * @since   : 2018. 2. 14.
 *  https://github.com/bactoria/Effective-Java-2nd/
 *
 */
public class Singleton{
    private static final Singleton INSTANCE = new Singleton();
   
    private Singleton(){}

    public static Singleton getSingleton(){
        return INSTANCE;
    }
}
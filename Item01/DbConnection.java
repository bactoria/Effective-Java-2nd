/**
 * 
 */
package Item01;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Juno Hwang (sbukkk)
 * @since   : 2018. 2. 14.
 *  https://github.com/bactoria/Effective-Java-2nd/
 *
 */
public class DbConnection{
	   private static final int MAX_CONNS = 100;
	   private static int totalConnections = 0;

	   private static Set<DbConnection> availableConnections = new HashSet<DbConnection>();

	   private DbConnection(){
	     // ...
	     totalConnections++;
	   }

	   //static factory method
	   public static DbConnection getDbConnection(){

	     if(totalConnections < MAX_CONNS){
	       return new DbConnection();

	     }else if(availableConnections.size() > 0){
	         DbConnection dbc = availableConnections.iterator().next();
	         availableConnections.remove(dbc);
	         return dbc;

	     }else {
	         throw new NoDbConnections();
	     }
	   }

	   public static void returnDbConnection(DbConnection dbc){
	     availableConnections.add(dbc);
	     //...
	   }
	}

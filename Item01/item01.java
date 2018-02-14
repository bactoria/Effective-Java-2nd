/**
 * 
 */
package Item01;


/**
 * @author : Juno Hwang (sbukkk)
 * @since   : 2018. 2. 14.
 *  https://github.com/bactoria/Effective-Java-2nd
 *
 */
public class item01 {
	
	public static void main(String[] args) {

		Member member1 = new Member("junoh" , "01098765432");
		Member member2 = new Member("sooduck");
		
		SfmMember sfmmember1 = SfmMember.new_Name_Phone("junoh" , "01098765432");
		SfmMember sfmmember2 = SfmMember.new_Name("sooduck");
	}
	
}

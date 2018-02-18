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
public class Member {
	
	private String memberName;
	private String memberPhone;
	
	//member1 持失切
	public Member(String memberName, String memberPhone) {
		this.memberName = memberName;
		this.memberPhone = memberPhone;
	}

	//member2 持失切
	public Member(String memberName) {
		this.memberName = memberName;
	}
	
}

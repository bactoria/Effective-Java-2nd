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
public class SfmMember {
	
	private String memberName;
	private String memberPhone;

	//member1ÀÇ static factory method
	public static SfmMember new_Name_Phone(String memberName, String memberPhone) {
		return new SfmMember(memberName, memberPhone);
	}
	
	//member2ÀÇ static factory method
	public static SfmMember new_Name(String memberName) {
		return new SfmMember(memberName);
	}
	
	private SfmMember(String memberName, String memberPhone) {
		this.memberName = memberName;
		this.memberPhone = memberPhone;
	}

	private SfmMember(String memberName) {
		this.memberName = memberName;
	}
	
}

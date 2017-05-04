import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class TokenList {

	private static TokenList instance = new TokenList();
	
	//here we create a a treeSet to store all token passed to the server. to in sure that the set can only be accessed by on server 
	//at a time we use Collections.synchronizedSortedSet
	//this combined with the tresSet being contained in a singleton class insures that no matter how many clients there are and when 
	//when try to access the set they will always be using the same most up to date TreeSet and that only one of them can access it at a time
	private SortedSet<String>  tokentree = Collections.synchronizedSortedSet(new TreeSet<String>());
	
	//private constructor
	private TokenList(){}
	
	//method that returns this object. This and the private constructor insure there will only ever be only instance of the class.
	public static TokenList getInstance(){
		return instance;
	}
	
	//this method is a getter to allow access to the tokentree
	public SortedSet<String> getTokentree(){
		return tokentree;
	}
}

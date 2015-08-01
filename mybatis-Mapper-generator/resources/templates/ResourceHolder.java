package templates;

public class ResourceHolder {
	public static String path() {
		return ResourceHolder.class.getResource("").getPath();
	}
	
	public static String path2() {
		return ResourceHolder.class.getResource("").toString();
	}
}

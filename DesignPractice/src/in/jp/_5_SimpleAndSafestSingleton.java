package in.jp;

public class _5_SimpleAndSafestSingleton {

	private _5_SimpleAndSafestSingleton() {}
	
	private static class Holder {
		private static final _5_SimpleAndSafestSingleton INSTANCE = new _5_SimpleAndSafestSingleton();
	}
	
	public static _5_SimpleAndSafestSingleton getInstance() {
        return Holder.INSTANCE;
    }
	
	public static void main(String[] args) {
		_5_SimpleAndSafestSingleton s1 = _5_SimpleAndSafestSingleton.getInstance();
		_5_SimpleAndSafestSingleton s2 = _5_SimpleAndSafestSingleton.getInstance();
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}

}

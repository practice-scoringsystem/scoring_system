package common;

//Stringをintに変換するメソッド
public class CommonUtil {

	public CommonUtil(){
	}

	public int[] parseInts(String[] s){

		  int[] x = new int[s.length];
		  for(int i = 0; i < s.length; i++){
		    x[i] = Integer.parseInt(s[i]);
		  }
		  return x;
		}

}
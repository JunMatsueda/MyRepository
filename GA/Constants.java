/**
*定数を定義するクラス Constants
*
*/
public class Constants {
	public static final int CHILDREN_NUM = 100; //子の個体の数
	public static final int PARENTS_NUM = 10 ; //親の個体の数
	public static final int PARAMETER_NUM = 10; //個体のパラメータ数
	public static final double MAX = 100.0; //パラメータの最大値
	public static final double MIN = -100.0; //パラメータの最小値
	public static final double RANGE = 0.5; //次世代作成時のパラメータの変更範囲
	public static final int TRIAL_NUM = 100000; //試行回数
	public static final int SIMULATOR_NUM = 0; //使用するシミュレータの番号
	public static final int CLOCK_UNIT = 0; //時間の単位を決定する　0:ミリ秒、1:秒、2:分、3:時間
	public static final double ODDS = 1.0/PARAMETER_NUM;

}

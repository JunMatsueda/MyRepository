
import java.util.ArrayList;
import java.util.List;

public class Example {

	public static void main(String[] args)  throws CloneNotSupportedException{
		int parentsNum = Constants.PARENTS_NUM;
		int childrenNum = Constants.CHILDREN_NUM;
		int trialNum = Constants.TRIAL_NUM;
		int parameterNum = Constants.PARAMETER_NUM;
		int simulatorNum = Constants.SIMULATOR_NUM;
		double max = Constants.MAX;
		double min = Constants.MIN;
		Boolean leargeOneIsGood = false;
		List<Individual> parents = Util.makeParetent(parentsNum, max, min, parameterNum, simulatorNum);
		List<Individual> children;
		Double range = Constants.RANGE;
		List<Double> goodEvaluations = new ArrayList<Double>();
		String filename = "test.csv";
		List<Double> averageList = new ArrayList<Double>();
		long start;
		long end;

		start = System.currentTimeMillis();

		children = Util.selectChildren(parents, childrenNum, leargeOneIsGood);
		Util.printEvaluate(children);
		averageList.add(Util.getAverage(children));
		System.out.println("__________________________________");
		goodEvaluations.add(children.get(0).getEvaluation());

		for(int i=1; i < trialNum; i++){
			parents.clear();
			parents = Util.makeNewParents(parentsNum, children, range, simulatorNum);

			List<Individual> idList = new ArrayList<Individual>(parents);
			idList.addAll(children);

			children.clear();
			children = new ArrayList<Individual>(Util.selectChildren(idList, childrenNum, leargeOneIsGood));
			averageList.add(Util.getAverage(children));

			goodEvaluations.add(children.get(0).getEvaluation());
			//System.out.println(children.get(0).getEvaluation());
		}
		Util.printEvaluate(children);
		Util.writeCSV(goodEvaluations, averageList, filename);
		end = System.currentTimeMillis();
		switch (Constants.CLOCK_UNIT){
			case 0:
				System.out.println("実行にかかった時間は " + (end - start) + " ミリ秒です。");
				break;
			case 1:
				System.out.println("実行にかかった時間は " + (end - start)/1000.0 + " 秒です。");
				break;
			case 2:
				System.out.println("実行にかかった時間は " + (end - start)/60000.0 + " 分です。");
				break;
			case 3:
				System.out.println("実行にかかった時間は " + (end - start)/3600000.0 + " 時間です。");
				break;
		}
		System.out.println("実行にかかった時間は " + (end - start)/1000.0 + " 秒です。");
	}
}

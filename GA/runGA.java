
import java.util.ArrayList;
import java.util.List;

public class runGA {

	public static void main(String[] args)  throws CloneNotSupportedException{
		int childrenNum = Constants.CHILDREN_NUM;
		int parentsNum = Constants.PARENTS_NUM;
		int trialNum = Constants.TRIAL_NUM;
		int parameterNum = Constants.PARAMETER_NUM;
		int simulatorNum = Constants.SIMULATOR_NUM;
		double max = Constants.MAX;
		double min = Constants.MIN;
		Boolean leargeOneIsGood = false;
		List<Individual> children = GAUtil.makeParetent(childrenNum, max, min, parameterNum, simulatorNum);
		List<Individual> parents;
		Double range = Constants.RANGE;
		List<Double> goodEvaluations = new ArrayList<Double>();
		String filename = "good.csv";
		String newFile = "allEV.csv";
		List<Double> averageList = new ArrayList<Double>();
		List<Double> allEvaluation = new ArrayList<Double>();
		long start;
		long end;

		start = System.currentTimeMillis();

		parents = GAUtil.selectChildren(children, parentsNum, leargeOneIsGood);
		GAUtil.printEvaluate(parents);
		averageList.add(GAUtil.getAverage(parents));
		System.out.println("__________________________________");
		goodEvaluations.add(parents.get(0).getEvaluation());

		for(Individual id: children){
			allEvaluation.add(id.getEvaluation());
		}
		
		for(int i=1; i < trialNum; i++){
			children.clear();
			children = GAUtil.makeNewParents(childrenNum, parents, range, simulatorNum);

			List<Individual> idList = new ArrayList<Individual>(children);
			idList.addAll(parents);

			parents.clear();
			parents = new ArrayList<Individual>(GAUtil.selectChildren(idList, parentsNum, leargeOneIsGood));
			averageList.add(GAUtil.getAverage(parents));

			goodEvaluations.add(parents.get(0).getEvaluation());
			//System.out.println(parents.get(0).getEvaluation());
			//parents.get(0).printParameter();
			//System.out.println("");

			for(Individual id: children){
				allEvaluation.add(id.getEvaluation());
			}

		}
		GAUtil.printEvaluate(parents);
		GAUtil.writeCSV(goodEvaluations, averageList, filename);
		GAUtil.writeCSV(allEvaluation, newFile);
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

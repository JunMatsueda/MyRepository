
import java.util.ArrayList;
import java.util.List;



public class Example {

	public static void main(String[] args)  throws CloneNotSupportedException{
		// TODO 自動生成されたメソッド・スタブ
		int parentsNum = Constants.PARENTS_NUM;
		int childrenNum = Constants.CHILDREN_NUM;
		int trialNum = Constants.TRIALN_NUM;
		int parameterNum = Constants.PARAMETER_NUM;
		int simulatorNum = Constants.SIMULATOR_NUM;
		double max = Constants.MAX;
		double min = Constants.MIN;
		Boolean leargeOneIsGood = false;
		List<Double> parentsEvaluations = new ArrayList<Double>();
		List<Double> childrenEvaluations = new ArrayList<Double>();
		List<Individual> parents = Util.makeParetent(parentsNum, max, min, parameterNum);
		List<Individual> children;
		Double range = Constants.RANGE;

		parentsEvaluations = Util.doSimurate(parents, simulatorNum);
		//List<Integer> selectedIndex = Util.selectIndividual(parentsEvaluations, childrenNum, leargeOneIsGood);
		children = Util.selectChildren(parentsEvaluations, parents, childrenNum, leargeOneIsGood);
		List<Integer> parentsIndex = new ArrayList<Integer>();
		childrenEvaluations = Util.doSimurate(children, simulatorNum);

/*
		for(Individual child: children){
			int index = parents.indexOf(child);
			childrenEvaluations.add(parentsEvaluations.get(index));
			parentsEvaluations.remove(parentsEvaluations.get(index));
			parents.remove(child);
		}*/


		for(int i=1; i < trialNum; i++){
			parents.clear();
			for(int k = 0; k<parentsNum; k++){
				int index = (int )(children.size() * Math.random());
				Individual id = children.get(index).clone();
				id.update(range);
				parents.add(id);
				//System.out.println("index = \t"	+ index + "      parent = \t" +children.get(index));
			}
			/*
			for(Individual id: parents){
				id.update(range);
			}*/
			parentsEvaluations.clear();
			parentsEvaluations = Util.doSimurate(parents, simulatorNum);
			//System.out.println("parentsEvaluationsSize = "+ parentsEvaluations.size());

			List<Double> evaluations = new ArrayList<Double>(parentsEvaluations);
			//System.out.println("main = "+parents + "		clone = "+evaluations);
			evaluations.addAll(childrenEvaluations);
			//System.out.println("evaluations = "+ evaluations.size());

			List<Individual> idList = new ArrayList<Individual>(parents);
			idList.addAll(children);
			//for(int j = 0;j<evaluations.size();j++){
				//System.out.println("ev = "+evaluations.get(j));
				//System.out.println("id = "+idList.get(j));

			//}
			//System.out.println("idList = "+ idList.size());
			//System.out.println("__________________________________");

			//System.out.println(evaluations.size());

			//System.out.println("__________________________________");

			//System.out.println("__________________________________");

			children.clear();
			children = new ArrayList<Individual>(Util.selectChildren(evaluations, idList, childrenNum, leargeOneIsGood));
			childrenEvaluations.clear();
			//System.out.println(childrenEvaluations.size());

			//childrenEvaluations = Util.doSimurate(children, simulatorNum);
			childrenEvaluations = Util.selectEvaluation(evaluations, childrenNum, leargeOneIsGood);
			//Util.printEvaluation(childrenEvaluations);

			//System.out.println(childrenEvaluations.size());

			/*
			for(Individual child: children){
				int index = idList.indexOf(child);
				childrenEvaluations.add(evaluations.get(index));
				evaluations.remove(index);
				idList.remove(index);
			}
			*/
			//System.out.println("childrenSize = "+ children.size());
			//System.out.println("childrenEvaluationsSize = "+ childrenEvaluations.size());


		}

		Util.printEvaluation(childrenEvaluations);
	}

}

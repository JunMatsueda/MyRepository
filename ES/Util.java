import java.util.List;
import java.util.ArrayList;


public class Util  extends Object implements Cloneable{

	//修正すべし
	public static List<Individual> selectChildren(List<Double> evaluations, List<Individual> parents, int selectNum, Boolean leargeOneIsGood)throws CloneNotSupportedException{

		List<Individual> selections = new ArrayList<Individual>();
		List<Individual> idList = new ArrayList<Individual>();
		List<Double> evList = new ArrayList<Double>(evaluations);
		for(Individual id: parents){
			idList.add(id.clone());
		}
		System.out.println("__________________________________");
		//System.out.println("goodEvaluation");
		for(int i = 0; i<selectNum; i++){
			double goodEvaluation= evList.get(0);
			//System.out.println("evaluation"+i+" = "+ goodEvaluation);
			if(leargeOneIsGood){
				for(int k =0; k<evList.size();k++){
					if(goodEvaluation < evList.get(k)){
						goodEvaluation = evList.get(k);
					}
				}
			}else{
				for(int k=0; k<evList.size();k++){
					if(goodEvaluation > evList.get(k)){
						goodEvaluation = evList.get(k);
					}
				}
			}
			int selectedIndex=evList.indexOf(goodEvaluation);
			Individual goodID = idList.get(selectedIndex).clone();
			//System.out.println("index = "+selectedIndex);

			selections.add(goodID);
			//System.out.println("ID = "+goodID);
			System.out.println("EV = "+goodEvaluation);
			//goodID.printParameter();
			//System.out.println(Simulator.evaluate(0,goodID.getParameter()));
			//System.out.println(Simulator.evaluate(0,goodID.getParameter()));
			idList.remove(goodID);
			evList.remove(goodEvaluation);
		}

		return selections;
	}
	public static List<Double> selectEvaluation(List<Double> evaluations, int selectNum, Boolean leargeOneIsGood){

		List<Double> selections = new ArrayList<Double>();
		List<Double> evList = new ArrayList<Double>(evaluations);
		//System.out.println("__________________________________");
		//System.out.println("goodEvaluation");
		for(int i = 0; i<selectNum; i++){
			double goodEvaluation= evList.get(0);
			//System.out.println("evaluation"+i+" = "+ goodEvaluation);
			if(leargeOneIsGood){
				for(double evaluation: evList){
					if(goodEvaluation < evaluation){
						goodEvaluation = evaluation;
					}
				}
			}else{
				for(Double evaluation: evList){
					if(goodEvaluation > evaluation){
						goodEvaluation = evaluation;
					}
				}
			}


			selections.add(goodEvaluation);
			//System.out.println("f(x) = "+goodEvaluation);
			//goodID.printParameter();
			evList.remove(goodEvaluation);
		}

		return selections;
	}
	public static List<Individual> makeParetent(int parentNum, double max, double min, int parameterNum){
		List<Individual> parents = new ArrayList<Individual>();
		for(int i = 0; i < parentNum; i++){
			parents.add(new Individual(parameterNum, max,min));
		}
		return parents;
	}

	public static void printEvaluation(List<Double> evaluations){
		Integer index=0;
		for(Double evaluation: evaluations){
			System.out.println("f(x"+index+") = \t"	+ evaluation);
			index++;
		}
		return;
	}

	public static List<Double> doSimurate(List<Individual> idList, int simurateNum){
		List<Double> results = new ArrayList<Double>();
		//System.out.println("__________________________________");
		for(int i=0;i<idList.size();i++){
			Double evaluation=Simulator.evaluate(simurateNum, idList.get(i).getParameter());
			results.add(evaluation);
			//System.out.println(id.getParameter());
			//id.printParameter();
		}
		return results;
	}
}

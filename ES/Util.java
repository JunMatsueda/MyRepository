import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class Util  extends Object implements Cloneable{

	public static List<Individual> selectChildren(List<Individual> parents, int selectNum, Boolean leargeOneIsGood)throws CloneNotSupportedException{
		List<Individual> selections = new ArrayList<Individual>();
		List<Individual> idList = new ArrayList<Individual>(parents);

		for(int i = 0; i<selectNum; i++){
			Individual goodID = idList.get(0);
			double goodEvaluation= idList.get(0).getEvaluation();
			if(leargeOneIsGood){
				for(int k =0; k<idList.size();k++){
					if(goodEvaluation < idList.get(k).getEvaluation()){
						goodEvaluation = idList.get(k).getEvaluation();
						goodID = idList.get(k);
					}
				}
			}else{
				for(int k=0; k<idList.size();k++){
					if(goodEvaluation > idList.get(k).getEvaluation()){
						goodEvaluation = idList.get(k).getEvaluation();
						goodID = idList.get(k);
					}
				}
			}
			int selectedIndex=idList.indexOf(goodID);

			selections.add(goodID.clone());

			idList.remove(selectedIndex);
		}

		return selections;
	}


	public static List<Individual> makeParetent(int parentNum, double max, double min, int parameterNum, int simulatorNum){
		List<Individual> parents = new ArrayList<Individual>();
		for(int i = 0; i < parentNum; i++){
			parents.add(new Individual(parameterNum, max,min, simulatorNum));

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

	public static void printEvaluate(List<Individual> idList){
		Integer index=0;
		for(Individual id: idList){
			System.out.println("f(x"+index+") = \t"	+ id.getEvaluation());
			index++;
		}
		return;
	}

	public static List<Double> doSimurate(List<Individual> idList, int simurateNum){

		List<Double> results = new ArrayList<Double>();
		for(int i=0;i<idList.size();i++){
			Double evaluation=Simulator.evaluate(simurateNum, idList.get(i).getParameter());
			results.add(evaluation);

		}

		return results;
	}

	public static void writeCSV(List<Double> aEvaluations, List<Double> anAverageList, String filename){
		try{
			File file = new File(filename);

			if (checkBeforeWritefile(file)){
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				pw.println("世代, 最高評価, 評価平均");
				for(Integer i = 0; i < aEvaluations.size(); i++){
					pw.println(i+1 +","+aEvaluations.get(i)+","+anAverageList.get(i));
				}
				pw.close();
			}else{
				System.out.println("ファイルに書き込めません");
			}
		}catch(IOException e){
			System.out.println(e);
		}
	}

	private static boolean checkBeforeWritefile(File file){
		if (file.exists()){
			if (file.isFile() && file.canWrite()){
				return true;
			}
		}else{
			try{
				file.createNewFile();
				return true;
			}catch(IOException e){
				System.out.println(e);
			}
		}

		return false;
	}
	public static List<Individual> makeNewParents(int parentsNum, List<Individual> children, double range, int simulatorNum)throws CloneNotSupportedException{
		List<Individual> newParents = new ArrayList<Individual>();
		for(int k = 0; k<parentsNum; k++){
			int index = (int )(children.size() * Math.random());
			Individual id = children.get(index).clone();
			id.update(range);
			id.setEvaluation(Simulator.evaluate(simulatorNum, id.getParameter()));
			newParents.add(id);
		}
		return newParents;
	}

	public static Double getAverage(List<Individual> idList){
		Double sum=0.0;
		for(Individual id: idList){
			sum += id.getEvaluation();
		}
		return sum / idList.size();
	}
}

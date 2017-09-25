import java.util.List;
import java.util.ArrayList;

public class Test{
  public static void main(String[] args) throws CloneNotSupportedException{
    int parentsNum = Constants.PARENTS_NUM;
    int childrenNum = Constants.CHILDREN_NUM;
    int trialNum = Constants.TRIALN_NUM;
    int parameterNum = Constants.PARAMETER_NUM;
    int simulatorNum = Constants.SIMULATOR_NUM;
    double max = Constants.MAX;
    double min = Constants.MIN;
    Boolean leargeOneIsGood = false;

    List<Individual> aList = Util.makeParetent(parentsNum, max, min, parameterNum);
    List<Individual> anotherList = new ArrayList<Individual>();
    for(Individual id: aList){
      Individual anId = id.clone();
      anId.update(1.0);
      anotherList.add(anId);
    }
    anotherList.get(0).update(1.0);
    aList.get(0).update(1.0);
    aList.get(0).printParameter();
    anotherList.get(0).printParameter();

  }
}

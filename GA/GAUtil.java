
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GAUtil extends Util{

  public static List<Individual> makeNewParents(int parentsNum, List<Individual> children, double range, int simulatorNum)throws CloneNotSupportedException{
    List<Individual> newParents = new ArrayList<Individual>();

    for(int k = 0; k<parentsNum; k++){
      int fatherIndex= (int )(children.size() * Math.random());
      int motherIndex= (int )(children.size() * Math.random());
      Individual mother = children.get(motherIndex).clone();
      Individual father = children.get(fatherIndex).clone();
      Gene child = new Gene();
      Double smallParam;
      Double bigParam;

      for(Integer i = 0; i<Constants.PARAMETER_NUM;i++){
        if(mother.get(i) < father.get(i)){
          smallParam = mother.get(i);
          bigParam = father.get(i);
        }else{
          smallParam = father.get(i);
          bigParam = mother.get(i);
        }

        if(Math.random() <= Constants.ODDS){
          child.setRandom(i);
        }else{
          Double L = bigParam - smallParam;
          Double newParam = (smallParam - L * range) + (((bigParam + L * range)-(smallParam - L * range)) * Math.random());
          if (newParam < Constants.MIN){
            newParam = Constants.MIN;
          }else if(newParam > Constants.MAX){
            newParam = Constants.MAX;
          }
          child.setParam(i, newParam);
        }

      }
      //id.update(range);
      child.setEvaluation(Simulator.evaluate(simulatorNum, child.getParameter()));
      newParents.add(child);
      /*
      System.out.println("----fatherParam----");
      father.printParameter();
      System.out.println("----motherParam----");
      mother.printParameter();
      System.out.println("----childParam----");
      child.printParameter();
      */
    }
    return newParents;
  }

}

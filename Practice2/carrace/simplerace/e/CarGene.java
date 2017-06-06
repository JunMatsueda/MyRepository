package simplerase.e;

import GALib.GA;
import GALib.Gene;
import GALib.Sortable;
import GALib.QSort;


/**
車の遺伝子情報CarGene
**/

public class CarGene extends Gene {

  String target;

  /**
  生成
  */
  public TargetStrGene(String target){
    super(target.length());
    this.target=target;
  }
  public TargetStrGene(TargetStrGene g){
    super(g);
    target=g.target;
  }

  /**
  複製
  */
  public Object clone() throws CloneNotSupportedException {
    return new TargetStrGene(this);
  }

  /**
  適応度の計算
  完全に一致した場合が 1 で、
  違うほど 0 に近づきます。
  */
  public void calcFitness(){
    double s=0;
    int n=target.length();
    for(int i=0;i<n;i++){
      int d=target.charAt(i)-chromosome[i];
      s+=d*d;
    }
    fitness=n/(n+s);
  }

  public void scoreCalcurator(double ave, double min, double max){
    fitness = ave*2 + min * max;
  }
  /**
  文字列化
  表示可能文字ならばその文字を表示します。
  そうでない場合は '.' を表示します。
  */
  public String toString(){
    char[] c=new char[chromosome.length];
    for(int i=0;i<c.length;i++){
      if(Character.isLetterOrDigit((char)chromosome[i]))
      c[i]=(char)chromosome[i];
      else
      c[i]='.';
    }
    return new String(c);
  }
}

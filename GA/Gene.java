public class Gene extends Individual{

  public Gene(int intNum, double maxNum, double minNum){
    super(intNum, maxNum, minNum);
  }

  public Gene(){
    elementNum = Constants.PARAMETER_NUM;
    parameter = new double[elementNum];
    max = Constants.MAX;
		min = Constants.MIN;
    evaluation = 0.0;
  }

  public void setParam(Integer index, Double value){
    parameter[index] = value;
    return;
  }

  /**
	*@throws CloneNotSupportedException
	*
	*/
  @Override
	public Gene clone() throws CloneNotSupportedException
	{
		return (Gene)super.clone();
	}
}

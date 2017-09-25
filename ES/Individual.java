public class Individual extends Object  implements Cloneable{
	protected double[] parameter;
	protected double max;
	protected double min;
	protected int elementNum;

	public Individual(int intNum, double maxNum, double minNum){
		elementNum = intNum;
		parameter = new double[elementNum];
		max = maxNum;
		min = minNum;
		this.makeFirstGeneration();
	}

	private void makeFirstGeneration(){
		for(int i = 0; i < elementNum; i++){
			parameter[i] = min + (max - min) * Math.random();
		}
		return;
	}

	protected void update(double range){
		//System.out.println("__________________________________");
		//Individual id = (Individual) this.clone();

		for(int i = 0; i < elementNum; i++){
			this.parameter[i]= (this.parameter[i] - range) + 2.0 * range * Math.random();
			if(this.parameter[i] > max){
				this.parameter[i] = max;
			}else if(this.parameter[i] < min){
				this.parameter[i] = min;
			}
			//System.out.println(parameter[i]);
		}
		return;
	}
	protected void update(double[] range){
		for(int i = 0; i < elementNum; i++){
			parameter[i] = (parameter[i] - range[i]) + 2.0 * range[i] * Math.random();
			if(parameter[i] > max){
				parameter[i] = max;
			}else if(parameter[i] < min){
				parameter[i] = min;
			}
		}
		return;
	}

	protected void update(int index, double range){
		parameter[index] = (parameter[index] - range) + 2 * range * Math.random();
		if(parameter[index] > max){
			parameter[index] = max;
		}else if(parameter[index] < min){
			parameter[index] = min;
		}
		return;
	}

	public int size(){
		return elementNum;
	}

	public double get(int index){
		return this.parameter[index];
	}

	public double[] getParameter(){
		return this.parameter;
	}

	public void printParameter(){
		Integer index = 0;
		for(double param: parameter){
			System.out.println("param"+index+" = \t"	+ param);
			index++;
		}
		return;
	}

	/**
	*@throws CloneNotSupportedException
	*
	*/
	public Individual clone() throws CloneNotSupportedException
	{
		Individual anIndividual = (Individual)super.clone();
		anIndividual.parameter = this.parameter.clone();


		return anIndividual;
	}
}

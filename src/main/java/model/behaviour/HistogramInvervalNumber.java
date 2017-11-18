package model.behaviour;

/**
 * Created by bartoszpietrzak on 18/11/2017.
 */
public enum HistogramInvervalNumber
{
	FIVE(5),
	TEN(10),
	FIFTEEN(15),
	TWENTY(20);

	private int counter;

	HistogramInvervalNumber(int counter)
	{
		this.counter = counter;
	}

	public int getCounter()
	{
		return this.counter;
	}
}

package model.response;

import org.apache.commons.math.complex.Complex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Data
@Builder
public class ResolveSignalResponse
{
	private SignalParametersResponse signalParametersResponse;

	/**
	 * Calculation results
	 */
	private Complex averageSignalValue;
	private Complex absoluteAverageSignalValue;
	private Complex signalPower;
	private Complex signalVariance;
	private Complex signalRootMeanSquareValue;

	@AllArgsConstructor
	@Getter
	@Setter
	public class SignalParametersResponse
	{
		/**
		 * Parameters from UI
		 */
		private int signalId;
		private Complex signalSamplingRate;
		private Complex initialTime;
		private Complex duration;

		@Override
		public String toString()
		{
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(signalId)
					.append(";")
					.append(signalSamplingRate.getReal())
					.append(";")
					.append(initialTime.getReal())
					.append(";")
					.append(duration.getReal())
					.append(";");

			return stringBuilder.toString();
		}
	}
}

package service.response;

import lombok.*;
import org.apache.commons.math.complex.Complex;

/**
 * Created by bartoszpietrzak on 27/10/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class ResolveSignalResponse
{
    public ResolveSignalResponse(){}

    private SignalParametersResponse signalParametersResponse;

    /**
     * Calculation results
     */
    private Complex averageSignalValue;
    private Complex absoluteAverageSignalValue;
    private Complex signalPower;
    private Complex signalVariance;
    private Complex signalRootMeanSquareValue;

    /**
     * TODO Move it to another, reusable structure
     */
    @AllArgsConstructor
    @Getter
    @Setter
    public class SignalParametersResponse
    {
        /**
         * Parameters from UI
         */
        private String signalId;
        private Complex signalSamplingRate;
        private Complex initialTime;
        private Complex duration;
        private String signalType;

        @Override
        public String toString()
        {
            return String.valueOf(signalId) + "; S-ing rate: " + signalSamplingRate.getReal() + " Init. time: " +
                    initialTime.getReal() + " Duration: " + duration.getReal() + " Type: " + signalType;
        }
    }
}

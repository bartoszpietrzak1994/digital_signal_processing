package service.response;

import org.apache.commons.math.complex.Complex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by bartoszpietrzak on 13/11/2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignalsCalculationResponse
{
    private SignalParametersResponse signalParametersResponse;

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
        private boolean isWindowed;

        @Override
        public String toString()
        {
            return String.valueOf(signalId) + ";" + signalSamplingRate.getReal() + ";" + initialTime.getReal() + ";" +
                    duration.getReal() + ";" + signalType + ";" + (isWindowed ? "[W]" : "");
        }
    }
}

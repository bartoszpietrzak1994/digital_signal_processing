package service.response;

import org.apache.commons.math.complex.Complex;

import lombok.Builder;
import lombok.Data;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Data
@Builder
public class SignalQuantizationResponse
{
	private Complex SNR;
	private Complex MSE;
	private Complex PSNR;
	private Complex MD;

	@Override
	public String toString()
	{
		return "SNR: " + SNR.getReal() + ", MSE: " + MSE.getReal() + ", PSNR: " + PSNR.getReal() + ", MD: " + MD.getReal();
	}
}

package utils.quantization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.QuantizationException;
import model.behaviour.QuantizationType;
import model.quantization.SignalQuantization;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class SignalQuantizationResolver
{
	@Autowired
	private List<SignalQuantization> quantizationList;

	public SignalQuantization resolve(QuantizationType quantizationType) throws QuantizationException
	{
		return quantizationList.stream()
				.filter(signalQuantization -> quantizationType.equals(signalQuantization.getQuantizationType()))
				.findFirst()
				.orElseThrow(() -> QuantizationException.quantizationTypeNotSupported(quantizationType.name()));
	}
}

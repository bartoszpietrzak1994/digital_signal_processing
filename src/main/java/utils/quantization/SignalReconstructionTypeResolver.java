package utils.quantization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.QuantizationException;
import model.behaviour.SignalReconstructionType;
import model.reconstruction.SignalReconstruction;

/**
 * Created by bartoszpietrzak on 07/12/2017.
 */
@Component
public class SignalReconstructionTypeResolver
{
	@Autowired
	public List<SignalReconstruction> signalReconstructionList;

	public SignalReconstruction resolve(SignalReconstructionType signalReconstructionType) throws QuantizationException
	{
		return signalReconstructionList.stream()
				.filter(signalReconstruction -> signalReconstruction.getReconstructionType().equals(signalReconstructionType))
				.findFirst()
				.orElseThrow(() -> QuantizationException.signalReconstructionNotSupported(signalReconstructionType.name()));
	}
}
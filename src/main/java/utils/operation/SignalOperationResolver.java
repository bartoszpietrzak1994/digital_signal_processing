package utils.operation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import model.behaviour.SignalOperation;

/**
 * Created by bartoszpietrzak on 14/11/2017.
 */
@Component
public class SignalOperationResolver
{
	@Autowired
	private List<SignalsOperationsCalculator> operations;

	public SignalsOperationsCalculator resolveOperationByType(String operationType) throws SignalParametersException
	{
		SignalOperation signalOperation = SignalOperation.valueOf(operationType);

		Optional<SignalsOperationsCalculator> operationOptional = operations.stream()
				.filter(operation -> operation.getSignalOperation().equals(signalOperation))
				.findFirst();

		if (!operationOptional.isPresent())
		{
			throw SignalParametersException.operationTypeNotSupported(signalOperation.name());
		}

		return operationOptional.get();
	}
}

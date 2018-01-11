package service;

import exception.QuantizationException;
import exception.SignalRepositoryException;
import model.behaviour.QuantizationType;
import model.quantization.SignalQuantization;
import model.signal.base.Signal;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SignalRepository;
import service.client.QuantizationService;
import service.response.SignalQuantizationResponse;
import utils.calculator.SignalReconstructionParametersCalculator;
import utils.quantization.SignalQuantizationResolver;

@Component
public class QuantizationServiceImpl implements QuantizationService
{
    @Autowired
    private SignalRepository signalRepository;

    @Autowired
    private SignalQuantizationResolver signalQuantizationResolver;

    @Autowired
    private SignalReconstructionParametersCalculator reconstructionParametersCalculator;

    @Override
    public SignalQuantizationResponse performSignalQuantization(String signalId, QuantizationType quantizationType,
                                                                double quantLevel) throws QuantizationException, SignalRepositoryException
    {
        Signal signal = signalRepository.findOne(signalId);

        SignalQuantization quantization = signalQuantizationResolver.resolve(quantizationType);
        Complex complexQuantLevel = new Complex(quantLevel, 0.0D);
        signal = quantization.signalQuantization(signal, complexQuantLevel);

        signalRepository.update(signal);

        return SignalQuantizationResponse.builder().SNR(reconstructionParametersCalculator.calculateSNR(signal)).MD
                (reconstructionParametersCalculator.calculateMD(signal)).MSE(reconstructionParametersCalculator
                .calculateMSE(signal)).PSNR(reconstructionParametersCalculator.calculatePSNR(signal)).build();
    }
}

package service;

import com.google.common.collect.Iterables;
import exception.ChartServiceException;
import exception.QuantizationException;
import exception.SignalRepositoryException;
import javafx.scene.chart.XYChart;
import model.behaviour.SignalReconstructionType;
import model.reconstruction.SignalReconstruction;
import model.signal.base.Signal;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SignalRepository;
import utils.quantization.SignalReconstructionTypeResolver;

import java.util.*;

/**
 * Created by bartoszpietrzak on 23/10/2017.
 */
@Component
public class ChartServiceImpl implements ChartService
{
    @Autowired
    private SignalRepository signalRepository;

    @Autowired
    private SignalReconstructionTypeResolver reconstructionTypeResolver;

    @Override
    public XYChart.Series<Double, Double> renderRealSignalChart(String signalId) throws ChartServiceException,
			SignalRepositoryException
    {
        Signal signal = signalRepository.findOne(signalId);

        if (!validateSignal(signal))
        {
            throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues()
					.size());
        }

        XYChart.Series<Double, Double> signalChart = new XYChart.Series<>();

        List<Complex> samples = signal.getSamples();
        List<Complex> values = signal.getValues();

        Complex currentSample;
        Complex currentValue;

        for (int i = 0; i < signal.getSamples().size(); i++)
        {
            currentSample = samples.get(i);
            currentValue = values.get(i);
            signalChart.getData().add(new XYChart.Data(currentSample.getReal(), currentValue.getReal()));
        }

        return signalChart;
    }

    @Override
    public XYChart.Series<Double, Integer> renderRealSignalHistogram(String signalId, int intervalCount) throws
			ChartServiceException, SignalRepositoryException
    {
        Signal signal = signalRepository.findOne(signalId);

        if (!validateSignal(signal))
        {
            throw ChartServiceException.samplesAndValuesDoNotMatch(signal.getSamples().size(), signal.getValues()
					.size());
        }

        XYChart.Series<Double, Integer> signalHistogram = new XYChart.Series<>();

        List<Complex> values = signal.getValues();

        List<Complex> copiedAndSortedValues = new ArrayList<>(values);
        Collections.copy(copiedAndSortedValues, values);
        Collections.sort(copiedAndSortedValues, (val1, val2) -> val1.getReal() > val2.getReal() ? 1 : (val1.getReal()
				< val2.getReal() ? -1 : 0));

        List<Double> intervals = new ArrayList<>();
        intervals.add(Iterables.getFirst(copiedAndSortedValues, null).getReal());

        double increment = ((signal.getAmplitude().getReal() * 2) / (double) intervalCount) + Iterables.getFirst
				(copiedAndSortedValues, null).getReal();
        double interval = (signal.getAmplitude().getReal() * 2) / (double) intervalCount;

        while (increment <= Iterables.getLast(copiedAndSortedValues).getReal())
        {
            intervals.add(increment);
            increment += interval;
        }

        Map<Double, Integer> histogram = new HashMap<>();

        for (int i = 0; i < intervals.size(); i++)
        {
            histogram.put(intervals.get(i), 0);
        }

        Complex currentValue;
        for (int i = 0; i < copiedAndSortedValues.size(); i++)
        {
            currentValue = copiedAndSortedValues.get(i);

            for (int j = 0; j < intervals.size() - 1; j++)
            {
                if (currentValue.getReal() >= intervals.get(j) && currentValue.getReal() < intervals.get(j + 1))
                {
                    histogram.put(intervals.get(j), histogram.get(intervals.get(j)) + 1);
                }
            }
        }

        for (Double key : histogram.keySet())
        {
            signalHistogram.getData().add(new XYChart.Data(String.valueOf(key), histogram.get(key)));
        }

        return signalHistogram;
    }

    @Override
    public XYChart.Series<Double, Double> reconstructQuantizationSignal(String signalId, SignalReconstructionType reconstructionType) throws QuantizationException, SignalRepositoryException
    {
        Signal signal = signalRepository.findOne(signalId);

        SignalReconstruction reconstruction = reconstructionTypeResolver.resolve(reconstructionType);
        return reconstruction.signalReconstruction(signal);
    }

    private boolean validateSignal(Signal signal)
    {
        return signal.getSamples().size() == signal.getValues().size();
    }
}

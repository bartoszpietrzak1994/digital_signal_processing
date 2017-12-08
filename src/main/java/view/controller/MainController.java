package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Iterables;

import exception.DigitalSignalProcessingErrorCode;
import exception.DigitalSignalProcessingException;
import exception.SignalIOException;
import exception.SignalRepositoryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.behaviour.HistogramInvervalNumber;
import model.behaviour.IOOperation;
import model.behaviour.QuantizationType;
import model.behaviour.SignalOperation;
import model.behaviour.SignalReconstructionType;
import model.signal.SignalType;
import model.signal.base.Signal;
import service.ChartService;
import service.SignalService;
import service.request.ResolveSignalRequest;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
import service.response.SignalQuantizationResponse;
import service.response.SignalsCalculationResponse;
import utils.request.ResolveSignalRequestBuilder;
import utils.request.SignalOperationRequestBuilder;

/**
 * Created by bartoszpietrzak on 21/10/2017.
 */
@Component
public class MainController implements Initializable
{
	/**
	 * .fxml file related objects
	 */
	@FXML
	private ComboBox<String> signalTypeComboBox;

	@FXML
	private ComboBox<String> signalOperationComboBox;

	@FXML
	private ComboBox<String> ioOperationComboBox;

	@FXML
	private ComboBox<String> histogramIntvervalComboBox;

	@FXML
	private Button renderHistogramButton;

	@FXML
	private TextField durationTextField;

	@FXML
	private LineChart<Double, Double> realChart;

	@FXML
	private Button clearChartButton;

	@FXML
	private BarChart<Double, Integer> realHistogram;

	@FXML
	private TextField initialTimeTextField;

	@FXML
	private TextField amplitudeTextField;

	@FXML
	private TextField dutyCycleTextField;

	@FXML
	private TextField periodTextField;

	@FXML
	private TextField signalSamplingRate;

	@FXML
	private TextField valuePresenceProbability;

	@FXML
	private TextField amplitudeRiseSample;

	@FXML
	private TextField amplitudeRiseTime;

	@FXML
	private TextField averageSignalValueTextField;

	@FXML
	private TextField absoluteAverageSignalValueTextField;

	@FXML
	private TextField signalPowerTextField;

	@FXML
	private TextField signalVarianceTextField;

	@FXML
	private TextField rootMeanSquareValueTextField;

	@FXML
	private Label resultProviderLabel;

	@FXML
	private Button generateButton;

	@FXML
	private Button renderChartsButton;

	@FXML
	private ListView<String> signalListView;

	@FXML
	private Button calculateButton;

	@FXML
	private Button readWriteButton;

	@FXML
	private TextField firstSignalNumber;

	@FXML
	private TextField secondSignalNumber;

	/**
	 * Quantization
	 */
	@FXML
	private ComboBox<String> quantizationTypeComboBox;

	@FXML
	private TextField quantLevelTextField;

	@FXML
	private Button performQuantizationButton;

	@FXML
	private ComboBox<String> signalReconstructionTypeComboBox;

	@FXML
	private Button performSignalReconstructionButton;

	@FXML
	private TextField signalQuantizationTextField;

	/**
	 * Services
	 */
	@Autowired
	private SignalService signalService;

	@Autowired
	private ChartService chartService;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// Put every signal type defined in SignalType enumeration to signalTypeComboBoxValues
		List<String> stringSignalTypes = Arrays.stream(SignalType.values()).map(Enum::toString).collect(Collectors.toList());

		// UNKNOWN signal type is reserved only for signals being a result of calculation between two signals of different type
		stringSignalTypes.remove(SignalType.UNKNOWN.name());
		ObservableList<String> signalTypes = FXCollections.observableList(stringSignalTypes);
		signalTypeComboBox.setItems(signalTypes);

		// same thing with signalOperationComboBox
		List<String> stringSignalOperations = Arrays.stream(SignalOperation.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> signalOperations = FXCollections.observableList(stringSignalOperations);
		signalOperationComboBox.setItems(signalOperations);

		// ioOperationComboBox
		List<String> stringIoOperations = Arrays.stream(IOOperation.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> ioOperations = FXCollections.observableList(stringIoOperations);
		ioOperationComboBox.setItems(ioOperations);

		// quantizationTypeComboBox
		List<String> stringQuantizationTypes = Arrays.stream(QuantizationType.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> quantizationTypes = FXCollections.observableList(stringQuantizationTypes);
		quantizationTypeComboBox.setItems(quantizationTypes);

		// signalReconstructionTypeComboBox
		List<String> stringReconstructionTypes = Arrays.stream(SignalReconstructionType.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> reconstructionTypes = FXCollections.observableList(stringReconstructionTypes);
		signalReconstructionTypeComboBox.setItems(reconstructionTypes);

		// interval counter as well
		List<String> counterValues = Arrays.stream(HistogramInvervalNumber.values())
				.map(HistogramInvervalNumber::getCounter)
				.map(Object::toString)
				.collect(Collectors.toList());
		ObservableList<String> counters = FXCollections.observableList(counterValues);
		histogramIntvervalComboBox.setItems(counters);

		realChart.getXAxis().setAnimated(false);
		realChart.getYAxis().setAnimated(true);
		realChart.setAnimated(true);
		realChart.setCreateSymbols(false);

		realHistogram.getXAxis().setAnimated(false);
		realHistogram.getYAxis().setAnimated(true);
		realHistogram.setAnimated(true);
	}

	@FXML
	public void computeSignalUsingProvidedParameters()
	{
		this.resultProviderLabel.setText("");
		if (StringUtils.isEmpty(this.signalTypeComboBox.getValue()))
		{
			this.resultProviderLabel.setText(DigitalSignalProcessingErrorCode.SIGNAL_TYPE_NOT_GIVEN_BY_USER.name() + ". Please provide signal type from list");
			return;
		}

		ResolveSignalRequest resolveSignalRequest = ResolveSignalRequestBuilder.builder()
				.signalType(this.signalTypeComboBox.getValue())
				.amplitude(this.amplitudeTextField.getText())
				.duration(this.durationTextField.getText())
				.dutyCycle(this.dutyCycleTextField.getText())
				.initialTime(this.initialTimeTextField.getText())
				.period(this.periodTextField.getText())
				.samplingRate(this.signalSamplingRate.getText())
				.valuePresenceProbability(this.valuePresenceProbability.getText())
				.amplitudeRiseSample(this.amplitudeRiseSample.getText())
				.amplitudeRiseTime(this.amplitudeRiseTime.getText())
				.build()
				.toRequest();

		ResolveSignalResponse response;
		try
		{
			response = signalService.processResolveSignalRequest(resolveSignalRequest);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		this.averageSignalValueTextField.setText(Double.valueOf(response.getAverageSignalValue().getReal()).toString());
		this.absoluteAverageSignalValueTextField.setText(Double.valueOf(response.getAbsoluteAverageSignalValue().getReal()).toString());
		this.signalPowerTextField.setText(Double.valueOf(response.getSignalPower().getReal()).toString());
		this.signalVarianceTextField.setText(Double.valueOf(response.getSignalVariance().getReal()).toString());
		this.rootMeanSquareValueTextField.setText(Double.valueOf(response.getSignalRootMeanSquareValue().getReal()).toString());

		signalListView.getItems().add(response.getSignalParametersResponse().toString());
	}

	@FXML
	public void renderChartsForSignal()
	{
		this.resultProviderLabel.setText("");
		ObservableList<String> selectedItems = signalListView.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty() && selectedItems.size() != 1)
		{
			this.resultProviderLabel.setText("Only one signal can be rendered to a chart.");
			return;
		}

		Signal signal = null;
		try
		{
			signal = signalService.findSignal(selectedItems.get(0).split("\\;")[0]);
		}
		catch (SignalRepositoryException exception)
		{
			resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		XYChart.Series<Double, Double> realSignalChart = null;
		try
		{
			realSignalChart = chartService.renderRealSignalChart(signal);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		this.realChart.getData().add(realSignalChart);
	}

	@FXML
	public void renderHistogramForSignal()
	{
		this.resultProviderLabel.setText("");
		ObservableList<String> selectedItems = signalListView.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty() && selectedItems.size() != 1)
		{
			this.resultProviderLabel.setText("Only one signal can be rendered to a chart.");
			return;
		}

		Signal signal = null;
		try
		{
			signal = signalService.findSignal(selectedItems.get(0).split("\\;")[0]);
		}
		catch (SignalRepositoryException exception)
		{
			resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		int interval = StringUtils.isEmpty(histogramIntvervalComboBox.getValue()) ?
				signal.getSamples().size() / 10 :
				Integer.valueOf(histogramIntvervalComboBox.getValue());

		XYChart.Series<Double, Integer> realSignalHistogram = null;
		try
		{
			realSignalHistogram = chartService.renderRealSignalHistogram(signal, interval);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		this.realHistogram.getData().setAll(realSignalHistogram);
	}

	@FXML
	public void performSignalsOperation()
	{
		this.resultProviderLabel.setText("");
		if (StringUtils.isEmpty(this.signalOperationComboBox.getValue()))
		{
			this.resultProviderLabel.setText(
					DigitalSignalProcessingErrorCode.SIGNAL_OPERATION_NOT_GIVEN_BY_USER.name() + ". Please select one of the available signals operations");
			return;
		}

		if (CollectionUtils.isEmpty(signalListView.getItems()))
		{
			this.resultProviderLabel.setText(
					DigitalSignalProcessingErrorCode.NO_SIGNALS_PRESENT_ON_SIGNAL_LIST.name() + ". Please generate signal or load if from file");
			return;
		}

		if (StringUtils.isAnyEmpty(this.firstSignalNumber.getText(), this.secondSignalNumber.getText()))
		{
			this.resultProviderLabel.setText(
					DigitalSignalProcessingErrorCode.TWO_SIGNALS_NOT_SELECTED_FOR_OPERATION.name() + ". Please select two signals");
			return;
		}

		String firstSignalData = null;
		String secondSignalData = null;
		try
		{
			firstSignalData = signalListView.getItems().get(Integer.valueOf(this.firstSignalNumber.getText()) - 1);
			secondSignalData = signalListView.getItems().get(Integer.valueOf(this.secondSignalNumber.getText()) - 1);
		}
		catch (IndexOutOfBoundsException exception)
		{
			this.resultProviderLabel.setText(DigitalSignalProcessingErrorCode.SIGNAL_NOT_PRESENT_IN_REPOSITORY.name() + ". Please select id from the list");
			return;
		}

		SignalsOperationRequest signalsOperationRequest = SignalOperationRequestBuilder.builder()
				.firstSignalData(firstSignalData)
				.secondSignalData(secondSignalData)
				.signalOperation(signalOperationComboBox.getValue())
				.build()
				.toRequest();

		SignalsCalculationResponse signalsCalculationResponse;

		try
		{
			signalsCalculationResponse = signalService.processSignalOperationRequest(signalsOperationRequest);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exception.getMessage());
			exception.printStackTrace();
			return;
		}

		signalListView.getItems().add(signalsCalculationResponse.getSignalParametersResponse().toString());
	}

	@FXML
	public void handleReadWriteRequest()
	{
		this.resultProviderLabel.setText("");
		if (StringUtils.isEmpty(this.ioOperationComboBox.getValue()))
		{
			this.resultProviderLabel.setText(DigitalSignalProcessingErrorCode.READ_WRITE_OPERATION_NOT_GIVEN_BY_USER.name()
					+ ". Please select one of the available signal I/O operations");
			return;
		}

		String value = ioOperationComboBox.getValue();
		IOOperation ioOperation = IOOperation.valueOf(value);

		switch(ioOperation)
		{
			case SAVE:
				exportSignalsToFile();
				break;
			case LOAD:
				loadSignalsFromFile();
				break;
		}
	}

	@FXML
	public void handleQuantizationRequest()
	{
		this.resultProviderLabel.setText("");
		if (StringUtils.isEmpty(this.quantizationTypeComboBox.getValue()))
		{
			this.resultProviderLabel.setText(DigitalSignalProcessingErrorCode.QUANTIZATION_TYPE_NOT_GIVEN_BY_USER.name()
					+ ". Please select one of the available quantization types");
			return;
		}

		String value = quantizationTypeComboBox.getValue();
		QuantizationType quantizationType = QuantizationType.valueOf(value);

		double quantLevel = Double.valueOf(quantLevelTextField.getText());

		ObservableList<String> selectedItems = signalListView.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty())
		{
			this.resultProviderLabel.setText("Please select at least one signal to perform quantization");
		}

		String signalId = Iterables.getFirst(selectedItems, null).split("\\;")[0];

		SignalQuantizationResponse signalQuantizationResponse;
		try
		{
			signalQuantizationResponse = signalService.performSignalQuantization(signalId, quantizationType, quantLevel);
		}
		catch (DigitalSignalProcessingException e)
		{
			e.printStackTrace();
			this.resultProviderLabel.setText(e.getMessage());
			return;
		}

		signalQuantizationTextField.setText(signalQuantizationResponse.toString());
	}

	@FXML
	public void clearChart()
	{
		this.realChart.getData().clear();
		this.quantLevelTextField.setText("");
	}

	@FXML
	public void handleSignalReconstructionRequest()
	{
		this.realChart.getData().clear();
		this.resultProviderLabel.setText("");
		if (StringUtils.isEmpty(this.signalReconstructionTypeComboBox.getValue()))
		{
			this.resultProviderLabel.setText(DigitalSignalProcessingErrorCode.RECONSTRUCTION_TYPE_NOT_GIVEN_BY_USER.name()
					+ ". Please select one of the available reconstruction types");
			return;
		}

		String signalId = Iterables.getFirst(signalListView.getSelectionModel().getSelectedItems(), null).split("\\;")[0];

		Signal signal;
		try
		{
			signal = signalService.findSignal(signalId);
		}
		catch (SignalRepositoryException e)
		{
			e.printStackTrace();
			this.resultProviderLabel.setText(e.getMessage());
			return;
		}

		String value = signalReconstructionTypeComboBox.getValue();
		SignalReconstructionType reconstructionType = SignalReconstructionType.valueOf(value);

		XYChart.Series<Double, Double> doubleDoubleSeries;
		try
		{
			doubleDoubleSeries = chartService.reconstructQuantizationSignal(signal, reconstructionType);
		}
		catch (DigitalSignalProcessingException e)
		{
			e.printStackTrace();
			this.resultProviderLabel.setText(e.getMessage());
			return;
		}

		this.realChart.getData().clear();
		this.realChart.getData().add(doubleDoubleSeries);
		renderChartsForSignal();
	}

	private void exportSignalsToFile()
	{
		this.resultProviderLabel.setText("");
		ObservableList<String> selectedItems = signalListView.getSelectionModel().getSelectedItems();

		if (selectedItems.isEmpty())
		{
			this.resultProviderLabel.setText("Please select at least one signal to export.");
		}

		List<Signal> signals = new ArrayList<>();

		for (String data : selectedItems)
		{
			try
			{
				signals.add(signalService.findSignal(data.split("\\;")[0]));
			}
			catch (SignalRepositoryException e)
			{
				this.resultProviderLabel.setText(e.getMessage());
				e.printStackTrace();
				return;
			}
		}

		List<Signal> signalsWithoutUnknowns = signals.stream()
				.filter(signal -> !signal.getSignalType().equals(SignalType.UNKNOWN))
				.collect(Collectors.toList());

		try
		{
			signalService.saveListOfSignalsInFile(signalsWithoutUnknowns, "./signals.json");
		}
		catch (SignalIOException e)
		{
			this.resultProviderLabel.setText(e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadSignalsFromFile()
	{
		this.resultProviderLabel.setText("");
		List<Signal> signals = null;
		try
		{
			signals = signalService.loadSignalsFromFile("./signals.json");
		}
		catch (SignalIOException e)
		{
			this.resultProviderLabel.setText(e.getMessage());
			return;
		}

		for (Signal signal : signals)
		{
			ResolveSignalRequest resolveSignalRequest = ResolveSignalRequest.builder()
					.signalType(signal.getSignalType().name())
					.amplitude(signal.getAmplitude())
					.amplitudeRiseSample(signal.getAmplitudeRiseSample())
					.amplitudeRiseTime(signal.getAmplitudeRiseTime())
					.duration(signal.getDuration())
					.dutyCycle(signal.getDutyCycle())
					.initialTime(signal.getInitialTime())
					.period(signal.getPeriod())
					.samplingRate(signal.getSamplingRate())
					.valuePresenceProbability(signal.getValuePresenceProbability()).build();

			ResolveSignalResponse response;
			try
			{
				response = signalService.processResolveSignalRequest(resolveSignalRequest);
			}
			catch (Exception exception)
			{
				this.resultProviderLabel.setText(exception.getMessage());
				exception.printStackTrace();
				return;
			}

			this.averageSignalValueTextField.setText(Double.valueOf(response.getAverageSignalValue().getReal()).toString());
			this.absoluteAverageSignalValueTextField.setText(Double.valueOf(response.getAbsoluteAverageSignalValue().getReal()).toString());
			this.signalPowerTextField.setText(Double.valueOf(response.getSignalPower().getReal()).toString());
			this.signalVarianceTextField.setText(Double.valueOf(response.getSignalVariance().getReal()).toString());
			this.rootMeanSquareValueTextField.setText(Double.valueOf(response.getSignalRootMeanSquareValue().getReal()).toString());

			signalListView.getItems().add(response.getSignalParametersResponse().toString());
		}
	}
}


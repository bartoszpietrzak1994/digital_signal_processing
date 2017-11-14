package view.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

import exception.DigitalSignalProcessingErrorCode;
import exception.DigitalSignalProcessingExceptionHandler;
import exception.SignalIOException;
import exception.SignalParametersException;
import exception.SignalRepositoryException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.behaviour.IOOperation;
import model.behaviour.SignalOperation;
import model.signal.SignalType;
import model.signal.base.Signal;
import service.ChartService;
import service.SignalService;
import service.request.ResolveSignalRequest;
import service.request.SignalsOperationRequest;
import service.response.ResolveSignalResponse;
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
	private TextField durationTextField;

	@FXML
	private LineChart<String, Double> realChart;

	@FXML
	private LineChart<Double, Double> imaginaryChart;

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

	/**
	 * Services
	 */
	@Autowired
	private SignalService signalService;

	@Autowired
	private ChartService chartService;

	@Autowired
	private DigitalSignalProcessingExceptionHandler exceptionHandler;

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

		// ...and ioOperationComboBox
		List<String> stringIoOperations = Arrays.stream(IOOperation.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> ioOperations = FXCollections.observableList(stringIoOperations);
		ioOperationComboBox.setItems(ioOperations);
	}

	@FXML
	public void computeSignalUsingProvidedParameters() throws SignalIOException, SignalParametersException
	{
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
				.build()
				.toRequest();

		ResolveSignalResponse response;
		try
		{
			response = signalService.processResolveSignalRequest(resolveSignalRequest);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exceptionHandler.handle(exception));
			throw exception;
		}

		this.averageSignalValueTextField.setText(Double.valueOf(response.getAverageSignalValue().getReal()).toString());
		this.absoluteAverageSignalValueTextField.setText(Double.valueOf(response.getAbsoluteAverageSignalValue().getReal()).toString());
		this.signalPowerTextField.setText(Double.valueOf(response.getSignalPower().getReal()).toString());
		this.signalVarianceTextField.setText(Double.valueOf(response.getSignalVariance().getReal()).toString());
		this.rootMeanSquareValueTextField.setText(Double.valueOf(response.getSignalRootMeanSquareValue().getReal()).toString());

		signalListView.getItems().add(response.getSignalParametersResponse().toString());
	}

	@FXML
	public void renderChartsForSignal(int signalId)
	{
		Signal signal = null;
		try
		{
			signal = signalService.findSignal(signalId);
		}
		catch (SignalRepositoryException exception)
		{
			resultProviderLabel.setText(exceptionHandler.handle(exception));
		}

		XYChart.Series<String, Double> realSignalChart = null;
		try
		{
			realSignalChart = chartService.renderRealSignalChart(signal);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exceptionHandler.handle(exception));
		}

		this.realChart.getData().setAll(realSignalChart);
	}

	@FXML
	public void performSignalsOperation()
	{
		if (StringUtils.isEmpty(ioOperationComboBox.getValue()))
		{
			this.resultProviderLabel.setText(
					DigitalSignalProcessingErrorCode.SIGNAL_OPERATION_NOT_GIVEN_BY_USER.name() + ". Please select one of the available signals operations");
			return;
		}

		ObservableList<String> selectedItems = signalListView.getSelectionModel().getSelectedItems();

		if (selectedItems.size() != 2)
		{
			this.resultProviderLabel.setText(
					DigitalSignalProcessingErrorCode.TWO_SIGNALS_NOT_SELECTED_FOR_OPERATION.name() + ". Please select two signals from signals list");
			return;
		}

		SignalsOperationRequest signalsOperationRequest = SignalOperationRequestBuilder.builder()
				.firstSignalData(Iterables.getFirst(selectedItems, null))
				.secondSignalData(Iterables.getLast(selectedItems, null))
				.signalOperation(ioOperationComboBox.getValue())
				.build()
				.toRequest();

		SignalsCalculationResponse signalsCalculationResponse;

		try
		{
			signalsCalculationResponse = signalService.processSignalOperationRequest(signalsOperationRequest);
		}
		catch (Exception exception)
		{
			this.resultProviderLabel.setText(exceptionHandler.handle(exception));
			throw exception;
		}

		signalListView.getItems().add(signalsCalculationResponse.getSignalParametersResponse().toString());
	}
}


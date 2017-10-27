package view.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.ChartServiceException;
import exception.SignalParametersException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.request.SignalPropertiesCalculationRequest;
import model.response.SignalPropertiesCalculationResponse;
import model.signal.SignalType;
import service.ChartService;
import service.SignalService;
import utils.request.SignalPropertiesCalculationRequestBuilder;

/**
 * Created by bartoszpietrzak on 21/10/2017.
 */
@Component
public class MainController implements Initializable
{
	@FXML
	private ComboBox<String> signalTypeComboBox;

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
	private TextField userSampleCount;

	@FXML
	private Label resultProviderLabel;

	@FXML
	private Button renderButton;

	@Autowired
	private SignalService signalService;

	@Autowired
	private ChartService chartService;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// Put every signal type defined in SignalType enumeration to signalTypeComboBoxValues
		List<String> stringSignalTypes = Arrays.stream(SignalType.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> signalTypes = FXCollections.observableList(stringSignalTypes);
		signalTypeComboBox.setItems(signalTypes);
	}

	@FXML
	public void renderCharts()
	{
		SignalPropertiesCalculationRequest signalPropertiesCalculationRequest = SignalPropertiesCalculationRequestBuilder.builder()
				.signalType(this.signalTypeComboBox.getValue())
				.amplitude(this.amplitudeTextField.getText())
				.duration(this.durationTextField.getText())
				.dutyCycle(this.dutyCycleTextField.getText())
				.initialTime(this.initialTimeTextField.getText())
				.period(this.periodTextField.getText())
				.samplingRate(this.signalSamplingRate.getText())
				.build()
				.toRequest();

		SignalPropertiesCalculationResponse response = null;
		try
		{
			response = signalService.calculateSignalProperties(signalPropertiesCalculationRequest);
		}
		catch (SignalParametersException e)
		{
			e.printStackTrace();
		}

		XYChart.Series<String, Double> realSignalChart = null;
		try
		{
			realSignalChart = chartService.renderRealSignalChart(response.getSignal());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.realChart.getData().setAll(realSignalChart);
	}
}


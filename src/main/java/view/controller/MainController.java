package view.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.signal.SignalType;
import model.signal.base.Signal;
import service.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 21/10/2017.
 */
public class MainController implements Initializable
{
	@FXML
	private ComboBox<String> signalTypeComboBox;

	@FXML
	private TextField durationTextField;

	@FXML
	private LineChart<?, ?> lineChart;

	@FXML
	private TextField initialTimeTextField;

	@FXML
	private TextField amplitudeTextField;

	@FXML
	private TextField dutyCycleTextField;

	@FXML
	private TextField periodTextField;

	@FXML
	private TextField signalSampleCount;

	@FXML
	private TextField userSampleCount;

	@FXML
	private Label resultProviderLabel;

 	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// Put every signal type defined in SignalType enumeration to signalTypeComboBoxValues
		List<String> stringSignalTypes = Arrays.stream(SignalType.values()).map(Enum::toString).collect(Collectors.toList());
		ObservableList<String> signalTypes = FXCollections.observableList(stringSignalTypes);
		signalTypeComboBox.setItems(signalTypes);
	}

	private Signal chooseProperSignalBySignalType()
	{
		SignalType signalType = null;
		try
		{
			signalType = SignalType.valueOf(signalTypeComboBox.getValue());
		}
		catch (IllegalArgumentException e)
		{
			resultProviderLabel.setText("Chosen signal type is not supported!");
		}

		return signalTypeResolver.resolveSignalByType(signalType);
	}
}


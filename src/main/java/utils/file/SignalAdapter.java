package utils.file;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import exception.SignalParametersException;
import model.signal.base.Signal;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Component
public class SignalAdapter implements JsonDeserializer<List<Signal>>
{
	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Override
	public List<Signal> deserialize(
			JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
	{
		JsonArray jsonArray = jsonElement.getAsJsonArray();

		List<Signal> signals = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JsonObject asJsonObject = jsonArray.get(i).getAsJsonObject();
			String signalTypeString = asJsonObject.get("signalType").getAsString();

			Signal signal = null;
			try
			{
				signal = signalTypeResolver.resolveSignalByType(signalTypeString);
			}
			catch (SignalParametersException e)
			{
				e.printStackTrace();
				return signals;
			}

			signal.setAmplitude(extractComplexNumberFromJsonObject(asJsonObject.get("amplitude")));
			signal.setInitialTime(extractComplexNumberFromJsonObject(asJsonObject.get("initialTime")));
			signal.setEndTime(extractComplexNumberFromJsonObject(asJsonObject.get("endTime")));
			signal.setDuration(extractComplexNumberFromJsonObject(asJsonObject.get("duration")));
			signal.setSamplingRate(extractComplexNumberFromJsonObject(asJsonObject.get("samplingRate")));
			signal.setIsPeriodic(Boolean.valueOf(asJsonObject.get("isPeriodic").getAsString()));

			signal.setPeriod(asJsonObject.get("period") != null ? extractComplexNumberFromJsonObject(asJsonObject.get("period")) : null);
			signal.setDutyCycle(asJsonObject.get("dutyCycle") != null ? extractComplexNumberFromJsonObject(asJsonObject.get("dutyCycle")) : null);
			signal.setValuePresenceProbability(asJsonObject.get("valuePresenceProbability") != null ?
					extractComplexNumberFromJsonObject(asJsonObject.get("valuePresenceProbability")) :
					null);
			signal.setAmplitudeRiseTime(
					asJsonObject.get("amplitudeRiseTime") != null ? extractComplexNumberFromJsonObject(asJsonObject.get("amplitudeRiseTime")) : null);
			signal.setAmplitudeRiseSample(
					asJsonObject.get("amplitudeRiseSample") != null ? extractComplexNumberFromJsonObject(asJsonObject.get("amplitudeRiseSample")) : null);

			signals.add(signal);
		}

		return signals;
	}

	private Complex extractComplexNumberFromJsonObject(JsonElement jsonElement)
	{
		JsonObject asJsonObject = jsonElement.getAsJsonObject();
		return new Complex(Double.valueOf(asJsonObject.get("real").getAsString()), Double.valueOf(asJsonObject.get("imaginary").getAsString()));
	}
}

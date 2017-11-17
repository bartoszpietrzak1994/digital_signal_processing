package utils.file;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import model.signal.base.Signal;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 06/10/2017.
 */
@Component
public class SignalAdapter implements JsonSerializer<List<Signal>>, JsonDeserializer<List<Signal>>
{
	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Override
	public JsonElement serialize(List<Signal> signals, Type type, JsonSerializationContext jsonSerializationContext)
	{
		JsonObject result = new JsonObject();
		result.add("type", new JsonPrimitive(signals.getClass().getSimpleName()));
		result.add("properties", jsonSerializationContext.serialize(signals, signals.getClass()));
		return result;
	}

	@Override
	public List<Signal> deserialize(
			JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
	{
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String stringType = jsonObject.get("type").getAsString();
		JsonElement element = jsonObject.get("properties");

		try
		{
			String thePackage = "model.signal.base";
			return jsonDeserializationContext.deserialize(element, Class.forName(thePackage + stringType));
		}
		catch (ClassNotFoundException cnfe)
		{
			throw new JsonParseException("Unknown element type: " + stringType, cnfe);
		}
	}
}

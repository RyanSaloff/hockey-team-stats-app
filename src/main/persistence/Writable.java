package persistence;

import org.json.JSONObject;

// This interface is based off JsonSerializationDemo

// Represents a class as a JSON object
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}

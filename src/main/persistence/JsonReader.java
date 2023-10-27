package persistence;

import model.Goalie;
import model.Skater;
import model.Team;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// This class is based off JsonSerializationDemo

// Represents a reader that reads Team from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads hockey team from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String teamName = jsonObject.getString("name");
        Team team = new Team(teamName);
        addSkaters(team, jsonObject);
        addGoalies(team, jsonObject);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses goalies from JSON object and adds them to the hockey team
    private void addGoalies(Team team, JSONObject jsonObject) {
        // if there's an error, check the key
        JSONArray jsonArray = jsonObject.getJSONArray("goalieList");
        for (Object json : jsonArray) {
            JSONObject nextGoalie = (JSONObject) json;
            addGoalie(team, nextGoalie);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses goalie from JSON object and adds it to the hockey team
    private void addGoalie(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String position = jsonObject.getString("position");
        int number = jsonObject.getInt("number");
        Goalie goalie = new Goalie();
        goalie.setName(name);
        goalie.setAge(age);
        goalie.setPosition(position);
        goalie.setNumber(number);
        team.addGoalie(goalie);
    }

    // MODIFIES: team
    // EFFECTS: parses skaters from JSON object and adds them to the hockey team
    private void addSkaters(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("skaterList");
        for (Object json : jsonArray) {
            JSONObject nextSkater = (JSONObject) json;
            addSkater(team, nextSkater);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses skater from JSON object and adds it to the hockey team
    private void addSkater(Team team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String position = jsonObject.getString("position");
        int number = jsonObject.getInt("number");
        Skater skater = new Skater();
        skater.setName(name);
        skater.setAge(age);
        skater.setPosition(position);
        skater.setNumber(number);
        team.addSkater(skater);
    }
}

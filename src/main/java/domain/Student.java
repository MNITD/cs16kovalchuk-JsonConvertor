package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    private JsonObject[] examsToJsonArray() {
        JsonObject[] jsonObjects = new JsonObject[exams.length];
        for (int i = 0; i < jsonObjects.length; i++) {
            jsonObjects[i] = new JsonObject(
                    new JsonPair("course", new JsonString(exams[i].key)),
                    new JsonPair("mark", new JsonNumber(exams[i].value)),
                    new JsonPair("passed", new JsonBoolean(exams[i].value > 2))
            );
        }
        return jsonObjects;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair jExams = new JsonPair("exams", new JsonArray(examsToJsonArray()));
        JsonObject jsonObject = super.toJsonObject();
        jsonObject.add(jExams);
        return jsonObject;
    }
}
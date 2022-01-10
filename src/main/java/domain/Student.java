package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    ArrayList<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = new ArrayList();
        for (Tuple exam: exams) {
            this.exams.add(exam);
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject obj = new JsonObject();
        JsonObject[] marks = new JsonObject[this.exams.size()];

        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));
        int i = 0;
        for (Tuple exam: this.exams) {
            String subject = exam.key.toString();
            Integer grade = Integer.valueOf(exam.value.toString());
            JsonBoolean pass;
            if (grade < 3) {
                pass = new JsonBoolean(false);
            }
            else {
                pass = new JsonBoolean(true);
            }

            JsonObject mathCourse = new JsonObject(
                    new JsonPair("course", new JsonString(subject)),
                    new JsonPair("mark", new JsonNumber(grade)),
                    new JsonPair("passed", pass));
            marks[i] = mathCourse;
            i++;
        }
        JsonArray marksCourses = new JsonArray(marks);
        JsonPair exams = new JsonPair("exams", marksCourses);
        obj.add(name);
        obj.add(surname);
        obj.add(year);
        obj.add(exams);

        return obj;
    }
}
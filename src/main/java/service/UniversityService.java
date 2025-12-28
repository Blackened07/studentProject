package service;

import com.google.gson.reflect.TypeToken;
import model.University;
import util.JSONutil.JsonUtil;
import util.downloadUtil.ExcelDataLoader;
import util.downloadUtil.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static model.Entities.SHEET_NAME_UNIVERSITY;
import static util.downloadUtil.PropertiesUtil.*;

public class UniversityService {
    private final List<University> universities;

    public UniversityService() {
        this.universities = ExcelDataLoader.readUniversities(
                PropertiesUtil.getProperty(EXCEL_PATH),
                SHEET_NAME_UNIVERSITY.getEntityName());
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void parseUniversitiesToJson() {
        JsonUtil.parseToJson(PropertiesUtil.getProperty(UNIVERSITIES_JSON_PATH), universities);
    }

    public List<University> parseUniversityFromJson() {
        return JsonUtil.parseFromJson(PropertiesUtil.getProperty(UNIVERSITIES_JSON_PATH), new TypeToken<>() {
        });
    }

    public University getRandom() {
        return universities.get(new Random().nextInt(universities.size()));
    }

    public List<University> getRandomUniversities() {
        List<University> result = new ArrayList<>();
        for (int i = 0; i < universities.size(); i++) {
            if (i == new Random().nextInt(universities.size())) {
                result.add(universities.get(i));
            }
        }
        return result;
    }
}

package service;

import model.University;
import util.downloadUtil.ExcelDataLoader;
import util.downloadUtil.PropertiesUtil;

import java.util.List;

import static model.Entities.SHEET_NAME_UNIVERSITY;
import static util.downloadUtil.PropertiesUtil.EXCEL_PATH;

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

}

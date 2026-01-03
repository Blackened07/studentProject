package util.downloadUtil;

import model.Statistic;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter {

    private static final String STATISTIC_SHEET_NAME = "Статистика";
    private static final short FONT_SIZE = 12;
    private static final byte UNDER_LINE = 3;
    private static final int HEADER_ROW = 0;
    private static final int START_ROW = 1;

    private static final int PROFILE_INDEX = 0;
    private static final int AVG_INDEX = 1;
    private static final int STUDENTS_NUMBER_INDEX = 2;
    private static final int UNIVERSITIES_NUMBER_INDEX = 3;
    private static final int UNIVERSITY_NAME_INDEX = 4;

    private static final String[] COLUMNS = {
            "Профиль обучения",
            "Средний балл за экзамен",
            "Количество студентов по профилю",
            "Количество университетов по профилю",
            "Университет"
    };

    private static final Logger logger = LoggerFactory.getLogger(XlsWriter.class);

    private XlsWriter() {
    }

    public static void write(List<Statistic> stats, String path) {
        logger.info("Старт сбора статистики в файл: {}, размер {}", path, stats.size());
        try (FileInputStream inputStream = new FileInputStream(path);
                Workbook workbook = WorkbookFactory.create(inputStream)) {

            if (workbook.getSheet(STATISTIC_SHEET_NAME) != null) {
                workbook.removeSheetAt(workbook.getSheetIndex(STATISTIC_SHEET_NAME));
            }

            Sheet sheet = workbook.createSheet(STATISTIC_SHEET_NAME);
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            setWorkBookFont(cellStyle, font);

            setWorkBookHeader(cellStyle, sheet);

            setData(stats, sheet);

            writeFile(workbook, path, stats);

        } catch (IOException e) {
            logger.warn("Элемент не найден");
            throw new RuntimeException(e);
        }
    }

    private static void setWorkBookFont(CellStyle cellStyle, Font font) throws IOException {
        font.setBold(true);
        font.setFontHeightInPoints(FONT_SIZE);
        font.setUnderline(UNDER_LINE);
        cellStyle.setFont(font);
    }

    private static void setWorkBookHeader(CellStyle cellStyle, Sheet sheet) throws IOException {
        Row row = sheet.createRow(HEADER_ROW);

        for (int i = 0; i < COLUMNS.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(COLUMNS[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    private static void setData(List<Statistic> stats, Sheet sheet) throws IOException {
        int count = START_ROW;
        for (Statistic stat : stats) {
            Row row = sheet.createRow(count++);

            row.createCell(PROFILE_INDEX).setCellValue(stat.getStudyProfile().getProfileName());
            row.createCell(AVG_INDEX).setCellValue(stat.getAvgExamScore());
            row.createCell(STUDENTS_NUMBER_INDEX).setCellValue(stat.getStudentsNumber());
            row.createCell(UNIVERSITIES_NUMBER_INDEX).setCellValue(stat.getUniversitiesNumber());
            row.createCell(UNIVERSITY_NAME_INDEX).setCellValue(stat.getUniversityNames());
        }
    }

    private static void writeFile(Workbook workbook, String path, List<Statistic> stats) {
        try (FileOutputStream out = new FileOutputStream(path)) {
            workbook.write(out);
            logger.info("Запись в файл {} прошла успешно. Записей: {}", path, stats.size());
        } catch (IOException e) {
            logger.error("Файл не найден {}", e.getMessage());
        }
    }
}

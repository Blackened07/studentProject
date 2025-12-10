package util.downloadUtil;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

@FunctionalInterface
interface SheetParser<T> {
    List<T> parse(Sheet sheet) throws Exception;
}

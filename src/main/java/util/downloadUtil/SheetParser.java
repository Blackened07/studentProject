package util.downloadUtil;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

@FunctionalInterface
interface SheetParser<T> {
    // T - вход (Sheet), R - выход (List<T>)
    List<T> parse(Sheet sheet) throws Exception; // <-- Вот в чем смысл!
}

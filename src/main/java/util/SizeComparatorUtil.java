package util;

import java.util.List;

public class SizeComparatorUtil {

    private SizeComparatorUtil() {
    }

    public static boolean compare(List<?> original, List<?> fromJson) {
        return original.size() == fromJson.size();
    }
}

package oob.lolprofile.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleOperation {
    public static double round(double value, int places, boolean isPercentage) {
        if (places < 0) throw new IllegalArgumentException();

        if (isPercentage) {
            value *= 100;
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String roundDoubleToString(double value, int places, boolean isPercentage) {
        if (places < 0) throw new IllegalArgumentException();

        return String.valueOf(DoubleOperation.round(value, places, isPercentage));
    }

    public static String roundDoubleToString(double value, int places) {
        return DoubleOperation.roundDoubleToString(value, places, false);
    }
}

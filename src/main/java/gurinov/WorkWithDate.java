package gurinov;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkWithDate {

    private static String getPattern(String s){
        return "^([1-9]||[0-2][0-9]||3[0-1])" + s + "([1-9]||0[1-9]||1[0-2])" + s + "([0-9][0-9])?[0-9][0-9]$";
    }

    private static LocalDate getLocalDate(int day, int month, int year){
        try {
            return LocalDate.of(year, month, day);
        } catch (Exception e) {
            return null;
        }
    }

    private static LocalDate getLocal(String date){
        int[] array = new int[3];
        int i = 0;
        Matcher matcher = Pattern.compile("(\\d+)").matcher(date);
        while(matcher.find()){
            array[i] = Integer.parseInt(matcher.group(1));
            i++;
        }
         return getLocalDate(array[0], array[1], array[2]);
    }

    public static boolean isValidDate(String date) {
        if(Pattern.compile(getPattern("/")).matcher(date).find() || Pattern.compile(getPattern("-")).matcher(date).find()){
            LocalDate localDate = getLocal(date);
            if(localDate != null && LocalDate.now().isAfter(localDate))
                return true;
        }
        return false;
    }

    public static String calculateAge(String date) {

        if(isValidDate(date)){
            LocalDate birthDate = getLocal(date);

            if ((birthDate != null)) {
                return Period.between(birthDate, LocalDate.now()).getYears() + "";
            } else {}
        }
        return "Error in date";
    }

    public static String calculateDaysBeforeBirthday(String date) {

        if(isValidDate(date)){
            LocalDate birthDate = getLocal(date);
            LocalDate nextBirthDate = getLocalDate(birthDate.getDayOfMonth(),birthDate.getMonthValue(), LocalDate.now().getYear());
            if(LocalDate.now().isAfter(nextBirthDate))
                nextBirthDate = nextBirthDate.plusYears(1);
            return ChronoUnit.DAYS.between(LocalDate.now(), nextBirthDate) + "";
        }
        return "Error in date";
    }
}

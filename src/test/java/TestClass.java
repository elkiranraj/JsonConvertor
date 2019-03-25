package test.java;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class TestClass {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(2018, 10, 5);
		System.out.println(c.getTime());
		Date d = new Date(c.getTimeInMillis());
		System.out.println(add(c.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 3));

	}

	static LocalDate StatusDate;
	static int Number;

	public static LocalDate add(LocalDate currDate, int numberOfBusinessDays) {
		if (numberOfBusinessDays < 1) {
			return currDate;
		}

		LocalDate result = currDate;
		int addedDays = 0;
		while (addedDays < numberOfBusinessDays) {
			result = result.plusDays(1);
			if (isHoliday(result)) {
				++addedDays;
			}
		}

		return result;
	}

	private static boolean isHoliday(LocalDate result) {
		return !(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY);
	}

	public static Date addDate(Date StatusDate, int Number) {
		if (Number < 1) {
			return StatusDate;
		}

		Date result = StatusDate;
		Calendar cal = Calendar.getInstance();
		cal.setTime(StatusDate);
		int addedDays = 0;
		while (addedDays < Number) {
			// result = result.plusDays(1);
			// if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
			++addedDays;
			// }
		}

		return result;
	}

}

package practice.basics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CaptureDateUsingJavaProgram {

	public static void main(String[] args) {
		Date date = new Date();

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sim.format(date);
		System.out.println(currentDate);

		Calendar calendar = sim.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 30);
		Date date2 = calendar.getTime();

		String futureDate = sim.format(date2);
		System.out.println(futureDate);

	}

}

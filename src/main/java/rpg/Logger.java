package rpg;

import java.io.PrintStream;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("kk:mm:ss");
	
	public static boolean isActive = true;
	
	private static void log(final PrintStream out, final String prefix, final String msg) {
		if(isActive) new SecurityManager() {
			{
				out.println("[" + OffsetDateTime.now().format(FORMATTER) + "] [" + getClassContext()[4].getCanonicalName() + "/" + prefix + "]: " + msg);
			}
		};
	}
	
	private static void log(final String prefix, final String msg) {
		log(System.out, prefix, msg);
	}
	
	public static void info(final String msg) {
		log("INFO", msg);
	}
	
	public static void debug(final String msg) {
		log("DEBUG", msg);
	}
	
	public static void error(final String msg) {
		log(System.err, "ERROR", msg);
	}
}

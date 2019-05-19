package rpg.api.dialog;

import java.util.ArrayList;

import rpg.api.localization.StringLocalizer;

public class Dialog {
	private int lineCount;
	private final ArrayList<String> lines = new ArrayList<>();
	
	public Dialog(final String name) {
		final String locString = "dialog." + name + ".";
		
		try {
			lineCount = Integer.parseInt(StringLocalizer.localize(locString + "lines"));
		}catch(final NumberFormatException e) {
			lineCount = 0;
		}
		
		for(int i = 0; i < lineCount; i++)
			lines.add(StringLocalizer.localize(locString + i));
	}
	
	public String getLine(final int lineNumber) {
		if(lineNumber < 0 || lineNumber >= lineCount) return "";
		
		return lines.get(lineNumber);
	}
}

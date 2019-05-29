package rpg.api.dialog;

import java.util.ArrayList;

import rpg.Logger;
import rpg.api.localization.StringLocalizer;

public class Dialog {
	private static final String nextSybol = StringLocalizer.localize("dialog.next");
	private int lineCount;
	private final ArrayList<String> lines = new ArrayList<>();
	
	public Dialog(final String name) {
		final String locString = "dialog." + name + ".";
		
		try {
			lineCount = Integer.parseInt(StringLocalizer.localize(locString + "lines"));
		}catch(final NumberFormatException e) {
			Logger.error(e);
			
			lineCount = 0;
		}
		
		for(int i = 0; i < lineCount; i++) {
			lines.add(StringLocalizer.localize(locString + i));
		}
		
		lines.add(nextSybol);
	}
	
	public int getLineCount() {
		return lines.size();
	}
	
	public String getLine(final int lineNumber) {
		if(lineNumber < 0 || lineNumber >= getLineCount()) return "";
		
		return lines.get(lineNumber);
	}
}

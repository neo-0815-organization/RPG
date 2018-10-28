package rpg.api.localization;

import java.io.File;

/**
 * The class Locale representing a language and country.
 *
 * @author Neo Hornberger
 */
public class Locale {
	public static final Locale NONE = createLocale("", "");
	
	public static final Locale	AMERICAN_ENGLISH	= createLocale("en", "US");
	public static final Locale	GERMAN				= createLocale("de", "DE");
	
	private final String language,
			country;
	
	private Locale(final String language, final String country) {
		this.language = language.toLowerCase();
		this.country = country.toUpperCase();
	}
	
	/**
	 * Gets the language code of the language represented by this {@link Locale}
	 *
	 * @return the language code corresponding to this {@link Locale}
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * Gets the country code of the country represented by this {@link Locale}.
	 *
	 * @return the country code corresponding to this {@link Locale}
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Gets the name of this {@link Locale}.
	 *
	 * @return the name of this {@link Locale} (la_CO)
	 */
	public String getName() {
		if(this == NONE) return null;
		
		return language + "_" + country;
	}
	
	/**
	 * Gets the name of the {@link File} corresponding to this {@link Locale}.
	 *
	 * @return the name of the {@link File} corresponding to this {@link Locale}
	 * (la_CO.lang)
	 */
	public String getFilename() {
		if(this == NONE) return null;
		
		return getName() + ".lang";
	}
	
	/**
	 * Gets the default {@link Locale}.
	 *
	 * @return the default {@link Locale}
	 */
	public static Locale getDefault() {
		return AMERICAN_ENGLISH;
	}
	
	/**
	 * Creates a new {@link Locale}.
	 *
	 * @param language
	 *     the language code which the new {@link Locale} should represent
	 * @param country
	 *     the country code which the new {@link Locale} should represent
	 * @return the new {@link Locale}
	 */
	private static Locale createLocale(final String language, final String country) {
		return new Locale(language, country);
	}
}

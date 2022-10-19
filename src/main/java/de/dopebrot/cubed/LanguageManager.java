package de.dopebrot.cubed;
import de.dopebrot.dopeapi.helper.LanguageHelper;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.logging.Level;

public class LanguageManager {

	private final Main main;
	private final LanguageHelper languageHelper;
	public LanguageManager(Main main) {
		this.main = main;
		this.languageHelper = new LanguageHelper("lang", "plugins/Cubed/language", "cubed");
		main.getResourceFile("lang.yml", new File("plugins/Cubed/language/lang.yml"));
		languageHelper.load();
	}

	/**
	 * gets string from key
	 */
	public String getString(String key) {
		Validate.notNull(key, "key can't be null");
		Validate.notEmpty(key, "key can't be empty");
		return languageHelper.getString(key);
	}

	/**
	 * gets string and replaces arguments
	 */
	public String getString(String key, String[] args) {
		Validate.notNull(key, "key can't be null");
		Validate.notEmpty(key, "key can't be empty");
		String tmp = languageHelper.getString(key);
		for (int i = 0; i < args.length; i++) {
			tmp = tmp.replace("%" + i + "%", "" + args[i]);
		}
		return tmp;
	}

}

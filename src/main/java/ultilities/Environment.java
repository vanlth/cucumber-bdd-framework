package ultilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources ({"file:EnvironmentConfig/${env}.properties"})
public interface Environment extends Config {
	
	@Key("App.Url")
	String appUrl();
	
	@Key("App.User")
	String appUsername();
	
	@Key("App.Password")
	String appPassword();
	
	@Key("DB.Host")
	String dbHost();
	
	@Key("DB.Username")
	String dbUsername();
	
	@Key("DB.Password")
	String dbPassword();

}

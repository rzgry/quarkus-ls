package com.redhat.microprofile.jdt.core;

import static com.redhat.microprofile.commons.metadata.ItemMetadata.CONFIG_PHASE_BUILD_TIME;
import static com.redhat.microprofile.commons.metadata.ItemMetadata.CONFIG_PHASE_BUILD_AND_RUN_TIME_FIXED;
import static com.redhat.microprofile.commons.metadata.ItemMetadata.CONFIG_PHASE_RUN_TIME;
import static com.redhat.microprofile.jdt.internal.core.MicroProfileAssert.assertProperties;
import static com.redhat.microprofile.jdt.internal.core.MicroProfileAssert.assertPropertiesDuplicate;
import static com.redhat.microprofile.jdt.internal.core.MicroProfileAssert.p;

import org.junit.Test;

import com.redhat.microprofile.commons.MicroProfileProjectInfo;
import com.redhat.microprofile.commons.MicroProfilePropertiesScope;


/**
 * Tests if <code>boolean</code> and <code>int</code> @ConfigItem properties without the 
 * <code>defaultValue</code> annotation, has a default value of <code>false</code> 
 * and <code>0</code> respectively 
 */
public class ConfigItemIntBoolDefaultValueTest extends BasePropertiesManagerTest {
	
	
	@Test
	public void configItemIntBoolDefaultValueTest() throws Exception {

		MicroProfileProjectInfo infoFromClasspath = getMicroProfileProjectInfoFromMavenProject(
				MavenProjectName.config_quickstart, MicroProfilePropertiesScope.SOURCES_AND_DEPENDENCIES);
		
		String booleanDefault = "false";
		String intDefault = "0";

		assertProperties(infoFromClasspath,
				9 /* properties from Java sources with ConfigProperty */ + //
				7 /* static properties from microprofile-context-propagation-api */,
	
				// GreetingConstructorResource(
				// 		@ConfigProperty(name = "greeting.constructor.message") String message,
				//		@ConfigProperty(name = "greeting.constructor.suffix" , defaultValue="!") String suffix,
				//		@ConfigProperty(name = "greeting.constructor.name") Optional<String> name)
				p(null, "greeting.constructor.message", "java.lang.String", null, false, "org.acme.config.GreetingConstructorResource",
						null, "GreetingConstructorResource(QString;QString;QOptional<QString;>;)V", 0, null),

				p(null, "greeting.constructor.suffix", "java.lang.String", null, false, "org.acme.config.GreetingConstructorResource",
						null, "GreetingConstructorResource(QString;QString;QOptional<QString;>;)V", 0, "!"),

				p(null, "greeting.constructor.name", "java.util.Optional", null, false, "org.acme.config.GreetingConstructorResource",
						null, "GreetingConstructorResource(QString;QString;QOptional<QString;>;)V", 0, null),

				// setMessage(@ConfigProperty(name = "greeting.method.message") String message)
				p(null, "greeting.method.message", "java.lang.String", null, false, "org.acme.config.GreetingMethodResource",
						null, "setMessage(QString;)V", 0, null),

				// setSuffix(@ConfigProperty(name = "greeting.method.suffix" , defaultValue="!") String suffix)
				p(null, "greeting.method.suffix", "java.lang.String", null, false, "org.acme.config.GreetingMethodResource",
						null, "setSuffix(QString;)V", 0, "!"),

				// setName(@ConfigProperty(name = "greeting.method.name") Optional<String> name)
				p(null, "greeting.method.name", "java.util.Optional", null, false, "org.acme.config.GreetingMethodResource",
						null, "setName(QOptional<QString;>;)V", 0, null)
				);

		assertPropertiesDuplicate(infoFromClasspath);
	}
}

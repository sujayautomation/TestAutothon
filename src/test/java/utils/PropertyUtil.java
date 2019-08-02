package utils;


import java.io.FileReader;
import java.util.Properties;

public class PropertyUtil {
    /**
     * instance object variable for properties.
     */
    private final Properties properties = new Properties();

    /**
     * constructor to initialize instance variables.
     */
    public PropertyUtil() {
        // First load the default.properties file
        try {
            this.properties.load(new FileReader("src/testData/default.properties"));
           /* this.properties.load(getClass().getClassLoader()
                    .getResourceAsStream("D:\\PracticeTestProject\\src\\testData\\default.properties"));*/
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to load default.properties file: "
                    + e.getCause());
        }
    }

/*    public String getHubUrl() {
        return this.properties.getProperty("hub.url") + "/wd/hub";
    }*/

    /**
     * Method used to get value for particular property.
     *
     * @param property
     *            (property)
     * @return property value
     */
    public String getProperty(final String property) {
        return this.properties.getProperty(property);
    }

    /**
     * Method used to set value for particular property.
     */
    public void setProperty(final String key, final String value) {
        this.properties.setProperty(key, value);
    }
}

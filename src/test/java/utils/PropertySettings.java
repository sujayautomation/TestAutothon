package utils;

public enum PropertySettings {
    TESTDATA("/resources/testData.properties"),
    DEFAULTDATA("/resources/default.properties");

    private String value;

    PropertySettings(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

}

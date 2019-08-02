package utils.reporting;

/**
 * Created by Nagesh Arkalgud on 1/7/15.
 * Custom Listener to provide running commentary during test execution
 */

import org.testng.*;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener, ISuiteListener {

    // This belongs to ISuiteListener and will execute before the Suite start
    @Override
    public void onStart(ISuite arg0) {
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "About to begin executing Suite " + arg0.getName(), true);
    }

    // This belongs to ISuiteListener and will execute, once the Suite is finished
    @Override
    public void onFinish(ISuite arg0) {
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "About to end executing Suite " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "About to begin executing Test Group " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "Completed executing Test Group " + arg0.getName(), true);
    }

    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }

    // This belongs to ITestListener and will execute before test start (@Test)
    public void onTestStart(ITestResult arg0) {
        System.out.println("<Thread-id: " + Thread.currentThread().getId() + "> " + "The execution of the test " + arg0.getName() + " starts now");
    }

    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult arg0) {
        printTestResults(arg0);
    }

    // Ignore this
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }

    // This is the method which will be executed in case of test pass or fail
    // This will provide the information on the test
    private void printTestResults(ITestResult result) {
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "Test Method " + result.getName() + " resides in " + result.getTestClass().getName(), true);
        if (result.getParameters().length != 0) {
            String params;
            StringBuilder builder = new StringBuilder();
            for (Object parameter : result.getParameters()) {
                builder.append(String.valueOf(parameter)).append(", ");
            }
            builder.deleteCharAt(builder.length() - 2);
            params =  builder.toString();
            Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "Test Method had the following parameters : " + params, true);
        }
        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "PASSED";
                break;
            case ITestResult.FAILURE:
                status = "FAILED";
                break;
            case ITestResult.SKIP:
                status = "SKIPPED";
        }
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "Test Status of " + result.getName() + " : " + status, true);

        Long durationInSeconds = TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis());
        Reporter.log("<Thread-id: " + Thread.currentThread().getId() + "> " + "Test Duration of " + result.getName() + " in seconds : "
                + durationInSeconds, true);
    }
}

package reqres_restService;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.*;
import static io.restassured.RestAssured.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.ExtentTest;
	

public class GetUserList {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	Response resp;
	@BeforeTest
	public void setExtent()
	{
		htmlReporter = new ExtentHtmlReporter(System.clearProperty("user.dir")+"/test-output/myreport.html");
		htmlReporter.config().setDocumentTitle("Try");
		htmlReporter.config().setReportName("reportName-try");
		htmlReporter.config().setDocumentTitle("Try");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("OS", "Windows");
		
		
	}@AfterTest
	public void endReport()
	{
		extent.flush();
		
	}
	
	@AfterMethod
	public void teardown(ITestResult result)
	{
		if (result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "" + result.getName() + "test case failed");
			System.out.println(result.getName());
			test.log(Status.FAIL, "" + result.getThrowable() + "test case failed,it exception");
		}
		else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "" + result.getName() + "Skippped test case ");
			}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "" + result.getName() + "Passed test case ");
			}
	}
	
@Test	
public void getUserlistbasedonPageNumber(){
	
test=extent.createTest("getUserlistbasedonPageNumber");
resp=given().when().get("https://reqres.in/api/users?page=2").andReturn();
System.out.println(resp.asString());


}

@Test
public void verifyStatusCode() {
	test=extent.createTest("verifyStatusCode");

	System.out.println(resp.statusCode());
	Assert.assertEquals(resp.statusCode(), 201);
	
}


@Test
public void verifyContentType() {
	test=extent.createTest("verifyContentType");

	System.out.println(resp.contentType());
	Assert.assertEquals(resp.contentType(), "application/json; charset=utf-8");
	
}



}

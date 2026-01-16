package practice.test;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectRepositoryUtility.LoginPage;

/**
 * test class for contact module
 * 
 * @author Ashwini
 *
 */
public class SearchContactTest extends BaseClass {
	/**
	 * Scenario - Login()--> Navigate to Contact-->Create contact-->Verify
	 */
	@Test
	public void searchContactTest() {

		/* Step - 1: Login to app */
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp("URL", "UserName", "Password");
	}

}

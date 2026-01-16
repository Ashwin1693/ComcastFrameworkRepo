package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class Sample_ActivateSIM_retryListener extends BaseClass {
	
	@Test(retryAnalyzer = com.comcast.crm.generic.listenerUtility.RetryListenerImp.class)
	public void activateSIM() {
		System.out.println("execute activateSIM test");
		String ActTitle= driver.getTitle();
		Assert.assertEquals(ActTitle, "Login"); //We failed the TestScript intentionally
		
		System.out.println("step - 1");
		System.out.println("step - 2");
		System.out.println("step - 3");
		System.out.println("step - 4");
	}

}

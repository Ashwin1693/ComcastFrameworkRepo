package practice.test;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass{
	@Test
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		String ActTitle= driver.getTitle();
		Assert.assertEquals(ActTitle, "Login"); //We failed the TestScript intentionally
		
		System.out.println("step - 1");
		System.out.println("step - 2");
		System.out.println("step - 3");
		System.out.println("step - 4");
	}
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println("step - 1");
		System.out.println("step - 2");
		System.out.println("step - 3");
		System.out.println("step - 4");
		
	}

}

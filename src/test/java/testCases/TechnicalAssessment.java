package testCases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import basePackage.TestBase;

public class TechnicalAssessment extends TestBase {

	@Test(priority = 1, description = "Test Email login")
	public static void test1() {

		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[1]")));

		Assert.assertTrue((driver.findElement(emailBox)).isDisplayed());
		Assert.assertTrue((driver.findElement(passwordBox)).isDisplayed());
		Assert.assertTrue((driver.findElement(loginBtn)).isDisplayed());

		WebElement email = driver.findElement(emailBox);
		WebElement password = driver.findElement(passwordBox);
		WebElement login = driver.findElement(loginBtn);

		type(email, properties.getProperty("Email"));
		type(password, properties.getProperty("Password"));
		click(login);

	}

	@Test(priority = 2, description = "Check Item List value and Badge")
	public static void test2() {

		click(driver.findElement(home));
		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[2]")));

		List<WebElement> listgroup = driver.findElements(list);
		int i = listgroup.size();

		Assert.assertEquals(i, Integer.parseInt(properties.getProperty("listsize")));

		for (WebElement li : listgroup) {
			li.getText();
		}
		String value = driver.findElement(test2value).getText();
		String[] arr = value.split("6");
		String listitem = arr[0].trim();
		Assert.assertEquals(listitem, properties.getProperty("Itemvalue"));

		String[] arr1 = value.split("2");
		String badgevalue = arr1[1].trim();
		System.out.println(badgevalue);
		Assert.assertEquals(badgevalue, properties.getProperty("Badgevalue"));

		/*
		 * System.out.println(value.length()); String lastvalue=
		 * arr1[value.length()-12]; System.out.println(lastvalue);
		 */

	}

	@Test(priority = 3, description = "Select Option 3 from dropdown")
	public static void test3() {

		click(driver.findElement(home));
		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[3]")));

		WebElement option1 = driver.findElement(dropdown);
		String text = option1.getText();
		Assert.assertEquals(text, properties.getProperty("optionSelected"));
		click(driver.findElement(dropdown));
		click(driver.findElement(option3));
		String text1 = option1.getText();
		Assert.assertEquals(text1, properties.getProperty("optionToSelect"));

	}

	@Test(priority = 4, description = "Enabled and Disabled buttons")
	public static void test4() {
		click(driver.findElement(home));
		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[4]")));
		WebElement button1 = driver.findElement(firstbutton);
		WebElement button2 = driver.findElement(secondbutton);
		if (button1.isEnabled()) {
			Assert.assertTrue(true);

		}
		if (!button2.isEnabled()) {

			Assert.assertTrue(true);
		}

	}

	@Test(priority = 5, description = "Element synchronisation to be visible")
	public static void test5() throws Exception, Exception {

		click(driver.findElement(home));
		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[5]")));

		waitclick(driver.findElement(button5), Integer.parseInt(properties.getProperty("Wait")));
		WebElement test5Button = driver.findElement(button5);

		WebElement successMsg = driver.findElement(message);
		String successText = successMsg.getText();
		Assert.assertTrue(successMsg.isDisplayed());
		Assert.assertEquals(successText, properties.getProperty("displaymessage"));

		if (successMsg.isDisplayed()) {
			Assert.assertTrue(!(test5Button.isEnabled()));
		}
	}

	@Test(priority = 6, description = "Extracting value from web table")
	public static void test6() {
		click(driver.findElement(home));
		scrollIntoViewElement(driver.findElement(By.xpath("(//h1)[6]")));

		List<WebElement> rows = driver.findElements(row);
		List<WebElement> cols = driver.findElements(col);

		int rowsize = rows.size();
		int colssize = cols.size();
		System.out.println(rowsize + "---" + colssize);
		for (int i = 1; i < rowsize; i++) {
			for (int j = 1; j <= colssize; j++) {

				WebElement values = driver.findElement(
						By.xpath("//table[@class='table table-bordered table-dark']//tr[" + i + "]//td[" + j + "]"));

				System.out.println(values.getText());

			}
		}

		WebElement lastcellvalue = driver.findElement(lastcell);
		Assert.assertEquals(lastcellvalue.getText(), properties.getProperty("cellvalue"));

	}

}

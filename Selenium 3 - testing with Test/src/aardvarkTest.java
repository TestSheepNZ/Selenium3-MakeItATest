/*
 * The following reuses previous scripts using JUnit and includes 
 * both assertions and validations
 * 
 * This code is linked to the following blog article,
 * http://testsheepnz.blogspot.co.nz/2016/08/automation-24-gui-11-unlocking-true.html
 * 
 * For more information - please reread.
 * 
 * Mike Talks, Aug 2016
 */

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import static org.junit.Assert.*;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;

public class aardvarkTest {
	
	@Test
	public void thisTestWillFail() 
	{
		
		System.out.println("thisTestWillFail");
		
        // Create a new instance of the Chrome driver
        // This copies all the information about the page we've loaded into
        // the page_item object
        //WebDriver page_item = new FirefoxDriver();
    	WebDriver driver = new ChromeDriver();
    	
        // Open our target page ... a previous blog article
        driver.get("http://testsheepnz.blogspot.co.nz/2016/07/im-hoping-that-this-blog-will-have-most.html");

        /*
         * THIS NEXT STEP WILL FAIL
         */
                
        //Assert we're on the right page - this will fail outright if not
        assertTrue ("Verify we're on the right blog page - THIS WILL FAIL",
        		driver.getPageSource().contains("THIS TEXT IS WRONG"));
        
        /*
         * THE TEST WILL NOT GET FURTHER THAN THIS
         */
        
        
		// Verify if there is a blue aardvark in the page
		try { 
			assertTrue("Verify the page contains that blue aardvark", 
					(driver.getPageSource().contains("blue aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
       
		// Verify if there is a blue aardvark in the page
		try { 
			assertTrue("Verify the page contains that red aardvark", 
					(driver.getPageSource().contains("red aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
             
        // We are now going to capture an element of the current page
        // I've used developer tools to find the ID for it
        // This is the main body of the piece (and excludes comments & title)
        WebElement element = driver.findElement(By.id("post-body-8711143007795160191"));
       
		// Verify if there is a blue aardvark in the post body
		try { 
			assertTrue("Verify the page contains that blue aardvark", 
					(element.getText().contains("blue aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
        
		// Verify if there is a blue aardvark in the post body
		try { 
			assertTrue("Verify the page contains that red aardvark", 
					(element.getText().contains("red aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}

	}

	@Test
	public void createCommentAndReview() 
	{
		
		System.out.println("createCommentAndReview");

		// Create a new instance of the Chrome driver
		// This copies all the information about the page we've loaded into
		// the driver object
		WebDriver driver = new ChromeDriver();

		// Open our target page ... a previous blog article
		driver.get("http://testsheepnz.blogspot.co.nz/2016/07/im-hoping-that-this-blog-will-have-most.html");

		// Print out the page title
		System.out.println("Page title is: " + driver.getTitle());

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Find the frame containing "enter your comment"
        java.util.List<WebElement> iframes
        	= driver.findElements(By.tagName("iframe"));
		int chosenFrame = -1;
		for (int i=0 ; i < iframes.size() ; i++)
		{
			//This moves you back out of the frame you've just been in
			driver.switchTo().defaultContent();
			System.out.println("Check frame");
			
			//Select the ith frame
			driver.switchTo().frame(i);
			if (driver.getPageSource().contains("Enter your comment"))
			{
				chosenFrame = i;
				System.out.println("Found frame");
				break;
			}          
        }

		// Select to comment as "anonymous"
		Select select = new Select(driver.findElement(By.name("identityMenu")));
		// select.selectByIndex(8);
		select.selectByValue("ANON");

		// Select the comment body
		String stringComment = "This is my comment";
		WebElement element = driver.findElement(By.id("commentBodyField"));
		element.sendKeys(stringComment);

		// Select to preview
		element = driver.findElement(By.id("postCommentPreview"));
		element.submit();
		
		//Assert that the stringComment can be seen on the page
		assertTrue("Assert that we can see " + stringComment + "on page", 
				driver.getPageSource().contains(stringComment));

		// Verify that we are told "Anonymous said" 
		try { 
			assertTrue("Verify the page contains that 'Anonymous said'", 
					(driver.getPageSource().contains("Anonymous") && driver.getPageSource().contains("said"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}

	}
	
	@Test
	public void checkForRedAndBlue() 
	{
		
		System.out.println("checkForRedAndBlue");
		
        // Create a new instance of the Chrome driver
        // This copies all the information about the page we've loaded into
        // the page_item object
        //WebDriver page_item = new FirefoxDriver();
    	WebDriver driver = new ChromeDriver();
    	
        // Open our target page ... a previous blog article
        driver.get("http://testsheepnz.blogspot.co.nz/2016/07/im-hoping-that-this-blog-will-have-most.html");

        //Assert we're on the right page - this will fail outright if not
        assertTrue ("Verify we're on the right blog page",
        		driver.getPageSource().contains("I'm hoping that this blog will have the most comments"));
        
		// Verify if there is a blue aardvark in the page
		try { 
			assertTrue("Verify the page contains that blue aardvark", 
					(driver.getPageSource().contains("blue aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
       
		// Verify if there is a blue aardvark in the page
		try { 
			assertTrue("Verify the page contains that red aardvark", 
					(driver.getPageSource().contains("red aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
             
        // We are now going to capture an element of the current page
        // I've used developer tools to find the ID for it
        // This is the main body of the piece (and excludes comments & title)
        WebElement element = driver.findElement(By.id("post-body-8711143007795160191"));
       
		// Verify if there is a blue aardvark in the post body
		try { 
			assertTrue("Verify the page contains that blue aardvark", 
					(element.getText().contains("blue aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}
        
		// Verify if there is a blue aardvark in the post body
		try { 
			assertTrue("Verify the page contains that red aardvark", 
					(element.getText().contains("red aardvark"))  );
				}
			catch (Error e) {
				System.out.println("ERROR");
				System.out.println(e.toString());
			}

	}
	
	

	
}

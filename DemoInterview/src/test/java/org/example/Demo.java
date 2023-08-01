package org.example;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Demo {
    public static void main(String[] args) {



        // Chrome Driver path
        System.setProperty("webdriver.chrome.driver", "/Users/cesaribanezgarcia/Downloads/chromedriver_mac64/chromedriver");

        //Invoking Browser
        ChromeDriver driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();

        // Navigate to web page
        driver.get("https://surveyrc.taxcreditco.com/automation-challenge");

        // Set Implicit Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        // Submitting Basic Information within initial page and click on Next button
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[1]")).sendKeys("Cesar");
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[2]")).sendKeys("Ibanez");
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[3]")).sendKeys("test_email@gmail.com");
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[4]")).sendKeys("Strip");
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[5]")).sendKeys("Las Vegas");
        driver.findElement(By.xpath("(//input[starts-with(@name,'SurveyControl$Question')])[6]")).sendKeys("12345");
        driver.findElement(By.id("SurveyControl_SurveySubmit")).click();


        // Answer “NO” to all questions and click on next button within following page
        By xpathExpression = By.xpath("//label[contains(@aria-pressed,'false') and text()='No']");

        // Find all matching elements
        List<WebElement> labelElements = driver.findElements(xpathExpression);

        // Click on each matching element using JavascriptExecutor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        for (WebElement element : labelElements) {
            jsExecutor.executeScript("arguments[0].click();", element);
        }

        //Click on next button using JavascriptExecutor
        WebElement nextButton = driver.findElement(By.id("SurveyControl_SurveySubmit"));
        jsExecutor.executeScript("arguments[0].click();", nextButton);


        // Click on submit form button
        driver.findElement(By.id("SurveyControl_SurveySubmit")).click();


        // Assert that you were redirected to “https://www.experian.com/employer-services
        // Get the current URL of the page
        String currentUrl = driver.getCurrentUrl();

        // Define the expected URL
        String expectedPartialUrl = "https://www.experian.com/employer-services";


        // Log the current URL to the console
        System.out.println("Current URL: " + currentUrl);

        // Assert that the current URL matches the expected URL
        if (currentUrl.contains(expectedPartialUrl)) {
            System.out.println("Redirected to the correct URL: " + expectedPartialUrl);
        } else {
            System.out.println("Redirect URL is not as expected. Expected: " + expectedPartialUrl);
        }


        // Close the browser
        driver.quit();















    }
}

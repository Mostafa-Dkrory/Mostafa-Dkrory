package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BaseClass {
    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BaseClass(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    protected void clickOn(By element) {
        //wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        driver.findElement(element).click();
    }

    protected void sendKeys(By element, String text) {
        driver.findElement(element).sendKeys(text);

    }

    protected String getText(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
    }

    protected void selectByText(By element, String txt) {
        //WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(element));
        WebElement webElement = driver.findElement(element);
        Select select = new Select(webElement);
        select.selectByVisibleText(txt);
    }
    protected void selectByValue(By element, String txt) {
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(element));
        Select select = new Select(webElement);
        select.selectByValue(txt);
    }
    protected void selectByIndex(By element, int index) {
        WebElement webElement = driver.findElement(element);
        Select select = new Select(webElement);
        select.selectByIndex(index);
    }

    protected WebElement waitToBeClickable(By locator) {

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected WebElement waitToPresent(By locator) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected Boolean isDisplayed(By locator) {
        return  driver.findElement(locator).isDisplayed();
    }

    protected void scrollTo(By element) {
        WebElement webElement = waitToPresent(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    protected void hoverOn(By element) {
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(element));
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
    }

    protected List<WebElement> getWebElements(By element) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    protected String getAttribute(By element, String attribute) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element)).getAttribute(attribute);
    }

    public void switchToNext(){
        Set<String> windowHandles = driver.getWindowHandles();
        String currentWindowHandle = driver.getWindowHandle();
        for (Iterator<String> iterator = windowHandles.iterator(); iterator.hasNext(); ) {
            String windowHandle = iterator.next();
            if (windowHandle.equals(currentWindowHandle)) {
                windowHandle = iterator.next();
                driver.switchTo().window(windowHandle);
                break; // Switched to the next tab
            }
        }

        //driver.switchTo().window(driver.getWindowHandles().stream().skip(1).findFirst().orElse(null));
    }public void switchToOriginal(){
        driver.switchTo().window(driver.getWindowHandle());    }
    public String getCurrentUrl(){

        return driver.getCurrentUrl();
    }
    public void closeTab(){
        driver.close();
    }
}
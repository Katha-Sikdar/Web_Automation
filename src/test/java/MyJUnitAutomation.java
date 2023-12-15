import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJUnitAutomation{

    WebDriver driver; // declare webdriver as a global element
    @BeforeAll

    //Web browser limit set
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void writeSomething(){
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Katha Sikdar");//class
        driver.findElements(By.className("mr-sm-2")).get(1).sendKeys("ferdouskatha@gmail.com");
        driver.findElements(By.className("form-control")).get(2).sendKeys("Dhaka");
        driver.findElements(By.tagName("textarea")).get(1).sendKeys("Gazipur");

        Utils.scroll(driver,0,500);

        driver.findElements(By.cssSelector("[type='button']")).get(1).click();
        String nameActual=driver.findElement(By.id("name")).getText();
        String nameExpected="Katha Sikdar";
        Assertions.assertTrue(nameActual.contains(nameExpected));



    }
    @Test //annotation
    public void clickButton(){
        driver.get("https://demoqa.com/buttons");//site url

        List<WebElement> button = driver.findElements(By.className("btn-primary"));
        Actions actions = new Actions(driver);
        actions.doubleClick(button.get(0)).perform();
        actions.contextClick(button.get(1)).perform(); //rightclick
        actions.click(button.get(2)).perform();

        //Assertion of button texts

      List<WebElement>  textActual = driver.findElements(By.tagName("p"));
      String t1=textActual.get(0).getText();
      String t2=textActual.get(1).getText();
      String t3=textActual.get(2).getText();

      Assertions.assertTrue(t1.contains("double click"));
      Assertions.assertTrue(t2.contains("right click"));
      Assertions.assertTrue(t3.contains("dynamic click"));
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get(("https://demoqa.com/alerts"));
        driver.findElement(By.id("alertButton")).click();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        //driver.switchTo().alert().dismiss();
        //driver.findElement(By.id(("promptButton"))).click();
        driver.switchTo().alert().sendKeys("Katha");

        }
    @Test
   public void handleCalendar(){

       driver.get(("https://demoqa.com/date-picker"));
       WebElement txtCalendar = driver.findElement(By.id("datePickerMonthYearInput"));
       txtCalendar.click();
       txtCalendar.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
       txtCalendar.sendKeys("12/20/20203");
       txtCalendar.sendKeys(Keys.ENTER);
   }
   @Test
    public void selectDropdown(){

        driver.get(("https://demoqa.com/select-menu"));
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select select=new Select(dropdown);
        select.selectByVisibleText("White");

    }

    @Test
    public void selectMultipleDropdown(){
        driver.get(("https://demoqa.com/select-menu"));
        Utils.scroll(driver);
        WebElement dropdown = driver.findElement(By.id("cars"));
        Select select=new Select(dropdown);
        if (select.isMultiple()){
            select.selectByVisibleText("Volvo");
            select.selectByVisibleText("Audi");
        }
    }
    @Test
    public void selectWithOptDropdown() throws InterruptedException {
        driver.get(("https://demoqa.com/select-menu"));
        WebElement dropdown = driver.findElement(By.id("withOptGroup"));
        dropdown.click();
       /*Thread.sleep(1000);
       dropdown.sendKeys(Keys.ARROW_DOWN);
       Thread.sleep(1000);
       dropdown.sendKeys(Keys.ARROW_DOWN);
       Thread.sleep(500);
       dropdown.sendKeys(Keys.ENTER); */

        Actions actions=new Actions(driver);
        for (int i=0;i<2;i++){

            actions.keyDown(Keys.ARROW_DOWN).perform();
            Thread.sleep(1000);

        }

        actions.sendKeys(Keys.ENTER);

        //actions.keyDown(Keys.ARROW_DOWN).perform();
        //Thread.sleep(1000);
        //dropdown.sendKeys(Keys.ENTER);
    }
@Test
    public void mouseHover(){
        driver.get("https://daffodilvarsity.edu.bd/");
        WebElement menu = driver.findElement(By.xpath("//a[contains(text(),\"Admissions\")]"));
        Actions actions =  new Actions(driver);
        actions.moveToElement(menu).perform();


    }

    @Test

    public void keyboardEvents() throws InterruptedException {
        driver.get("https://www.google.com/");
        WebElement searchElement = driver.findElement(By.name("q"));
        Actions action = new Actions(driver);
        action.moveToElement(searchElement);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("Resources for SQA")
                .keyUp(Keys.SHIFT)
                .doubleClick()
                .contextClick()
                .perform();

        Thread.sleep(5000);
    }

@Test
    public void modalDialog() throws InterruptedException {
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showSmallModal")).click();
        String text= driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        driver.findElement(By.id("closeSmallModal")).click();
        Thread.sleep(5000);
    }

@Test
    public void uploadImage(){
        driver.get("https://demoqa.com/upload-download");
        System.out.println(System.getProperty("user.dir"));
        driver.findElement(By.id("uploadFile")).sendKeys(System.getProperty("user.dir")+"./src/test/resources/15.PNG");

    //driver.findElement(By.id("uploadFile")).sendKeys(System.getProperty("user.dir")+"./src/test/resources/15.PNG");
    }


    @AfterAll
    public void closeDriver(){
        //driver.quit();
    }
}

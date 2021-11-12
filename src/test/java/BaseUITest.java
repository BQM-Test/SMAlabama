import org.openqa.selenium.WebDriver;

public class BaseUITest {

    public WebDriver driver;

    public String getTitle(){
        return driver.getTitle();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}

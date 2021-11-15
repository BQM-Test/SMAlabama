import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlabamaRegisterPage extends BasePage{

    public AlabamaRegisterPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getH1TitleRegister(){
        return  driver.findElement(By.xpath("(//*[contains(text(), ' Registro de usuario ')])")).getText();


    }


}

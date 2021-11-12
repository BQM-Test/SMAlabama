import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlabamaApuestasPage extends BasePage {

    WebDriver driver;

    public AlabamaApuestasPage(WebDriver remoteDriver){
        driver = remoteDriver;
    }

    public List<WebElement> getTitleTodosLosEventos(){

        return driver.findElements(By.xpath("//div[contains(text(), 'Todos')]"));

    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public WebElement divListTornesDeportes(){
        WebElement getListDeportesMenuApuestas = driver.findElement(By.xpath("//div[@class='dropdown-menu show']"));
        return getListDeportesMenuApuestas;
        //div[@class='dropdown-menu show']
    }

    /*public List<WebElement> getListGana(){
        return driver.findElements(By.xpath("//*[contains(text(), 'Gana')]"));
    }*/
    public List<WebElement> getListGana(){
        return driver.findElements(By.xpath("//*[@class='texto md hydrated'][contains(text(), 'Gana')]"));
    }

    public List<WebElement> btnApostar(){

        return driver.findElements(By.xpath("//button[@type='button'][contains(text(), ' Apostar ')]"));
    }

    public List<WebElement> getListEmpate(){
        return driver.findElements(By.linkText("Empate"));
    }

    public List<WebElement> getListEquipos(){
        return driver.findElements(By.xpath("//h2[@class='texto equipo']"));
    }

    public List<WebElement> getLink(){

        return driver.findElements(By.tagName("a"));
    }

}

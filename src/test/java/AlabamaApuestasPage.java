import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
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

    @Test
    public List<WebElement> listEventosTodos(){
        AlabamaApuestasPage alabamaApuestasPage =   new AlabamaApuestasPage(driver);
        //Busco encontrar todos los eventos de un deporte que selecciono para poder apostar y capto sus nombres
        List<WebElement> getListEventos = alabamaApuestasPage.getListEquipos();
        System.out.println(getListEventos.size());

        ArrayList<WebElement> eventosGanaNombre = new ArrayList<WebElement>();

        for (int i = 0; i <getListEventos.size() ; i++) {
            WebElement equipo = getListEventos.get(i);
            if(equipo.isDisplayed()){
                eventosGanaNombre.add(equipo);

            }
        }
        return eventosGanaNombre;
    }

}

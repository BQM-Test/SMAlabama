import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

public class MiLibreria extends BasePage {

    public MiLibreria(WebDriver remoteDriver) {
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
    }

    /*
    * Metodo para disminuir el zoom del navegador (Chrome / Firefox)
    * Recibe cuantas veces debe hacer click en el boton "-"
    * */
    public static void disminuirZoom(int cant) throws AWTException, InterruptedException {
        Thread.sleep(3000);
        Robot robot = new Robot();

        for (int i = 0; i < cant; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_SUBTRACT);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    /*
     * Metodo para aumenta el zoom del navegador (Chrome / Firefox)
     * Recibe cuantas veces debe hacer click en el boton "+"
     * */
    public static void aumentarZoom(int cant) throws AWTException, InterruptedException {
        Thread.sleep(3000);
        Robot robot = new Robot();

        for (int i = 0; i < cant; i++) {
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_ADD);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        }
    }

    /*
    * El metodo valida la existencia de un WebElement en el sitio,
    * Variables: driver que se esta usando, localiador que se va a usar en el By
    * ej: (By.xpath("(//span[contains(text(), 'Datos personales')])")
    */
    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public void scrollObjeto (WebElement aObject){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",aObject);

    }


    public void btnEmpty (){

    int invalidEmptyButton = 0;
    int emptyNameAttribute = 0;
    int emptyIdAttribute = 0;

    List<WebElement> buttonTagsList = driver.findElements(By.tagName("button"));


for(WebElement element : buttonTagsList){
        if (element.getText().isEmpty() == true){
            invalidEmptyButton++;
        }
        System.out.println(element.getAttribute("id"));
        if (element.getAttribute("id").isEmpty()){
            emptyIdAttribute++;
        }
        if (element.getAttribute("name").isEmpty()){
            emptyNameAttribute++;
        }
    }
System.out.println("Cantidad de botones vacios " + invalidEmptyButton);
System.out.println("Cantidad de botones sin id " + emptyIdAttribute);
System.out.println("Cantidad de botones sin attr name " + emptyNameAttribute);

    }

}

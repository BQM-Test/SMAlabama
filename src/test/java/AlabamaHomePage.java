import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AlabamaHomePage extends BasePage {


    public AlabamaHomePage(WebDriver remoteDriver){
        driver = remoteDriver;
    }
public String validarTituloSMAlabama() {
        return driver.getTitle();

    }
//POP UP INGRESAR------
    public void clickBtnIngresar(){

        driver.findElement(By.xpath("(//*[contains(text(), ' Ingresar ')])")).click();
    }

    public void clickBtnRegistrarse(){

        driver.findElement(By.xpath("(//*[contains(text(), ' Registrarse ')])")).click();
    }

    public void fillloginPorUpIngresar(){

        driver.findElement(By.id("login-username")).sendKeys("tilinalab1");
        driver.findElement(By.id("login-password")).sendKeys("12345jj");
    }
    public void clickBtnpopUpIngresar(){

        driver.findElement(By.xpath("//button[contains(text(), ' Ingresar ')][@type='submit']")).click();

    }

    public void clickBtnpopUpOlvidasteContrasena(){

        driver.findElement(By.xpath("//button[@type='button'][@class='btn btn-link']")).click();

    }

    public void fillingUsrNoRegisterIngresar(){

        Faker faker_data = new Faker();

        String userName = faker_data.internet().domainName();
        String pass  = faker_data.internet().password();
        System.out.println("---> utilizo el siguiente userName: " + userName);
        System.out.println("---> utilizo el siguiente pass: " + pass);
        driver.findElement(By.id("login-username")).sendKeys(userName);
        driver.findElement(By.id("login-password")).sendKeys(pass);


    }


    public String errorTextUsrPassIncorrect(){

        String textError=driver.findElement(By.id("toast-container")).getText();
        return textError;
        //*[contains(text(), 'Usuario y/o clave incorrectos')")
    }



    //-------------

//Ir a apuestas

    public void listaApuestasPrematch(){

        driver.findElement(By.xpath("//*[@class='dropbtn'][contains(text(), 'Apuestas')]")).click();
         //a[@id='dropdownBasic']
        //a[contains(text(), ' Apuestas ')]
        //a[@id='dropdownBasic'][contains(text(), ' Apuestas ')]
    }


    public String apuestasHome(){

        String apuhome = driver.findElement(By.xpath("//button[@class='dropbtn'][contains(text(), 'Apuestas')]")).getText();
        return apuhome;
    }
    public String virtualesHome(){

        String virHome = driver.findElement(By.xpath("//button[@class='dropbtn'][contains(text(), 'Virtuales')]")).getText();
        return virHome;
    }

    public String resultadosHome(){

        String resultHome = driver.findElement(By.xpath("//button[contains(text(), 'Resultados')]")).getText();
        return resultHome;
    }




    public void listDeportesApuestas(){

         driver.findElement(By.xpath("//div[@class='dropdown-content'][contains(text(), 'Apuestas')]")).click();
        //a[@id='dropdownBasic']
        //a[contains(text(), ' Apuestas ')]
        //a[@id='dropdownBasic'][contains(text(), ' Apuestas ')]

    }
    public WebElement divListDeportes(){
        WebElement getListDeportesMenuApuestas = driver.findElement(By.xpath("(//div[@class='dropdown-content'])[1]"));
       return getListDeportesMenuApuestas;

    }
    /*public WebElement apuestasListDeportes(){
        WebElement getListDepoApuestas = driver.findElement(By.xpath("//button[@class='dropbtn'][contains(text(), 'Apuestas')]"));
        return getListDepoApuestas;
    }*/

    public WebElement apuestaHome(){
        WebElement apuhome =driver.findElement(By.xpath("//button[@class='dropbtn'][contains(text(), 'Apuestas')]"));
        return apuhome;
    }

    public List<WebElement> listBtnApuestas(){
   // return driver.findElements(By.xpath("//a[@class='dropdown-content']"));
        //return driver.findElements(By.xpath("//*[@class='idropdown'][contains(text(), 'Apuestas')]"));
        //return driver.findElements(By.xpath("//div[@class='idropdown'][1]").xpath("//div[@class='dropdown-content']"));
        return driver.findElements(By.xpath("(//div[@class='dropdown-content']/a)"));

    }


    public List<WebElement> getLink(){
        return driver.findElements(By.tagName("a"));
    }
}




import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlabamaRegisterPage extends BasePage{




    public AlabamaRegisterPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);    //para utilizar los @Find By...
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getH1TitleRegister(){
        return  driver.findElement(By.xpath("(//*[contains(text(), ' Registro de usuario ')])")).getText();

    }

    //Registrarme luego de llenado de info
    @FindBy(xpath = "(//button[contains(text(), 'Registrarme')])")
    public WebElement clickRegistrarme;

    @FindBy(id="nombres")
    public WebElement rNombre;

    public void rClickRegistrarme () throws InterruptedException {
        //jse.executeScript("window.scrollTo(0, 500)");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",clickRegistrarme);
        Thread.sleep(3000);

        clickRegistrarme.click();

    }

    public String msjErrorNombre () throws InterruptedException {
        //filling information
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("window.scrollTo(0, 500)");
        jse.executeScript("arguments[0].scrollIntoView(true);",rNombre);
        Thread.sleep(3000);
        String msjErrorNombre = nombreError.getText();
        return msjErrorNombre;
   }

    @FindBy (id="apellidos")
    public WebElement rApellidos;

    //Fecha Nac

    /*public WebElement rSelectDay(){
        WebElement selectedDay = driver.findElement(By.id("selectedDay"));
        return selectedDay;
    }

    public WebElement rSelectedMonth(){
        WebElement selectedMonth = driver.findElement(By.id("selectedMonth"));
        return selectedMonth;
    }

    public WebElement rSelectedYear(){
        WebElement selectedYear = driver.findElement(By.id("selectedYear"));
        return selectedYear;
    }*/

    @FindBy (id="selectedDay")
    public WebElement rSelectDay;

    @FindBy (id="selectedMonth")
    public WebElement rSelectedMonth;

    @FindBy (id="selectedYear")
    public WebElement rSelectedYear;
       //****

    @FindBy (id="document")
    public WebElement rDocument;

    public WebElement rDocument(){
        WebElement rDocument = driver.findElement(By.id("document"));
        return rDocument;
    }

    @FindBy (id="radio-ci-tradicional")
    public WebElement rRadioCITradicional;

    @FindBy (id="radio-ci-chip")
    public WebElement rRadioCIChip;

    @FindBy (id="serie")
    public WebElement rSerie;

    @FindBy (id="folioNumber")
    public WebElement rFolioNumber;

    @FindBy (id="cellphone")
    public WebElement rCellphone;

    @FindBy (id="betBySMS") //checkBox
    public WebElement rBetBySMS;

    @FindBy (id="email")
    public WebElement rEmail;

    @FindBy (id="receiveEmails") //checkBox
    public WebElement rReceiveEmails;

    @FindBy (id="location")
    public WebElement rLocation;

    @FindBy (id="usuario")
    public WebElement rUsuario;

    @FindBy (id="password")
    public WebElement rPassword;

    @FindBy (id="passwordRetyped")
    public WebElement rPasswordRetyped;

    @FindBy (id="acceptTermsAndConditions") //checkBox
    public WebElement rAcceptTermsAndConditions;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre debe de tener al menos 2 caracteres y máximo 100 caracteres')])")
    public WebElement nombreError;

    @FindBy (xpath = "(//span[contains(text(), ' El apellido debe de tener al menos 2 caracteres y máximo 100 caracteres')])")
    public WebElement apellidoError;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre no debe tener números ni caracteres especiales')])")
    public WebElement nombreNumError;

    @FindBy (xpath = "(//span[contains(text(), ' El apellido no debe tener números ni caracteres especiales')])")
    public WebElement apellidoNumError;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre es obligatorio ')])")
    public WebElement nombreVacioError;

    @FindBy (xpath = "(//span[contains(text(), ' El apellido es obligatorio ')])")
    public WebElement apellidoVacioError;

    @FindBy (xpath = "(//span[contains(text(), ' El número de documento es obligatorio ')])")
    public WebElement docIncorrectoVacio;

    @FindBy (xpath = "(//span[contains(text(), ' El formato del número de documento es incorrecto ')])")
    public WebElement docIncorrecto; //puede contener una letra y el msj de error es este


     public static Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public static Date sumarRestarYearFecha(Date fecha, int year){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.YEAR, year);  //

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

    }

    public void fillingRegister (String aNombre,String aApellido, String aCI, String aSerie, String aFolioNumber
                                , String aCellphone, String aEmail, String aLocation, String aUsuario,
                                 String aPassword, String aPasswordRetyped) throws InterruptedException {

        JavascriptExecutor jse = (JavascriptExecutor)driver;

    //filling information
        rNombre.sendKeys(aNombre);
        rApellidos.sendKeys(aApellido);
        rDocument.sendKeys(aCI);
        jse.executeScript("arguments[0].scrollIntoView(true);",rSerie);
        Thread.sleep(3000);
        rSerie.sendKeys(aSerie);
        if( rFolioNumber.isEnabled()){
            rFolioNumber.sendKeys(aFolioNumber);
        }
        rCellphone.sendKeys(aCellphone);
        rBetBySMS.click();
        rEmail.sendKeys(aEmail);
        rReceiveEmails.click();
        Select selectDay = new Select(rLocation);
        selectDay.selectByVisibleText(aLocation);
        rUsuario.sendKeys(aUsuario);
        rPassword.sendKeys(aPassword);
        rPasswordRetyped.sendKeys(aPasswordRetyped);
        rAcceptTermsAndConditions.click();


    }

    public void fillingUserData(String name, String psw){

    }

}

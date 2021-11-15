import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    //Registrarme luego de llenado de info
    @FindBy(xpath = "(//button[contains(text(), 'Registrarme')])")
    public WebElement clickRegistrarme;

    @FindBy(id="nombres")
    public WebElement rNombre;

    @FindBy (id="apellidos")
    public WebElement rApellidos;
       //Fecha Nac

    public WebElement rSelectDay(){
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
    }

   /* @FindBy (id="selectedDay")
    public WebElement rSelectDay;

    @FindBy (id="selectedMonth")
    public WebElement rSelectedMonth;

    @FindBy (id="selectedYear")
    public WebElement rSelectedYear;*/
       //****

    @FindBy (id="document")
    public WebElement rDocument;

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


    // Suma los días recibidos a la fecha
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

}

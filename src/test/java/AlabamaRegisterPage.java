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
    public WebElement   rNombre;

    public void rClickRegistrarme () throws InterruptedException {
        //jse.executeScript("window.scrollTo(0, 500)");
        scrollObjeto(clickRegistrarme);
        Thread.sleep(3000);

        clickRegistrarme.click();
    }

    public String msjErrorNombre () throws InterruptedException {
        //filling information
        scrollObjeto(rNombre);
        Thread.sleep(3000);
        String msjErrorNombre = nombreError.getText();
        return msjErrorNombre;
   }

    @FindBy (id="apellidos")
    public WebElement rApellidos;

    @FindBy (id="selectedDay")
    public WebElement rSelectDay;

    @FindBy (id="selectedMonth")
    public WebElement rSelectedMonth;

    @FindBy (id="selectedYear")
    public WebElement rSelectedYear;
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

    //msjs de error de los campos de llenado en Registro de usuario
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

    @FindBy (xpath = "(//span[contains(text(), 'Debe seleccionar una fecha')])")
    public WebElement fechaNacObligatoria;

    @FindBy (xpath = "(//span[contains(text(), ' El usuario debe ser mayor de edad')])")
    public WebElement fechaNacMayor;

    @FindBy (xpath = "(//span[contains(text(), ' El número de documento es obligatorio ')])")
    public WebElement docIncorrectoVacio;

    @FindBy (xpath = "(//span[contains(text(), ' El formato del número de documento es incorrecto ')])")
    public WebElement docIncorrecto; //puede contener una letra y el msj de error es este

    @FindBy (xpath = "(//span[contains(text(), ' El identificador es obligatorio ')])")
    public WebElement identificadorObligatorio;

    @FindBy (xpath = "(//span[contains(text(), ' El identificador solo acepta letra y/o números y debe empezar con un número ')])")
    public WebElement identificadorIncorrecto;

    @FindBy (xpath = "(//span[contains(text(), ' El identificador debe tener 9 caracteres ')])")
    public WebElement identificadorCantCaraMenorNueve;

    @FindBy (xpath = "(//span[contains(text(), ' La serie es obligatoria ')])")
    public WebElement serieObligatoria;

    @FindBy (xpath = "(//span[contains(text(), ' La serie debe ser una única letra ')])")
    public WebElement serieLetra;

    @FindBy (xpath = "(//span[contains(text(), ' El número de folio es obligatorio ')])")
    public WebElement numFolioObligatorio;

    @FindBy (xpath = "(//span[contains(text(), ' El número de folio sólo acepta números ')])")
    public WebElement numFolioSoloNum;

    @FindBy (xpath = "(//span[contains(text(), ' El número de folio debe de tener 6 números ')])")
    public WebElement numFolioMax6;

    @FindBy (xpath = "(//span[contains(text(), ' El número de celular es obligatorio ')])")
    public WebElement cellObligatrio;

    @FindBy (xpath = "(//span[contains(text(), ' El número de celular es incorrecto ')])")
    public WebElement cellIncorrecto; //no tiene la cant de digitos de un cel o contiene letra

    @FindBy (xpath = "(//span[contains(text(), ' El email es obligatorio ')])")
    public WebElement emailObligatorio;

    @FindBy (xpath = "(//span[contains(text(), ' El email no es correcto ')])")
    public WebElement emailIncorrecto;

    @FindBy (xpath = "(//span[contains(text(), 'El departamento es obligatorio')])")
    public WebElement depObligatorio;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre de usuario es obligatorio ')])")
    public WebElement usrObligatorio;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre de usuario debe de tener al menos 3 caracteres. ')])")
    public WebElement usrCantMenorTresCara;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre de usuario debe de tener menos de 23 caracteres ')])")
    public WebElement usrCantMenorVeintiTresCara;

    @FindBy (xpath = "(//span[contains(text(), ' El nombre de usuario no debe contener espacios ni caracteres especiales')])")
    public WebElement usrCaraEspeciales;

    @FindBy (xpath = "(//span[contains(text(), ' La contraseña es obligatoria ')])")
    public WebElement passObligatoria;

    @FindBy (xpath = "(//span[contains(text(), ' La contraseña debe tener entre 6 y 15 caracteres, al menos una letra y un número ')])")
    public WebElement passSeisQuinceCaraLetraNum;

    @FindBy (xpath = "(//*[contains(text(), ' Mis datos ')])")
    public WebElement retypePassIguales;

    @FindBy (xpath = "(//span[contains(text(), 'Debe de aceptar los términos de uso y la política de privacidad')])")
    public WebElement acceptTermsAndConditionsObligatorio;

    //



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

    public void scrollObjeto (WebElement aObject){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);",aObject);

    }

    public void fillingRegister (String aNombre,String aApellido, String aCI, String aSerie, String aFolioNumber
                                , String aCellphone, String aEmail, String aLocation, String aUsuario,
                                 String aPassword, String aPasswordRetyped) throws InterruptedException {

         //filling information
        rNombre.sendKeys(aNombre);
        rApellidos.sendKeys(aApellido);
        rDocument.sendKeys(aCI);
        scrollObjeto(rSerie);
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

 }

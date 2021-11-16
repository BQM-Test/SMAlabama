import com.google.errorprone.annotations.FormatMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlabamaCuentaPage extends BasePage{

    //para utilizar los @Find By...
    public AlabamaCuentaPage(WebDriver remoteDriver){
        driver = remoteDriver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    //Edicion de datos del usuario

    @FindBy(xpath = "(//p[contains(text(), 'Tilinalab1')])")
    public static WebElement cUsuarioLogueado;

    //Botones
    @FindBy(xpath = "(//button[contains(text(), 'Mi cuenta')])")
    public static WebElement clickMiCuenta;

    @FindBy(xpath = "(//span[contains(text(), 'Editar')])")
    public WebElement clickEditar;

    @FindBy(id = "btn-confirm")
    public WebElement clickCambiar;

    @FindBy(xpath = "(//a[contains(text(), 'Cancelar')])")
    public WebElement clickCancelar;

    //Titulo del form
    @FindBy(xpath = "(//span[contains(text(), 'Datos personales')])")
    public static WebElement cTituloForm;

    //Datos no editables del usuario para la validacion del usuario logueado
    @FindBy(xpath = "(//span[contains(text(), '16-05-1986')])")
    public WebElement cNacimientoUsuLogueado;

    @FindBy(xpath = "(//span[contains(text(), '9683847')])")
    public WebElement cDocumentoUsuLogueado;

    @FindBy(xpath = "(//span[contains(text(), ' TILINALAB1 ')])")
    public WebElement cUsuLogueado;

    //Inputs de datos editables
    @FindBy(id ="name")
    public WebElement cNombre;

    @FindBy(id ="lastname")
    public WebElement cApellido;

    @FindBy(id ="cellphone")
    public WebElement cCelular;

    @FindBy(id ="betBySMS")
    public WebElement cCheckSMS;

    @FindBy(id ="email")
    public WebElement cEmail;

    @FindBy(id ="selectedDepartment")
    public WebElement cDepartamento;

    @FindBy(id ="oldPassword")
    public WebElement cPswActual;

    @FindBy(id ="password")
    public WebElement cPswNueva;

    @FindBy(id ="passwordRetyped")
    public WebElement cPswRepetir;

    //Inputs de datos no editables
    @FindBy(id = "selectedDay")
    public WebElement cDiaNac;

    @FindBy(id = "selectedMonth")
    public WebElement cMesNac;

    @FindBy(id = "selectedYear")
    public WebElement cAnioNac;

    @FindBy(id = "document")
    public WebElement cDocumento;

    @FindBy(id = "username")
    public WebElement cUser;

    public static String usuarioLogueado(String usuPrueba){
        String mensaje="La cuenta no corresponde al usuario";
        String usu= cUsuarioLogueado.getAttribute("innerHTML");

        try{
            if(usu.equals(usuPrueba)){
                mensaje = "La cuenta corresponde al usuario logueado";
            }
        }catch(NoSuchElementException ex){
           mensaje= ex.getMessage();
        }

        return mensaje;
    }

    public static boolean ingresoMiCuenta(){
        cUsuarioLogueado.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(cTituloForm.getAttribute("innerHTML").equals("Datos personales")){
            return true;
        }
        return false;
    }
}

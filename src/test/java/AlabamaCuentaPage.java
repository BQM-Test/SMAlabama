import com.google.errorprone.annotations.FormatMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;



import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlabamaCuentaPage extends BasePage{

    /*
    *Instancia de clases
    * */
    private static AlabamaRegisterPage alabamaRegisterPage;
    private static MiLibreria miLibreria;

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
    public static WebElement cNacimientoUsuLogueado;

    @FindBy(xpath = "(//span[contains(text(), '9683847')])")
    public static WebElement cDocumentoUsuLogueado;

    @FindBy(xpath = "(//span[contains(text(), ' TILINALAB1 ')])")
    public static WebElement cUsuLogueado;

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

    //Inputs del menu lateral Mi cuenta
     @FindBy(xpath ="(//h2[contains(text(), 'Mi Cuenta')])")
     public static WebElement tituloMiCuenta;

   public static boolean menuMiCuenta(WebDriver driver, List<String> listaEsperada) throws AWTException, InterruptedException {
       boolean listaCorrecta= false;
       int aux=0;

       return listaCorrecta;
    }

    /*
    * Verifica que el nombre del usuario en la web
    * corresponda al user que se logueo (En este caso: Tilinalab1)
    * */
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

    /*
    * Al ingresar a "Mi cuenta" valida que los datos no editables del usuario
    * correspondan al usuario logueado (En este caso: Tilinalab1)
    * */
    public static boolean ingresoMiCuenta(WebDriver driver){
        boolean ret=false;
        cUsuarioLogueado.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elementos= armarListaWebElements(driver);
        if(elementos.size() == 4){

            System.out.println("Los datos no editables del usuario logueado son correctos");
            ret= true;

            for (WebElement e: elementos) {
                System.out.println(e.getAttribute("innerHTML"));
            }
        }
        else{
            int cantNoEncontrados= 4- elementos.size();
            //Else si el size < 4, hay elementos que no se encontraron
            System.out.println("Hay elementos "+cantNoEncontrados +" que no se encontraron");
        }

        return ret;
    }

    /*
    * Arma una lista con los elementos que encontro
    * */
    public static List<WebElement> armarListaWebElements(WebDriver driver){

        //Carga los elementos que encuentra a la lista
        boolean add;
        List<WebElement> elementos = new ArrayList<>();

        //Valida primero si encuentra el elemento con isElementPresent()
        if(miLibreria.isElementPresent(driver, By.xpath("(//span[contains(text(), 'Datos personales')])"))){
            cTituloForm.getAttribute("innerHTML");
            elementos.add(cTituloForm); //si es asi lo agrega
        }else{System.out.println("El titulo no es el esperado");}

        if(miLibreria.isElementPresent(driver, By.xpath("(//span[contains(text(), '16-05-1986')])"))){
            cNacimientoUsuLogueado.getAttribute("innerHTML");
            elementos.add(cNacimientoUsuLogueado);
        }else{System.out.println("La fecha de nacimiento no es la esperada");}

        if(miLibreria.isElementPresent(driver, By.xpath("(//span[contains(text(), '9683847')])"))){
            cDocumentoUsuLogueado.getAttribute("innerHTML");
            elementos.add(cDocumentoUsuLogueado);
        }else{System.out.println("El documento no es el esperado");}

        if(miLibreria.isElementPresent(driver, By.xpath("(//span[contains(text(), ' TILINALAB1 ')])"))){
            cUsuLogueado.getAttribute("innerHTML");
            elementos.add(cUsuLogueado);
        }else{System.out.println("El usuario no es el esperado");}

       return elementos;
    }

}

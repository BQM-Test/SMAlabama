import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;


import java.util.ArrayList;
import java.util.List;

public class AlabamaCuentaPage extends BasePage{

    /*
    *Instancia de clases
    * */
    private static AlabamaRegisterPage alabamaRegisterPage;
    private static MyLibrary myLibrary;

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
    public static WebElement clickEditar;

    @FindBy(id = "btn-confirm")
    public static WebElement clickCambiar;

    @FindBy(xpath = "(//a[contains(text(), 'Cancelar')])")
    public static WebElement clickCancelar;

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
    public static WebElement cName;

    @FindBy(id ="lastname")
    public static WebElement cLastName;

    @FindBy(id ="cellphone")
    public WebElement cCelular;

    @FindBy(id ="betBySMS")
    public WebElement cCheckSMS;

    @FindBy(id ="email")
    public WebElement cEmail;

    @FindBy(id ="selectedDepartment")
    public WebElement cDepartamento;

    @FindBy(id ="oldPassword")
    public static WebElement cPswActual;

    @FindBy(id ="password")
    public static WebElement cPswNueva;

    @FindBy(id ="passwordRetyped")
    public static WebElement cPswRepetir;

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
    public static WebElement cUser;

    //Inputs del menu lateral Mi cuenta
     @FindBy(xpath ="(//h2[contains(text(), 'Mi Cuenta')])")
     public static WebElement tituloMiCuenta;

     public static boolean editNameLastName(WebDriver driver, String newName, String newLastName, boolean change){
         driver.navigate().refresh();
         boolean ret= false;
         clickEditar.click();
         Actions builder= new Actions(driver);
         String messageObtained= "No devolvio ningun mensaje\n";

         if(myLibrary.isElementPresent(driver, By.id("name")) &&
            myLibrary.isElementPresent(driver, By.id("lastname"))){

             Actions serieOfActions= builder.moveToElement(cName).sendKeys(cName, newName).
                                     moveToElement(cLastName).sendKeys(cLastName, newLastName).moveToElement(cDocumentoUsuLogueado).click();
                    serieOfActions.perform();

             if(change){

             }
         }



         return ret;
     }

     /*
     * Una vez en el menu de cuentas del usuario,
     * edita la psw del mismo*/
     public static boolean editPsw(WebDriver driver, String currentPsw, String newPsw, String newPswRe, String messageExpected, boolean change){
         driver.navigate().refresh();
         Actions builder= new Actions(driver);
         boolean ret=false;
         String messageObtained= "No devolvio ningun mensaje\n";
         clickEditar.click();

         try {

             //Si existen los 3 campos necesarios para la edicion de la psw
             if (myLibrary.isElementPresent(driver, By.id("oldPassword")) &&
                     myLibrary.isElementPresent(driver, By.id("password")) &&
                     myLibrary.isElementPresent(driver, By.id("passwordRetyped"))) {

                 Actions seriesOfActions = builder.moveToElement(cPswActual).sendKeys(cPswActual, currentPsw)
                         .moveToElement(cPswNueva).sendKeys(cPswNueva, newPsw)
                         .moveToElement(cPswRepetir).sendKeys(cPswRepetir, newPswRe).moveToElement(cUser).click();
                 seriesOfActions.perform();

                 if (change) {
                     seriesOfActions.moveToElement(clickCambiar).click();
                     seriesOfActions.perform();
                     WebElement alert = driver.findElement(By.id("toast-container"));
                     messageObtained = alert.getText();
                     if (messageObtained.equals(messageExpected)) {
                         ret = true;
                     }

                 } else {

                     messageObtained = driver.findElement(By.xpath("//span[contains(@class, 'text-danger ng-star-inserted')]")).getText();
                     if (messageExpected.equals(messageObtained)) {
                         ret = true;
                     }

                     seriesOfActions.moveToElement(clickCancelar).click();
                     seriesOfActions.perform();
                 }

             }
         }catch(Exception e){
             e.getMessage();

         }finally {

             if(!ret) {
                 System.out.println("Fallo un test de editPsw(), mensajes que compare: \n");
                 System.out.println("Mensaje esperado --> " + messageExpected);
                 System.out.println("Mensaje obtenido --> " + messageObtained+ "Datos de prueba --> currentPsw= " + currentPsw + " newPsw= " + newPsw + " newPswRe= " + newPswRe + "change= "+change);
             }

             return ret;

         }

     }


    /*
    * Valida menu de mi cuenta
    * Recibe por parametro los Strings esperados en la ul del menu
    * */
    public static boolean menuMyAccount(WebDriver driver, List<String> expectedList) throws InterruptedException, AWTException {
        MyLibrary myLibrary = new MyLibrary(driver);
        boolean ret=true;
        int aux=0;

        //Obtengo el webElement lista
        WebElement element = driver.findElement(By.xpath("(//ul[@class='nav-pills w-100'])"));

        //Obtengo los web elements dentro de la lista
        List<WebElement> paragraphs= element.findElements(By.tagName("ion-label"));
        List<WebElement> paragraphsA= element.findElements(By.tagName("a"));

        //Unifico las listas
        paragraphs.addAll(paragraphsA);

        //Recorro la lista, si se encuentra un item que no corresponde con lo esperado, develve false
        for(WebElement e : paragraphs){
            myLibrary.scrollObjeto(e);
            if(!e.getText().contains(expectedList.get(aux)) ){
                System.out.println("Texto esperado --> "+expectedList.get(aux)+"\nTexto obtenido -->"+paragraphs.get(aux).getText());
                ret= false;
                break;
            }
            aux++;
            System.out.println(e.getText());
        }

        return ret;
   }

    /*
    * Verifica que el nombre del usuario en la web
    * corresponda al user que se logueo (En este caso: Tilinalab1)
    * */
    public static String loggedInUser(String testUsr){
        String message="La cuenta no corresponde al usuario";
        String usr= cUsuarioLogueado.getAttribute("innerHTML");

        try{
            if(usr.equals(testUsr)){
                message = "La cuenta corresponde al usuario logueado";
            }
        }catch(NoSuchElementException ex){
            message= ex.getMessage();
        }

        return message;
    }

    /*
    * Al ingresar a "Mi cuenta" valida que los datos no editables del usuario
    * correspondan al usuario logueado (En este caso: Tilinalab1)
    * */
    public static boolean loginAccount(WebDriver driver){
        boolean ret=false;
        cUsuarioLogueado.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements= createListOfWebElemets(driver);
        if(elements.size() == 4){

            System.out.println("Los datos no editables del usuario logueado son correctos");
            ret= true;

            for (WebElement e: elements) {
                System.out.println(e.getAttribute("innerHTML"));
            }
        }
        else{
            int quantityNotFound= 4- elements.size();
            //Else si el size < 4, hay elementos que no se encontraron
            System.out.println("Hay elementos "+quantityNotFound +" que no se encontraron");
        }

        return ret;
    }

    /*
    * Arma una lista con los elementos que encontro
    * */
    public static List<WebElement> createListOfWebElemets(WebDriver driver){

        //Carga los elementos que encuentra a la lista
        List<WebElement> elements = new ArrayList<>();

        //Valida primero si encuentra el elemento con isElementPresent()
        if(myLibrary.isElementPresent(driver, By.xpath("(//span[contains(text(), 'Datos personales')])"))){
            cTituloForm.getAttribute("innerHTML");
            elements.add(cTituloForm); //si es asi lo agrega
        }else{System.out.println("El titulo no es el esperado");}

        if(myLibrary.isElementPresent(driver, By.xpath("(//span[contains(text(), '16-05-1986')])"))){
            cNacimientoUsuLogueado.getAttribute("innerHTML");
            elements.add(cNacimientoUsuLogueado);
        }else{System.out.println("La fecha de nacimiento no es la esperada");}

        if(myLibrary.isElementPresent(driver, By.xpath("(//span[contains(text(), '9683847')])"))){
            cDocumentoUsuLogueado.getAttribute("innerHTML");
            elements.add(cDocumentoUsuLogueado);
        }else{System.out.println("El documento no es el esperado");}

        if(myLibrary.isElementPresent(driver, By.xpath("(//span[contains(text(), ' TILINALAB1 ')])"))){
            cUsuLogueado.getAttribute("innerHTML");
            elements.add(cUsuLogueado);
        }else{System.out.println("El usuario no es el esperado");}

       return elements;
    }

}

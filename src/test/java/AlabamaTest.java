import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.net.URL;



public class AlabamaTest  extends BasePage {

    @BeforeMethod
    public void ingresarAlabamaSM() {
        driver.get("https://supermatch-alabama-test.bqmtest.com.uy");

    }

    @Test
    public void validarTitulo() {

        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        String tituloSMAlabama = alabamaHomePage.validarTituloSMAlabama();

        System.out.println(">---" + tituloSMAlabama);

        Assert.assertEquals(tituloSMAlabama, "Supermatch2");
    }

    @Test
    public void loginSMAlabama() {

        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        alabamaHomePage.clickBtnIngresar();
        alabamaHomePage.fillloginPorUpIngresar();
        alabamaHomePage.clickBtnpopUpIngresar();
    }

    @Test

    public void loginSMAlabamaNoRegisterUsr(){
        //ingreso al azar un nombre y pass valido que no este registrado
        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        alabamaHomePage.clickBtnIngresar();
        alabamaHomePage.fillingUsrNoRegisterIngresar();
        alabamaHomePage.clickBtnpopUpIngresar();
        String errorTextUsrPassIncorrect = alabamaHomePage.errorTextUsrPassIncorrect();
        System.out.println(errorTextUsrPassIncorrect);
        //Assert.assertEquals(errorTextUsrPassIncorrect, "Usuario y/o clave incorrectos" );
        Assert.assertEquals(errorTextUsrPassIncorrect, "Error al procesar su solicitud" );

    }

    @Test
    public void irApuTodos() throws InterruptedException {
        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        AlabamaApuestasPage alabamaApuestasPage = new AlabamaApuestasPage(driver);
        ArrayList<String> deportes = new ArrayList<String>();
        WebElement  divListDeportes = alabamaHomePage.divListDeportes();
        List<WebElement> listDeportes = divListDeportes.findElements(By.tagName("a"));
        System.out.println(">---Cantidad de deportes:>--- " + listDeportes.size());

        for (WebElement dep : listDeportes) {

            System.out.println(">>>>" + dep.getAttribute("innerHTML"));
        }

        if(listDeportes.size()>1){
            System.out.println(">---Cantidad de deportes Activos:>--- " + listDeportes.size());

        }else{
            System.out.println(">---Solo esta 'Todos', es posible que no haya eventos o la pagina no este respondiendo--- " + listDeportes.size());
        }


        for (WebElement depActivos : listDeportes) {
            System.out.println(">---Deporte encontrado: "+depActivos.getAttribute("innerHTML"));
            deportes.add(depActivos.getAttribute("innerHTML"));
        }
        for (WebElement depActivos : listDeportes) {
            if (depActivos.getAttribute("innerHTML").equals("Todos")) {
                Assert.assertEquals(depActivos.getAttribute("innerHTML"), "Todos");
                System.out.println(">---Encontre titulo Todos y hago click para ir a todas las apuestas");
                WebElement clickTodos = depActivos;
                WebElement apuestasHome = alabamaHomePage.apuestaHome();
                Actions actionProvider = new Actions(driver);
                // Realiza la acción move hacia el elemento
                actionProvider.moveToElement(apuestasHome).build().perform();
                clickTodos.click();

                break;
            }else {
                System.out.println(">--- NO Encontre titulo todos");
            }
        }
        Thread.sleep(3000);

        String urlApuestasTodos=alabamaApuestasPage.getCurrentUrl();
        System.out.println("url de prematch todas las apuestas: >>>--" + urlApuestasTodos);
        Assert.assertTrue(urlApuestasTodos.contains("prematch"));
        Assert.assertTrue(urlApuestasTodos.contains("all"));


        //solo para comprobar que se cargaron elementos en la lista
        if(deportes.size() != 0 ){
            for (String depo : deportes) {
                System.out.println("Deporte>---"+depo);

            }
        }else{
            System.out.println("No hay elementos en la lista deportes");
        }

    }

    @Test
    public void obtengoLisBtnApuestas() throws InterruptedException {
        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        AlabamaApuestasPage alabamaApuestasPage =   new AlabamaApuestasPage(driver);

        Thread.sleep(10000);

        List<WebElement> listApu = alabamaHomePage.listBtnApuestas();
        Assert.assertEquals(alabamaHomePage.apuestasHome(), "Apuestas");
        Assert.assertEquals(alabamaHomePage.virtualesHome(), "Virtuales");
        Assert.assertEquals(alabamaHomePage.resultadosHome(), "Resultados");

        System.out.println(listApu.size());
        System.out.println(alabamaHomePage.apuestasHome());

        for (WebElement apu : listApu) {

            System.out.println(">>>" + apu.getAttribute("innerHTML"));
                }

        //llamo metodo para ir a apuestas-todos
        irApuTodos();

        Thread.sleep(3000);
        List<WebElement> getListGana= alabamaApuestasPage.getListGana();

        System.out.println(getListGana.size());

        /* for (WebElement listDepo: getListGana) {

       //WebElement textListUla = ul.findElement(By.tagName("a"));
       //WebElement textListUlaSpan = textListUla.findElement(By.tagName("span"));
       //System.out.println("Span text dentro de UL lista de deportes encontrada en la Web>--- " +ul.getText());

           for (int i = 1; i < deportes.size() ; i++) {

                if( listDepo.getAttribute("innerHTML").equals(deportes.get(i))){
                    System.out.println("En la lista de deportes creada esta>-- " + deportes.get(i) +" coincide con la UL lista de deportes encontrada en la Web>--- " +listDepo.getAttribute("innerHTML"));
                }
            }

        }*/
        //Busco encontrar todos los eventos de un deporte que selecciono para poder apostar y capto sus nombres
        List<WebElement> getListEventos = alabamaApuestasPage.getListEquipos();
        System.out.println(getListEventos.size());
       //Guardo los objetos donde dice gana y empate para luego poder hacer click
       // ArrayList<WebElement> eventosGana = new ArrayList<WebElement>();
        ArrayList<WebElement> eventosGanaNombre = new ArrayList<WebElement>();
       // ArrayList<WebElement> eventosEmpate = new ArrayList<WebElement>();
       // Boolean encontreGana = false;
        // String textoGana = "Gana";
        //if(getListEventos.size()==getListGana.size()){


        //ver de guardar si es visible
        for (int i = 0; i <getListEventos.size() ; i++) {
            WebElement equipo = getListEventos.get(i);
            if(equipo.isDisplayed()){
            eventosGanaNombre.add(equipo);
            /*if (encontreGana){
                eventosGanaNombre.add(equipo);
            }
            if(equipo.getAttribute("innerHTML").equals(textoGana)) {
                eventosGana.add(equipo);
                encontreGana=true;
            }else{
                encontreGana=false;
            }*/
                //spanDepo.click();
                //break;
            }
            }
       // }
            for (WebElement cuadros: eventosGanaNombre) {
                System.out.println("Los equipos encontrados que se visualizan en primera pantalla>>>>--- " + cuadros.getAttribute("innerHTML"));

            }
            // } (WebElement spanDepo: getListSpanEventos) {


        }
    //lista de eventos en todos (solo los que se visualizan primero


    @Test

        public void apostarPrematch() throws InterruptedException {

            AlabamaApuestasPage alabamaApuestasPage =   new AlabamaApuestasPage(driver);
            loginSMAlabama();
            Thread.sleep(10000);
            irApuTodos();
            List<WebElement> listEventosTodos=  alabamaApuestasPage.listEventosTodos();
            List<WebElement> listBtnApostar =  alabamaApuestasPage.btnApostar();
            if(listEventosTodos.size()>0) {
                listEventosTodos.get(1).click();//esta lista tiene los eventos que se ven en pantalla, no se visualizan los que se necesita hacer scroll
                for (WebElement findBtnEnable: listBtnApostar){
                    if(findBtnEnable.isEnabled()){
                        findBtnEnable.click();
                        break;
                    }
                }
            }else{
                System.out.println("NO hay eventos para apostar");
            }


        }

    @Test
        public void fillRegister() throws InterruptedException {

        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        AlabamaApuestasPage alabamaApuestasPage =   new AlabamaApuestasPage(driver);
        AlabamaRegisterPage alabamaRegisterPage =   new AlabamaRegisterPage(driver);

        alabamaHomePage.clickBtnRegistrarse();

        Thread.sleep(3000);

        String urlRegister=alabamaRegisterPage.getCurrentUrl();
        System.out.println("url de register >>>--" + urlRegister);//https://supermatch-alabama-test.bqmtest.com.uy/user/register
        Assert.assertTrue(urlRegister.contains("user"));
        Assert.assertTrue(urlRegister.contains("register"));

        System.out.println("Title fill information>>>>" + alabamaRegisterPage.getH1TitleRegister());

        Date myDate = new Date();
        String myDateFormat = new SimpleDateFormat("dd-MM-yyyy").format(myDate);

        Date myDateMenos18 = alabamaRegisterPage.sumarRestarYearFecha(myDate, -18);
        System.out.println(myDateMenos18);

        String dateDay = new SimpleDateFormat("dd").format(myDate);
        String dateMounth = new SimpleDateFormat("MM").format(myDate);
        String dateYear = new SimpleDateFormat("YYYY").format(myDateMenos18);
        System.out.println(dateDay);
        System.out.println(dateMounth);
        System.out.println(dateYear);

        Thread.sleep(3000);

        //
        Select selectDay = new Select(alabamaRegisterPage.rSelectDay);
        selectDay.selectByValue(dateDay);
       // Select selectDay = new Select(alabamaRegisterPage.rSelectDay);

        Select selectMounth = new Select(alabamaRegisterPage.rSelectedMonth);
        Select selectYear = new Select(alabamaRegisterPage.rSelectedYear);
        selectMounth.selectByValue(dateMounth);
        selectYear.selectByVisibleText(dateYear);

        System.out.println(alabamaRegisterPage.sumarRestarDiasFecha(myDate, -1));
        System.out.println(myDateFormat);

        alabamaRegisterPage.fillingRegister("pop", "p", "1", "1",
                "T","123456789", "j@j", "Montevideo",
                "pepe", "12345", "123456");

        alabamaRegisterPage.rClickRegistrarme();
        alabamaRegisterPage.scrollObjeto(alabamaRegisterPage.rNombre);
        Thread.sleep(2000);
        alabamaRegisterPage.rNombre.click();
        String name = alabamaRegisterPage.rNombre.getAttribute("value");
        System.out.println(name);
        if (name.length()<2){

            System.out.println("El nombre tiene menos de 1 letra");
            System.out.println("msj de error es>>>>" + alabamaRegisterPage.msjErrorNombre());
        }else {
            System.out.println("tiene mas de 2 letras");
        }

    }

    @Test
    public void mostarLinksHomeTest(){

        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        List<WebElement> linkList = alabamaHomePage.getLink();
        System.out.println(linkList.size());

        for (WebElement link : linkList) {
            System.out.println(">---"+link.getAttribute("href"));
        }

        String url = "";
        List<String> brokenLinks= new ArrayList<String>();
        List<String> okLinks= new ArrayList<String>();

        HttpURLConnection httpConnection= null;
        int responseCode=200;
        Iterator<WebElement> it = linkList.iterator();

        while (it.hasNext()){
            url=it.next().getAttribute("href");
            if(url==null || url.isEmpty()){
                System.out.println(url + "url no tiene configurado o esta vacia");
                continue;
            }
            try{
                httpConnection= (HttpURLConnection)(new URL(url).openConnection());
                httpConnection.setRequestMethod("HEAD");
                httpConnection.connect();
                responseCode = httpConnection.getResponseCode();

                if(responseCode>400){
                    System.out.println("Error link---" +url );
                    brokenLinks.add(url);
                }else{
                    System.out.println("Ok Link---"+url);
                    okLinks.add(url);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("ok Links= "+okLinks.size() );
        System.out.println("broken Links= "+brokenLinks.size() );

        if(brokenLinks.size()>0){
            System.out.println("ERROR BROKEN LINKS");
            for (int i=0; i< brokenLinks.size(); i++) {
                System.out.println(">---"+ brokenLinks.get(i));

            }

        }

    }


    @Test //(dependsOnMethods = {"ingresarPageApuestas"})
    public void mostarLinksApuestasAllTest() throws InterruptedException {

        irApuTodos();
        AlabamaApuestasPage alabamaApuestasPage = new AlabamaApuestasPage(driver);
        List<WebElement> linkList = alabamaApuestasPage.getLink();
        System.out.println(linkList.size());

        for (WebElement link : linkList) {
            System.out.println(">---"+link.getAttribute("href"));
        }

        String url = "";
        List<String> brokenLinks= new ArrayList<String>();
        List<String> okLinks= new ArrayList<String>();

        HttpURLConnection httpConnection= null;
        int responseCode=200;
        Iterator<WebElement> it = linkList.iterator();

        while (it.hasNext()){
            url=it.next().getAttribute("href");
            if(url==null || url.isEmpty()){
                System.out.println(url + "url no tiene configurado o esta vacia");
                continue;
            }
            try{
                httpConnection= (HttpURLConnection)(new URL(url).openConnection());
                httpConnection.setRequestMethod("HEAD");
                httpConnection.connect();
                responseCode = httpConnection.getResponseCode();

                if(responseCode>400){
                    System.out.println("Error link---" +url );
                    brokenLinks.add(url);
                }else{
                    System.out.println("Ok Link---"+url);
                    okLinks.add(url);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("ok Links= "+okLinks.size() );
        System.out.println("broken Links= "+brokenLinks.size() );

        if(brokenLinks.size()>0){
            System.out.println("ERROR BROKEN LINKS");
            for (int i=0; i< brokenLinks.size(); i++) {
                System.out.println(">---"+ brokenLinks.get(i));

            }

        }

    }

      /*@AfterTest
    public void tearDown(){
        System.out.println("Se ha terminado de ejecutar un test...");
        driver.close();
    }*/


        /*for (WebElement ul: getListUl) {

            for (int i = 1; i < deportes.size() ; i++) {

            if( ul.getText() == deportes.get(i)){
                System.out.println("En la lista de deportes creada esta>-- " + deportes.get(i) +" coincide con la UL lista de deportes encontrada en la Web>--- " +ul.getText());
            }
            }

        }*/
/*
        Thread.sleep(5000);

        //String titlePageApuestas
        System.out.println(alabamaApuestasPage.getTitle());
        Assert.assertEquals(alabamaApuestasPage.getCurrentUrl(), "https://sm-pwa-uat.alabamasolutions.com/prematch/betting/all");
        Assert.assertTrue(alabamaApuestasPage.getCurrentUrl().contains("prematch"));




        //cantidad de deportes
       // List<WebElement> listDeportes = alabamaApuestasPage.getListDeportes();

        System.out.println(listDeportes.size());
        boolean deporte = false;
        for (WebElement dep : listDeportes) {
            //System.out.println(">---"+dep.getText());
            if (dep.getText().equals("Fútbol")) {
                deporte = true;
                System.out.println(">---Encontre deporte Fútbol");
                dep.click();
                List<WebElement> liTorneosActivos = dep.findElements(By.tagName("li"));//driver.findElements(By.tagName("span"));
                //Assert.assertTrue(ListTorneosActivos.isEmpty(),"Esta vacia");
                for (WebElement lilistTorneos : liTorneosActivos){
                    List<WebElement> spanlistTorneoDep = lilistTorneos.findElements(By.tagName("span"));
                    for (WebElement spanlistTorneosDep : spanlistTorneoDep){
                        System.out.println(">TORNEO---spanlistTorneosDep"+"spanlistTorneosDep");

                    }



                }

            } else {

                System.out.println(">--- NO Encontre deporte");
            }


    }
      @Test

    public void ingresarPageApuestas() throws InterruptedException {

        AlabamaHomePage alabamaHomePage = new AlabamaHomePage(driver);
        //alabamaHomePage.clickBtnApuestas();
        List<WebElement> listApu = alabamaHomePage.listBtnApuestas();
        System.out.println(listApu.size());

        for (WebElement apu : listApu) {
            System.out.println(">---"+apu.getText());

        }

        WebElement  divListDeportes = alabamaHomePage.divListDeportes();

        List<WebElement> listDeportes = divListDeportes.findElements(By.id("dropdownBasic"));

        System.out.println(">---Cantidad de deportes Activos:>--- " + listDeportes.size());

        for (WebElement depActivos : listDeportes) {
            System.out.println(">---Deporte encontrado: "+depActivos.getText());

        }
        for (WebElement depActivos : listDeportes) {
            if (depActivos.getText().equals("Todos")) {
                Assert.assertEquals(depActivos.getText(), "Todos");
                System.out.println(">---Encontre titulo Todos y hago click para ir a todas las apuestas");
                depActivos.click();
                break;
            }else {
                System.out.println(">--- NO Encontre titulo todos");
            }
        }
        Thread.sleep(3000);


    }
*/






}



package com.uniovi.notaneitor;

import com.uniovi.notaneitor.pageobjects.*;
import com.uniovi.notaneitor.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotaneitorApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    static String Geckodriver = "C:\\Users\\uo295286\\Downloads\\sesion06\\sesion06\\PL-SDI-Sesión6-material\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }


    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }

    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    @BeforeAll
    static public void begin() {
    }

    @AfterAll
    static public void end() {
        driver.quit();
    }


    @Test
    @Order(1)
    void PR01A() {
        PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
    }

    @Test
    @Order(2)
    void PR01B() {
        List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver, PO_Properties.getSPANISH());
        Assertions.assertEquals(
                welcomeMessageElement.get(0).getText(),
                PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH())
        );
    }


    @Test
    @Order(3)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }

    // PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
    @Test
    @Order(4)
    public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }


    @Test
    @Order(5)
    public void PR04() {
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(),
                PO_Properties.getENGLISH());
    }


    @Test
    @Order(6)
    public void PR05() {
        // Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

        // Rellenamos el formulario
        PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");

        // Comprobamos que entramos en la sección privada y nos muestra el texto a buscar
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    @Test
    @Order(7)
    public void PR06A() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");

        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
        // Comprobamos el error de DNI repetido.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate", PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    // PR06B. Prueba del formulario de registro. Nombre corto.
// Propiedad: Error.signup.dni.length
    @Test
    @Order(8)
    public void PR06B() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");

        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
        // Comprobamos el error de nombre corto.
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length", PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    @Test
    @Order(9)
    public void PR07() {
        // Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

        // Rellenamos el formulario.
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");

        // Comprobamos que entramos en la página privada de Alumno.
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR08() {
        // Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

        // Rellenamos el formulario.
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");

        // Comprobamos que entramos en la página privada de Alumno.
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }


    @Test
    @Order(11)
    public void PR09() {
        // Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

        // Rellenamos el formulario.
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        // Comprobamos que entramos en la página privada de Alumno.
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(12)
    public void PR010() {
        PO_HomeView.login(driver, "99999990A", "123456");

        PO_HomeView.checkIdentificate(driver);
    }


    @Test
    @Order(13)
    public void PR011() {
        PO_HomeView.login(driver, "99999990A", "123456");

        WebElement logout = driver.findElement(By.xpath("/html/body/nav/div/ul[2]/li[2]/a"));
        logout.click();

        PO_HomeView.checkIdentificate(driver);
    }

    // PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de estudiante
    @Test
    @Order(14)
    public void PR12() {

        PO_HomeView.login(driver, "99999990A", "123456");

        // Comprobamos que entramos en la página privada de Alumno.
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        // Contamos el número de filas de notas.
        List<WebElement> marksList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr", PO_View.getTimeout());
        Assertions.assertEquals(2, marksList.size());

        // Ahora nos desconectamos y comprobamos que aparece el menú de registro.
        PO_PrivateView.logut(driver);
    }

    @Test
    @Order(15)
    public void PR13() {
        PO_HomeView.login(driver, "99999990A", "123456");

        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);

        // Contamos las notas
        By enlace = By.xpath("/html/body/div/div/table/tbody/tr[2]/td[4]/a");
        driver.findElement(enlace).click();

        // Esperamos por la ventana de detalle
        checkText = "Detalles de la nota";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());


        // Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        PO_PrivateView.logut(driver);
    }





    @Test
    @Order(16)
    public void PR14() {



        PO_HomeView.login(driver, "99999993D", "123456");

        // Comprobamos que entramos en la página privada del Profesor.
        PO_View.checkElementBy(driver, "text", "99999993D");

        // Pinchamos en la opción de menú de Notas:
        // li[contains(@id, 'marks-menu')]/a
        // List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
        PO_PrivateView.click(driver,"free","//*[@id='myNavbar']/ul[1]/li[2]");


        // Esperamos a que aparezca la opción de añadir nota:
        // a[contains(@href, 'mark/add')]

        PO_PrivateView.click(driver,"free","//a[contains(@href, 'mark/add')]");

        // Ahora vamos a rellenar la nota con más de 20 caracteres.
        // option[contains(@value, '4')]
        String checkText = "Nota sistemas distribuidos";
        PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");

        // Esperamos a que se muestren los enlaces de paginación de la lista de notas.

        PO_PrivateView.click(driver,"free","//a[contains(@class, 'page-link')]",4);

        // Comprobamos que aparece la nota en la página.
        List<WebElement> elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());

        PO_PrivateView.logut(driver);
    }

    @Test
    @Order(17)
    public void PR15() {
        PO_HomeView.login(driver, "99999993D", "123456");


        PO_View.checkElementBy(driver, "text", "99999993D");
        // Pinchamos en la opción de menú de Notas:
        // li[contains(@id, 'marks-menu')]/a
        // List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
        PO_PrivateView.click(driver,"free","//*[@id='myNavbar']/ul[1]/li[2]");

        // Pinchamos en la opción de lista de notas.
        PO_PrivateView.click(driver,"free","//a[contains(@href, 'mark/list')]");

        // Esperamos a que se muestren los enlaces de paginación de la lista de notas.
        // Nos vamos a la última página.
        PO_PrivateView.click(driver,"free","//a[contains(@class, 'page-link')]",4);

        // Esperamos a que aparezca la Nueva nota en la última página.
        // Y pinchamos en el enlace de borrado de la Nota "Nota sistemas distribuidos".
        PO_PrivateView.click(driver,"free","/html/body/div/div/table/tbody/tr[2]/td[6]/a");

        // Volvemos a la última página.
        PO_PrivateView.click(driver,"free","//a[contains(@class, 'page-link')]",4);
        // Y esperamos a que NO aparezca la nota "Nota sistemas distribuidos".
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Nota sistemas distribuidos", PO_View.getTimeout());


        PO_PrivateView.logut(driver);
    }






}

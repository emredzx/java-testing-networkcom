import io.github.bonigarcia.wdm.WebDriverManager;
import org.emredzx.HomePage;
import org.emredzx.Log;
import org.emredzx.LoginPage;
import org.emredzx.SearchPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(BaseTestLog.class)
public class BaseTest {

    Log log = new Log();
    WebDriver webDriver;
    HomePage homePage;
    SearchPage searchPage;
    LoginPage loginPage;

    @BeforeAll
    public void setupAll(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        homePage = new HomePage(webDriver);
        searchPage = new SearchPage(webDriver);
        loginPage = new LoginPage(webDriver);
        webDriver.get("https://www.network.com.tr");
        webDriver.manage().window().maximize();
    }

    @AfterAll
    public void teardown(){
        webDriver.quit();
    }

    @Test
    @Order(1)
    public void validateHomePage() {
        log.info("Validating the correct URL");
        Assertions.assertTrue(homePage.isOnPage());
    }

    @Test
    @Order(2)
    public void searchProduct() {
        log.info("Searching 'ceket' entry");
        homePage.search("ceket");
    }

    @Test
    @Order(3)
    public void scrollToElement() {
        log.info("Scrolling until the button");
        searchPage.scroll();
    }

    @Test
    @Order(4)
    public void validateSearchPage() {
        log.info("Checking the correct URL");
        Assertions.assertTrue(searchPage.isOnPage());
    }

    @Test
    @Order(5)
    public void hoverAndChoose() {
        log.info("Hovering discounted item, Choosing the size and Changing to the cart");
        searchPage.productElement().choose();
    }

    @Test
    @Order(6)
    public void validateCheckout() {
        log.info("Checking price and size");
        Assertions.assertTrue(searchPage.productElement().validateChart());
    }

    @Test
    @Order(7)
    public void validatePrice() {
        log.info("Checking old and current price");
        Assertions.assertTrue(searchPage.productElement().validatePrice());
    }

    @Test
    @Order(8)
    public void continueCart() {
        log.info("Clicking continue button");
        searchPage.productElement().continueCart();
    }

    @Test
    @Order(9)
    public void writeLoginData() {
        log.info("Reading data from .csv file and Placing to the right boxes");
        loginPage.writeData();
    }

    @Test
    @Order(10)
    public void checkLoginButton() {
        log.info("Checking login button");
        Assertions.assertTrue(loginPage.validateLoginButton());
    }

    @Test
    @Order(11)
    public void returnHomePage() {
        log.info("Clicking Network logo and Returning to the homepage");
        loginPage.returnHomePage();
    }

    @Test
    @Order(12)
    public void showCart() {
        log.info("Showing the cart");
        homePage.showCart();
    }

    @Test
    @Order(13)
    public void dropCart() {
        log.info("Dropping items from cart and Validating the empty cart");
        Assertions.assertTrue(homePage.validateDropCart());
    }
}

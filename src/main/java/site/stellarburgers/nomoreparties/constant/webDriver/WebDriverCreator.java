//package site.stellarburgers.nomoreparties.constant.webDriver;//package driver;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;


//public class WebDriverCreator {

//  public static WebDriver driver;
//  private final boolean openYandexBrowser;

//  public WebDriverCreator(boolean openYandexBrowser) {
//     this.openYandexBrowser = openYandexBrowser;
//  }

// public static WebDriver createWebDriver() {
//     driver = getWebDriver(false);
//      return new ChromeDriver();
//  }

// static ChromeDriver getWebDriver(boolean openYandexBrowser) {
//  if (openYandexBrowser) {
//       System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\BROWSER_DRIVERS\\yandexdriver.exe");
//    }
//  else {
//       return new ChromeDriver();  // System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\BROWSER_DRIVERS\\chromedriver.exe");
//   }
//    return new ChromeDriver();
//  }
//}

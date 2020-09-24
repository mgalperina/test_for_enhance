package PageObjectModel;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

    public class Page_Object {
        protected WebDriver driver;

        public Page_Object(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
    }


package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SearchFunction {
    static WebDriver driver;

    @BeforeEach
//    public void Setup(String linkUrl) {
//
//    }

    public void SearchKeywords(String linkUrl) throws Exception {
        String pathWebDriver = "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathWebDriver);
        driver = new ChromeDriver();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        ops.addArguments("start-maximized");
        driver.manage().window().maximize();
        driver.navigate().to(linkUrl);

        String siteCode = driver.getCurrentUrl().substring(linkUrl.lastIndexOf("/") + 5);

        ArrayList<String> arrayKeywords = new ArrayList<String>();
        arrayKeywords.addAll(Arrays.asList("galaxy z flip5","phone"));
        //arrayKeywords.addAll(Arrays.asList("Galaxy E1","Galaxy E2","Galaxy E3","E1","E2","E3","Galaxy S series","new S series","2024 S series","S Pen","Galaxy S","new Galaxy S","2024 galaxy S","Galaxy AI","AI","Eureka"));

        for (int i = 0; i < arrayKeywords.size(); i++) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            Boolean isUnpackVideoAppear = driver.findElements(By.id("takeover202401")).size() > 0;
            if (isUnpackVideoAppear) {
                WebElement clickOnCloseUnpackVideoButton = driver.findElement(By.className("takeover202401__close-btn"));
                clickOnCloseUnpackVideoButton.click();
            };

            String locatorAcceptCookies = "//*[@id=\"header\"]/div[2]/div/div/div[2]/a";

            Boolean isAcceptCookiesAppear = driver.findElements(By.xpath(locatorAcceptCookies)).size() > 0;
            if (isAcceptCookiesAppear) {
                WebElement clickOnAcceptCookies = driver.findElement(By.xpath(locatorAcceptCookies));
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                clickOnAcceptCookies.click();
            };

            //Click nút search
            String locatorSearchButton = "//*[@id=\"component-id\"]/div[1]/div[6]/div[1]/button";
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement clickOnSearch = driver.findElement(By.xpath(locatorSearchButton));
            clickOnSearch.click();
            //Thread.sleep(2000);

            //Nhập keywords
            String locatorInputKeyword = "gnb-search-keyword";
            WebElement searchKeywordInput = driver.findElement(By.id(locatorInputKeyword));
            searchKeywordInput.sendKeys(arrayKeywords.get(i));
            //Enter kết quả search
            searchKeywordInput.sendKeys(Keys.RETURN);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Thread.sleep(2000);

            //Click sang tab Tất Cả
            String locatorSwitchToAll = "//*[@id=\"result-list-container\"]/div[1]/div/ul/li[1]/a";
            Boolean isShowingTheSearchResults = driver.findElements(By.xpath(locatorSwitchToAll)).size() > 0;
            if (isShowingTheSearchResults) {
                WebElement clickAll = driver.findElement(By.xpath(locatorSwitchToAll));
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click()", clickAll);
                Thread.sleep(1000);
            };

//           String locatorClassPopupText = "/html/body/div/div/div/div/div/div/div/div";
//           String locatorButtonClosePopupText = "/html/body/div/div/div/div/div/div/button";
//           Boolean isPopUpTextAppear = driver.findElements(By.xpath(locatorClassPopupText)).size() > 0;
//           if(isPopUpTextAppear) {
//               WebElement clickOnClosePopupTextButton = driver.findElement(By.xpath(locatorButtonClosePopupText));
//               driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//               clickOnClosePopupTextButton.click();
//           };

            String locatorBestMatchSection = "//*[@id=\"all\"]/div[1]/div[1]/section/a";

            Boolean isShowingBestMatch = driver.findElements(By.xpath(locatorBestMatchSection)).size() > 0;
            if (isShowingBestMatch) {
                WebElement sectionBestMatch = driver.findElement(By.xpath(locatorBestMatchSection));
                String redirectedUrl= sectionBestMatch.getAttribute("href");
                System.out.println(siteCode.toUpperCase() + "Yes/ "+"Keyword: "+ arrayKeywords.get(i).toString() + "/ The redirected URL: "+ redirectedUrl);
                Thread.sleep(1000);
            } else System.out.println(siteCode.toUpperCase() + "No/ "+"Keyword: "+ arrayKeywords.get(i).toString()+ "/ The redirected URL: N/A");;

            //Chụp ảnh
            TakeScreenshot takeScreenshot = new TakeScreenshot();
            Thread.sleep(1000);
            String pathNameMO = "D:\\Auto\\Screenshot\\" + siteCode.toUpperCase() + siteCode.substring(0, 2).toUpperCase() + "_" + arrayKeywords.get(i).toString() + "(MO).png";
            String pathNamePC = "D:\\Auto\\Screenshot\\" + siteCode.toUpperCase() + siteCode.substring(0, 2).toUpperCase() + "_" + arrayKeywords.get(i).toString() + "(PC).png";
            takeScreenshot.takeSnapShot(this.driver, pathNamePC);

            driver.manage().window().setSize(new Dimension(390, 1200));
            //to perform Scroll on application using Selenium
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,200)", "");
            Thread.sleep(1000);
            takeScreenshot.takeSnapShot(this.driver, pathNameMO);
            driver.manage().window().maximize();
            js.executeScript("window.scrollBy(0,-200)", "");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

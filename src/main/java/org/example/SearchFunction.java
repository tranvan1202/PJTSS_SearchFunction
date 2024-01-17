package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import org.openqa.selenium.interactions.Actions;


public class SearchFunction {
    static WebDriver driver;

    @BeforeEach
//    public void Setup(String linkUrl) {
//
//    }

    public void SearchKeywords() throws Exception {
        String pathWebDriver = "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathWebDriver);
        driver = new ChromeDriver();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        ops.addArguments("start-maximized");

        String urlVN = "https://www.samsung.com/vn/search/?searchvalue=";
        String urlSG = "https://www.samsung.com/sg/search/?searchvalue=";
        String urlID = "https://www.samsung.com/id/search/?searchvalue=";
        String urlTH = "https://www.samsung.com/th/search/?searchvalue=";
        String urlPH = "https://www.samsung.com/ph/search/?searchvalue=";
        String urlMY = "https://www.samsung.com/my/search/?searchvalue=";
        String urlNZ = "https://www.samsung.com/nz/search/?searchvalue=";
        ArrayList arrayURLs = new ArrayList<String>();
        arrayURLs.addAll(Arrays.asList(urlSG));
        //arrayURLs.addAll(Arrays.asList(urlVN, urlID, urlSG, urlTH, urlNZ, urlPH, urlMY));
        ArrayList arrayKeywords = new ArrayList<String>();
        arrayKeywords.addAll(Arrays.asList("galaxy z flip5", "phone"));

        //arrayKeywords.addAll(Arrays.asList("Galaxy E1","Galaxy E2","Galaxy E3","E1","E2","E3","Galaxy S series","new S series","2024 S series","S Pen","Galaxy S","new Galaxy S","2024 galaxy S","Galaxy AI","AI","Eureka"));

        System.out.println("Sub" + ",PC (Y/N)" + ",MO (Y/N)" + ",Keyword" + ",Redirected URL");
        for (int x = 0; x < arrayURLs.size(); x++) {
            for (int i = 0; i < arrayKeywords.size(); i++) {
                //Giữ cửa sổ hiện tại
                String winHandleBefore = driver.getWindowHandle();
                driver.manage().window().maximize();
                //driver.navigate().to(arrayURLs.get(x)+arrayKeywords.get(i).toString());

                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("window.open()");
                ArrayList<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
                driver.switchTo().window(browserTabs.get(1));
                driver.get(arrayURLs.get(x) + arrayKeywords.get(i).toString());

                String siteCode = (driver.getCurrentUrl().substring(arrayURLs.get(x).toString().indexOf(".com/") + 5)).substring(0, 2);
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                Boolean isUnpackVideoAppear = driver.findElements(By.id("takeover202401")).size() > 0;
                if (isUnpackVideoAppear) {
                    WebElement clickOnCloseUnpackVideoButton = driver.findElement(By.className("takeover202401__close-btn"));
                    clickOnCloseUnpackVideoButton.click();
                };

                String locatorAcceptCookies = "//*[@id=\"header\"]/div[2]/div/div/div[2]/a";

                Boolean isAcceptCookiesAppear = driver.findElements(By.xpath(locatorAcceptCookies)).size() > 0;
                if (isAcceptCookiesAppear) {
                    WebElement clickOnAcceptCookies = driver.findElement(By.xpath(locatorAcceptCookies));
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                    clickOnAcceptCookies.click();
                };

//            //Click nút search (từ Homepage)
//            String locatorSearchButton = "//*[@id=\"component-id\"]/div[1]/div[6]/div[1]/button";
//            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//            WebElement clickOnSearch = driver.findElement(By.xpath(locatorSearchButton));
//            clickOnSearch.click();
//            //Thread.sleep(2000);

//            //Nhập keywords (từ Homepage)
//            String locatorInputKeyword = "gnb-search-keyword";
//            WebElement searchKeywordInput = driver.findElement(By.id(locatorInputKeyword));
//            searchKeywordInput.sendKeys(arrayKeywords.get(i));
//            //Enter kết quả search
//            searchKeywordInput.sendKeys(Keys.RETURN);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//            //Thread.sleep(2000);

                //Click sang tab Tất Cả
                String locatorSwitchToAll = "//*[@id=\"result-list-container\"]/div[1]/div/ul/li[1]/a";
                Boolean isShowingTheSearchResults = driver.findElements(By.xpath(locatorSwitchToAll)).size() > 0;
                if (isShowingTheSearchResults) {
                    WebElement clickAll = driver.findElement(By.xpath(locatorSwitchToAll));
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript("arguments[0].click()", clickAll);
                    Thread.sleep(1000);
                };
                ////////////////////////////////////////////////////////////////////////////////////////////
                Thread.sleep(1000);
                String locatorTheFrameOfText = "//iframe[@title='Proactive Prompt']";
                String locatorClassPopupText = "//div[@class='frame-content']";
                String locatorButtonClosePopupText = "//button[@class='center-x-y flex-column-container center-x-y e1di9suy2 css-1s0vtz8 e1nedcse0']";
                Boolean isTheFrameOfTextAppear = driver.findElements(By.xpath(locatorTheFrameOfText)).size() > 0;
                if (isTheFrameOfTextAppear) {
                    driver.switchTo().defaultContent();
                    driver.switchTo().frame(driver.findElement(By.xpath(locatorTheFrameOfText)));
                    Boolean isPopUpTextAppear = driver.findElements(By.xpath(locatorClassPopupText)).size() > 0;
                    if (isPopUpTextAppear) {
                        WebElement clickOnClosePopupTextButton = driver.findElement(By.xpath(locatorButtonClosePopupText));
                        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        //Double Click the button
                        Boolean isTheCloseButtonPopupTextAppear = driver.findElements(By.xpath(locatorButtonClosePopupText)).size() > 0;
                        if(isTheCloseButtonPopupTextAppear) {
                            clickOnClosePopupTextButton.click();
                        }
                    };
                }

                String locatorBestMatchSection = "//*[@id=\"all\"]/div[1]/div[1]/section/a";
                Boolean isShowingBestMatch = driver.findElements(By.xpath(locatorBestMatchSection)).size() > 0;
                if (isShowingBestMatch) {
                    WebElement sectionBestMatch = driver.findElement(By.xpath(locatorBestMatchSection));
                    String redirectedUrl = sectionBestMatch.getAttribute("href");
                    System.out.println(siteCode.toUpperCase() + ",Yes" + ",Yes," + arrayKeywords.get(i).toString() + "," + redirectedUrl);
                    Thread.sleep(1000);
                } else
                    System.out.println(siteCode.toUpperCase() + ",No" + ",No," + arrayKeywords.get(i).toString() + ",Not Available");

                //Chụp ảnh
                TakeScreenshot takeScreenshot = new TakeScreenshot();
                Thread.sleep(1500);
                String pathNameMOSiteCode = "D:\\Auto\\Screenshot\\" + siteCode.toUpperCase() + "/" + siteCode.toUpperCase() + "_" + arrayKeywords.get(i).toString() + "_MO.png";
                String pathNamePCSiteCode = "D:\\Auto\\Screenshot\\" + siteCode.toUpperCase() + "/" + siteCode.toUpperCase() + "_" + arrayKeywords.get(i).toString() + "_PC.png";

                takeScreenshot.takeSnapShot(this.driver, pathNamePCSiteCode);

                driver.manage().window().setSize(new Dimension(390, 1200));
                //to perform Scroll on application using Selenium
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,100)", "");
                Thread.sleep(1500);
                takeScreenshot.takeSnapShot(this.driver, pathNameMOSiteCode);
                driver.manage().window().maximize();
                js.executeScript("window.scrollBy(0,-100)", "");

                //Đóng tab hiện tại
                driver.close();
                //Chuyển sang tab đã giữ
                driver.switchTo().window(winHandleBefore);
            }
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

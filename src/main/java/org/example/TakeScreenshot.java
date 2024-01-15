package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class TakeScreenshot {
//    public void testBStackTakeScreenShot() throws Exception{
//        String pathWebDriver = "D:\\Auto\\Setup\\chromedriver-win64\\chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",pathWebDriver);
//        WebDriver driver = new ChromeDriver();
//        String url = "https://p6-qa.samsung.com/sg/monitors/high-resolution/viewfinity-s9-27-inch-5k-resolution-ls27c900paexxs";
//        driver.get(url);
//        //Call take screenshot function
//        this.takeSnapShot(driver, "D://Test.png") ;
//    }
    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
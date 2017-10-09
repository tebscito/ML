package testing;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class Utilities {

    public static void takeScreenshot(WebDriver driver, String pathname){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
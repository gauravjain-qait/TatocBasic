package com.qait.gauravjain.tatocbasic;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qait.gauravjain.utility.GeneralActions;
import com.qait.gauravjain.utility.Utility;

public class TatocBasic {
    
    public static void main(String[] args) throws InterruptedException {
       
       WebDriver driver = new GeneralActions().getDriver(Utility.getConfigValue("browser"));
       
       //now use this to visit the url
       driver.get(Utility.getConfigValue("url"));
       
       //go to tatoc page
       driver.findElement(By.partialLinkText("tatoc")).click(); 
       driver.findElement(By.linkText("Basic Course")).click();
     
       //1 Grid Gate
        driver.findElement(By.className("greenbox")).click();
       
       //2 Frame Dungeon
       driver.switchTo().frame("main");
       String color1, color2;
       boolean color=true;
       while(true){
           color1 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
           driver.switchTo().frame("child");
           color2 = driver.findElement(By.cssSelector("#answer")).getAttribute("class");
           if(color1.equals(color2))
               break;
           driver.switchTo().parentFrame();
           driver.findElement(By.partialLinkText("Repaint Box 2")).click();
        }
        driver.switchTo().parentFrame();
        driver.findElement(By.partialLinkText("Proceed")).click();

         //3 Drag Around
         WebElement dragbox = driver.findElement(By.id("dragbox"));
         WebElement dropbox = driver.findElement(By.id("dropbox"));
         
         (new Actions(driver)).dragAndDrop(dragbox, dropbox).build().perform();
         driver.findElement(By.partialLinkText("Proceed")).click();
        
        //4 Popup Windows 
        String parent_window = driver.getWindowHandle();
        driver.findElement(By.partialLinkText("Launch Popup Window")).click();
        for(String handle:driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        driver.findElement(By.cssSelector("#name")).sendKeys("gaurav jain");
        driver.findElement(By.cssSelector("#submit")).click();
        driver.switchTo().window(parent_window);
        driver.findElement(By.partialLinkText("Proceed")).click();
        
        //5 Cookie Handling
        driver.findElement(By.partialLinkText("Generate Token")).click();
        String token=driver.findElement(By.cssSelector("#token")).getText();
        String[] tokenarr = token.split(": ");
        
        Cookie cookie = new Cookie("Token",tokenarr[1]);
        driver.manage().addCookie(cookie);
        driver.findElement(By.partialLinkText("Proceed")).click();

        //closing firefox
        //driver.quit();
    }
}
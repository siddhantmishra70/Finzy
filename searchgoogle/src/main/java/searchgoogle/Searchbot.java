package searchgoogle;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Searchbot {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions chromeoptions = new ChromeOptions();
//		'Setting up Driver Path
		WebDriverManager.chromedriver().setup();
//		WebDriverManager.firefoxdriver().setup();
//		WebDriverManager.edgedriver().setup();

		WebDriver driver = new ChromeDriver(chromeoptions);
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();

		WebElement element = driver.switchTo().activeElement();
		element.sendKeys("peer to peer lending");
		Thread.sleep(3000);

		List<WebElement> lstGoogle = driver.findElement(By.xpath("//ul[@role='listbox']"))
				.findElements(By.xpath("//li[@role='presentation']"));
		lstGoogle.get(0).click();
		Thread.sleep(5000);

		int i = 0;
		int flag = 0;
		int linkcounter = 1;

		while (flag == 0) {
			List<WebElement> lstlnk = driver.findElements(By.tagName("a"));
			for (i = 0; i < lstlnk.size() - 1; i++) {

				if (lstlnk.get(i).getText().contains("finzy")) {

					flag = 1;
				}
			}
			if (flag == 0) {
				linkcounter = linkcounter + 1;
				WebElement pagelink = driver
						.findElement(By.xpath("//a[contains(@aria-label,'Page " + linkcounter + "')]"));
				pagelink.click();
				lstlnk.clear();
			}
		}
		System.out.println("Got Finzy Website on " + linkcounter + " Page");
		driver.quit();
	}
}

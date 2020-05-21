package com.w2a.utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.w2a.base.Page;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utilities extends Page {

	public static String screenshotName;
	public static String getcurrenttime() {
		Date d = new Date();
		String current_time = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		return current_time;
	}

	// Get screenshot Using AShot
	public static void captureFullScreenUsingAshot(String screenshotpath,String methodName) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File(screenshotpath+methodName+"_Ashot_"+getcurrenttime()));
	}

	// Get full screenshot using inbuilt functionality
	public static void captureScreenShots(String screenshotpath, String methodName) throws IOException {
		
		screenshotName = screenshotpath+methodName+"_Full_"+getcurrenttime();
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(screenshotName));
	}

	// Get element screenshot with highlight
	public static void captureelement(String screenshotpath, String methodName, WebElement ele) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullimg = ImageIO.read(screenshot);

		Point point = ele.getLocation();
		int element_width = ele.getSize().getWidth();
		int element_height = ele.getSize().getHeight();

		BufferedImage element_screenshot = fullimg.getSubimage(point.getX(), point.getY(), element_width,element_height);
		ImageIO.write(element_screenshot, "jpg", screenshot);

		File sslocation = new File(screenshotpath+methodName+"_Element_"+getcurrenttime());
		FileUtils.copyFile(screenshot, sslocation);
	}

	// make zip of reports
	public static void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			File outFolder = new File(".\\zip\\Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

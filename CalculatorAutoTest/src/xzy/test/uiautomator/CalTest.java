package xzy.test.uiautomator;

import java.io.IOException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CalTest extends UiAutomatorTestCase {

	public void testDemo() throws UiObjectNotFoundException, RemoteException {

		UiDevice device = getUiDevice();
		// 唤醒屏幕
		device.wakeUp();
		assertTrue("screenOn: can't wakeup", device.isScreenOn());
		// 回到HOME
		device.pressHome();
		sleep(1000);

		// 启动计算器App
		try {
			Runtime.getRuntime().exec(
					"am start -n com.android.calculator2/.Calculator");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sleep(1000);
		UiObject oneButton = new UiObject(new UiSelector().text("1"));
		assertTrue("oneButton not found", oneButton.exists());
		UiObject plusButton = new UiObject(new UiSelector().text("+"));
		assertTrue("plusButton not found", plusButton.exists());

		sleep(100);

		UiObject equalButton = new UiObject(new UiSelector().text("="));
		assertTrue("equalButton not found", equalButton.exists());

		oneButton.click();
		sleep(100);
		plusButton.click();
		sleep(100);
		oneButton.click();

		equalButton.click();
		sleep(100);

		UiObject switcher = new UiObject(
				new UiSelector()
						.resourceId("com.android.calculator2:id/display"));
		UiObject result = switcher.getChild(new UiSelector().index(0));
		System.out.print("text is :" + result.getText());
		assertTrue("result != 2", result.getText().equals("2"));
	}
}

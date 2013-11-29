/*
 *    Copyright 2013 TOYAMA Sumio <jun.nama@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.jun_nama.test.utf7ime.sample;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UiAutomatorInputTest extends UiAutomatorTestCase {
    public void testDemo() throws UiObjectNotFoundException {

        // Press on the HOME button.
        getUiDevice().pressHome();

        // Launch the "Google" apps via the All Apps screen.
        UiObject allAppsButton = new UiObject(new UiSelector().description("Apps"));
        allAppsButton.clickAndWaitForNewWindow();
        UiObject appsTab = new UiObject(new UiSelector().text("Apps"));
        appsTab.click();
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.setAsHorizontalList();
        UiObject testApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),
                "Google");
        testApp.clickAndWaitForNewWindow();

        // Get the google search text box
        UiObject searchBox = new UiObject(
                new UiSelector().className("com.google.android.search.shared.ui.SimpleSearchText"));

        // do Japanese Input!
        searchBox.setText(Utf7ImeHelper.e("こんにちは！UiAutomatorで入力しています。"));
    }

}

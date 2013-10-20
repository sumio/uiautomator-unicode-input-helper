Uiautomator Unicode Input Helper
======================
(Japanese Version is [hear](README.ja.md).)

_Uiautomator Unicode Input Helper_ enables you to input any Unicode 
(including non-ASCII) characters by using Android 
[uiautomator](http://developer.android.com/tools/help/uiautomator/index.html).

Uiautomator has 
[UiObject.setText(String)](http://developer.android.com/tools/help/uiautomator/UiObject.html#setText%28java.lang.String%29)
API to set text in an editable field.
The API works well if the text consists of only the ASCII character, 
but can not input any non-ASCII characters, such as Japanese.
This limitation has prevented developers of non-English
applications from using uiautomator for testing.

Uiautomator Unicode Input Helper solves it by providing an IME,
which converts encoded text using only ASCII characters into
Unicode text.
Modified UTF-7 ([RFC 3501](http://tools.ietf.org/html/rfc3501))
is used for the encoding scheme.

Getting Started
------
### Precaution
_Utf7Ime_, which is installed by the following instructions,
prevents you from inputting any text via software keyboard.
**Do not use Utf7Ime as a human interface.**
It is intended for only test automation.

To avoid your confusion, change the default IME to your favorite
one after using it:

1. Show home screen by tapping home key.
2. Launch ``Settings``.
3. Open ``Language & input``.
4. Tap ``Default`` belonging to ``KEYBOARD & INPUT METHODS``
category.
5. Select your favorite IME.

### Prerequisites
#### Install Utf7Ime and change the default IME
* Import ``Utf7Ime/`` directory into your Eclipse workspace.
  You can import by 
  ``File -> Import... -> Existing Android Code Into Workspace``.
* Build ``Utf7Ime`` project and install it on your
  Android-powered device (or emulator) for UI testing.
* Launch ``Settings`` App in the device.
* Open ``Language & input``.
* Switch on the ``UTF7 IME for UI Testing`` checkbox belonging to
  ``KEYBOARD & INPUT METHODS`` category.
* Tap ``Default`` belonging to ``KEYBOARD & INPUT METHODS``
  category.
* Change the default IME to Utf7Ime by selecting 
  ``UTF7 IME for UI Testing``.

#### Prepare your Uiautomator tests
Prepare (or create) your test that runs in the uiautomator
framework.
If you have not written a test for uiautomator yet, you can see
detailed instruction
[here](http://developer.android.com/tools/testing/testing_ui.html).

#### Copy the helper library to your Uiautomator project
Copy ``helper-library/src/*`` to 
``<your uiaotomator project>/src/``.  
Note that a uiautomator project does not recognize any JAR
libraries in ``libs`` directory.

### Usage
Apply ``Utf7ImeHelper.e()`` to a string used for
``UiObject.setText()``. Any Unicode (including non-ASCII)
characters are accepted.

```java
import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

....

// gets UiObject which refers to editable text
UiObject editText = ...; 
// inputs German umlaut characters
editText.setText(Utf7ImeHelper.e("Danke schön"));
// inputs Japanese characters
editText.setText(Utf7ImeHelper.e("ありがとう"));
....
```

Note that you can set a string to ``UiObject.setText()``
directly as long as the string consists of only the ASCII
characters **except ``&``**.

```java
....    
UiObject editText = ...; 
// inputs ASCII text not containing '&'.
// Utf7ImeHelper.e() is not needed.
editText.setText("Thank you very much.");

// inputs ASCII text containing '&'.
// You must use Utf7ImeHelper.e().
editText.setText(Utf7ImeHelper.e("fish & chips"));
....
```

### Sample
You can see a sample project which resides in
``UiAutomatorInputSample/`` directory.
This sample code simulates the following instructions:

* Press on the HOME button.
* Launch the "Google" app.
* Input Japanese sentences into Google search box.  
  ![Result Screenshot](/images/sample-screenshot.png)

#### How to run
* Change the Locale of your testing device to ``English (United States)``.
* Set ``ANDROID_HOME`` environment variable properly.
* Connect your testing device.
* Change directories to the root of the sample project.
* Build and run by typing the following commands (ant is required):
```
ant clean build install
adb shell uiautomator runtest UiAutomatorInputSample.jar \
          -c jp.jun_nama.test.utf7ime.sample.UiAutomatorInputTest
```

The sample code is tested under Nexus7 (2013) and Android
emulators based on Android 4.3.

It is not run properly for Android emulators based on API level
17 or earlier because the class name of Google search box is
different. Rewrite search criteria in the sample code so that it
can be run under such a device or emulator.

Note that such restrictions are applied to only the sample code.
Utf7Ime and the helper library are run properly under devices
supporting uiautomator.

License
------
Copyright 2013 TOYAMA Sumio <<jun.nama@gmail.com>>  
Licensed under the
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

This software contains
[jutf7-1.0.0](http://sourceforge.net/projects/jutf7/) written by J.T. Beetstra,
which is available under a MIT license.

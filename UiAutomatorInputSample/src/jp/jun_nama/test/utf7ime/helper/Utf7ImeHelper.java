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
package jp.jun_nama.test.utf7ime.helper;

import java.nio.charset.Charset;

import com.beetstra.jutf7.CharsetProvider;

/**
 * Utf7ImeHelper provides a simple Modified UTF-7 encoder. <br/>
 * If you use it in uiautomator, you can write simply as follows:
 * 
 * <pre>
 * ....
 * 
 * UiObject editText = ...; 
 * editText.setText(Utf7ImeHelper.e("こんにちは")); // any Unicode String
 * 
 * ....
 * </pre>
 * 
 * @author TOYAMA Sumio
 * 
 */
public class Utf7ImeHelper {

    private static final Charset CHARSET_MODIFIED_UTF7 = new CharsetProvider().charsetForName("X-MODIFIED-UTF-7");

    /**
     * Encodes a specified text into modified UTF-7.
     * 
     * @param text
     *            plain unicode text
     * @return encoded text in modified UTF-7.
     */
    public static String e(String text) {
        byte[] encodedByte = text.getBytes(CHARSET_MODIFIED_UTF7);
        return new String(encodedByte, Charset.forName("US-ASCII"));
    }

}

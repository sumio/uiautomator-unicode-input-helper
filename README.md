Uiautomator Unicode Input Helper
======================
Androidの自動テストツール
[uiautomator](http://developer.android.com/tools/help/uiautomator/index.html)
で、日本語のような非ASCII文字の入力を実現します。

uiautomatorには、テキストボックスに文字を入力するためのメソッド
[UiObject.setText(String)](http://developer.android.com/tools/help/uiautomator/UiObject.html#setText%28java.lang.String%29)
がありますが、このメソッドの引数にはASCII以外の文字列
(正確には、QWERTYキーボードを使って入力できない文字列)
を指定することができません。

このプロジェクトでは、Modified UTF-7形式で入力された文字列を、デコードしてから
テキストボックスに送り込むIMEを提供することで、上記制限を解決します。

Usage
------
ここでは、uiautomatorを使ったテストプロジェクトの作り方や、実行方法については
説明しません。既にuiautomatorを使ったテストプロジェクトが存在しているものとします。

uiautomatorを使ったことの無い方は、Android Developersのサイトにある、
[UI Testing](http://developer.android.com/tools/testing/testing_ui.html)
を参考にしてください。

### 注意事項
ここでインストールしたUtf7Imeは、自動テスト用途のみを想定しているため、ソフトウェアキーボード
は実装されていません。
そのため、以下の手順に従ってUtf7Imeを有効化した状態では、テキストボックスにフォーカスを当てても、
ソフトウェアキーボードが表示されず、全く文字を入力することができなくなってしまいます。

**テストが終了したら、デフォルトのIMEを、通常利用するIMEに戻しておいてください。**
IMEを元に戻す手順は以下の通りです。

* ホームキーを押し、ホーム画面を表示します。
* [Settings]アプリを起動します。
* [Language &amp; input]を開きます。
* [KEYBOARD &amp; INPUT METHODS]カテゴリにある、[Default]を押下し、
  普段利用しているIMEを選択し直します。

### 準備
#### Utf7ImeのインストールとデフォルトIMEへの設定
* ``Utf7Ime/``ディレクトリ配下をEclipseにインポートします。
  [Import...]＞[Existing Android Code Into Workspace] でインポートできます。
* ``Utf7Ime``プロジェクトをビルドし、テスト対象端末へインストールします。
* テスト対象端末の[Settings]＞[Language &amp; input]を開きます。
* [KEYBOARD &amp; INPUT METHODS]カテゴリにある、[UTF7 IME for UI Testing]
  のチェックボックスをチェックします。
  IMEを有効化する時の警告ダイアログが表示されるため[OK]を押します。
* [KEYBOARD &amp; INPUT METHODS]カテゴリにある、
  [Default]を選択し、[UTF7 IME for UI Testing]をデフォルトのIMEに指定します。

#### ヘルパーライブラリのコピー
``helper-library/src``ディレクトリ配下のソースコードを、自分のuiautomatorプロジェクトの
``src/``ディレクトリ配下へコピーします。

uiautomatorのプロジェクトでは、``libs/``ディレクトリ配下にJARファイルを保存しても、
無視されてしまう点に注意してください。

### 使い方
以下のように、``UiObject.setText()``の引数に指定する文字列について、``Utf7ImeHelper.e()``
でラップしてください。

    import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;
    
    ....
    
    UiObject editText = ...; 
    editText.setText(Utf7ImeHelper.e("こんにちは"));
    ....

``UiAutomatorInputSample/``ディレクトリ配下の実装例も参考にしてください。

なお、``&amp;``以外のASCII文字を入力するのであれば、``Utf7ImeHelper.e()``でラップせずに、
``setText()``の引数に直接入力しても問題ありません。

### サンプルについて
``UiAutomatorInputSample/``ディレクトリ配下に、
uiautomatorで日本語を入力するサンプルプロジェクトが同梱されています。
このサンプルでは、Googleの検索ボックスに  
「こんにちは！UiAutomatorで入力しています。」  
と自動的に入力します。

サンプルプロジェクトをビルド・実行するには、``ANDROID_HOME``環境変数を適切に設定した上で、
以下のコマンドを実行してください(antが必要です)。
なお、**テスト対象端末のロケールは英語(English (United States))に設定しておく必要があります**。

    cd UiAutomatorInputSample
    ant clean build install
    adb shell uiautomator runtest UiAutomatorInputSample.jar \
              -c jp.jun_nama.test.utf7ime.sample.UiAutomatorInputTest

License
------
Copyright 2013 TOYAMA Sumio <<jun.nama@gmail.com>>  
Licensed under the
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

This software contains
[jutf7-1.0.0](http://sourceforge.net/projects/jutf7/) written by J.T. Beetstra,
which is available under a MIT license.

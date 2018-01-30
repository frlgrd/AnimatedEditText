AnimatedEditText
================

[ ![Download](https://api.bintray.com/packages/frlgrd/maven/animated-edit-text/images/download.svg) ](https://bintray.com/frlgrd/maven/animated-edit-text/_latestVersion)

This is a simple component to animate text inputs.

Implementation of [this](https://www.pinterest.fr/pin/406731410091824542/) ui prototype.

![Gif](art/aet.gif)

## Usage


```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorPrimary"
    android:orientation="vertical"
    android:padding="16dp">
    
    <frlgrd.animatededittext.AnimatedEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:editTextIcon="@drawable/name_icon"
        app:editTextInputType="name"
        app:hintText="Name"/>
        
    <frlgrd.animatededittext.AnimatedEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:editTextIcon="@drawable/mail_icon"
        app:editTextInputType="email"
        app:hintText="E-mail"/>
        
    <frlgrd.animatededittext.AnimatedEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:editTextIcon="@drawable/password_icon"
        app:editTextInputType="password"
        app:hintText="Password"/>
</LinearLayout>

```
####  Download
Via Gradle:

```groovy
dependencies {
    compile 'com.frlgrd:animated-edit-text:1.0.0'
}
```

#### Attributes

```
app:editTextIcon="reference"
```
Reference to a drawable used as drawableLeft (optional). 

```
app:hintText="string"
```
Standard edit text hint (optional).
```
app:editTextInputType="text|name|email|password|nonKeyboard|number|phone"
```
Apply an input type on the internal editText. Default value is `text`. Note : `nonKeyboard` allows you override 
input method like date picker instead of soft keyboard.

License
-----
```
MIT License

Copyright (c) 2018 François Legrand

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

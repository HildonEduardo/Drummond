Drummond Log Framework
===============

A simple and efficient Log framework for Android with support to Unity applications. Also, it logs when application crashes with simple crash message.

Gradle
------

Project level build.gradle:

```
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

App level build.gradle:
```
dependencies {
    ...
    implementation 'com.github.hildoneduardo:drummond:0.1'
}
```


Usage
-----
First configure your Application object:
```java
public class SampleApp extends Application {  

  @Override
  public void onCreate() {
    super.onCreate();
    DrummondHelper.initFullDebugMode(this);
    Drummond.getInstance().setSymbol("###"); // all your logs will start with this symbol
                                             // default symbol = "###"
  }  
}
```
Call log for debug:

```java
...
	Drummond.getInstance().d("This is a simple debug");
...
```


Changelog
---------
* **0.1**
    * Initial version with android support only

## Contributors

Hildon Eduardo Lima de Paula <<hildon.eduardo@gmail.com>>

License
-------

    Copyright 2019 - 2020 Hildon Lima

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

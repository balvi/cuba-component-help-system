[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/balvi/cuba-component-help-system.svg?branch=master)](https://travis-ci.org/balvi/cuba-component-help-system)

# CUBA Platform Component - Help system

This application component offers a context help system for your CUBA application. 

## Installation
Currently you have to [download](https://github.com/balvi/cuba-component-help-system/archive/master.zip) the app-component manually and import it into Studio. After opening it in studio, you have to execute "Run > Install app component". 
After that you can go into your project and add the dependency to you project through "Project Properties > Edit > custom components (+) > cuba-component-help-system".

Note: This manual installation step will probably simplify with Version 6.6 of CUBA and studio.

## Usage


### Defining help texts
To use the help system, users can define help texts for certain screens (even components) of our application. These texts are stored in the databse.


![Screenshot help text management](https://github.com/mariodavid/cuba-component-runtime-diagnose/blob/master/img/groovy-console-screenshot.png)

### Usage of the predefined help texts

When the user uses a particular screen of your application, there is a help button. 
Using that will open the help information for this particular screen. 

![Screenshot context help](https://github.com/mariodavid/cuba-component-runtime-diagnose/blob/master/img/groovy-console-screenshot.png)

[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/balvi/cuba-component-help-system.svg?branch=master)](https://travis-ci.org/balvi/cuba-component-help-system)

# CUBA Platform Component - Help system

This application component offers a context help system for your CUBA application. 


## Installation

1. Add the following maven repository `https://dl.bintray.com/balvi/cuba-components` to the build.gradle of your CUBA application:


    buildscript {
        
        //...
        
        repositories {
        
            // ...
        
            maven {
                url  "https://dl.bintray.com/balvi/cuba-components"
            }
        }
        
        // ...
    }

2. Select a version of the add-on which is compatible with the platform version used in your project:

| Platform Version | Add-on Version |
| ---------------- | -------------- |
| 6.10.x           | 0.5.x          |
| 6.9.x            | skipped        |
| 6.8.x            | 0.3.x          |
| 6.7.x            | 0.2.x          |
| 6.6.x            | 0.1.x          |

The latest version is: `0.5.1`

Add custom application component to your project:

* Artifact group: `de.balvi.cuba.helpsystem`
* Artifact name: `help-system-global`
* Version: *add-on version*

## Usage


### Defining help texts
To use the help system, users can define help texts for certain screens (even components) of the application. These texts are stored in the databse.


![Screenshot help text management](https://github.com/balvi/cuba-component-help-system/blob/master/img/help-text-management.png)

### Usage of the help texts in the application

When the user uses a particular screen of your application, there is a help button on the upper right of the screen. 
Using that will open the help information for this particular screen. 

In order to make a screen available for help text, just add the `@HasHelp` Annotation on your controller and extend from `AnnotatableAbstract(Lookup|Editor)` like this:

    @HasHelp
    class CustomerBrowse extends AnnotatableAbstractLookup {
    
    }
    
For more information on the annotation topic, you can take a look at the [declarative-controllers](https://github.com/balvi/cuba-component-declarative-controllers) application component.

Now you can take a look at the screen you annotated and will see the help button on the upper right:

![Screenshot context help](https://github.com/balvi/cuba-component-help-system/blob/master/img/context-help-usage.png)

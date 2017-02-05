# cordova-plugin-firebase-analytics
> Cordova plugin for [Firebase Analytics](https://firebase.google.com/docs/analytics/)

## Installation

    cordova plugin add https://github.com/ITDarCom/cordova-plugin-firebase-analytics --save

Go to your firebase console and export `google-services.json` and `GoogleService-Info.plist`. Put those files into the root of your cordova app folder.

NOTE: on iOS in order to collect demographic, age, gender data etc. you should additionally [include `AdSupport.framework`](https://firebase.google.com/support/guides/analytics-adsupport) into your project.

## Requirements

- cordova >= 6.4.0
- cordova-ios >= 4.3.0

## Supported Platforms

- iOS
- Android

## Methods

### logEvent(_name_, _params_)
Logs an app event.
```js
window.cordova.plugins.firebase.analytics.logEvent("page_view", {page: "dashboard"});
```

Be aware of [automatically collected events](https://support.google.com/firebase/answer/6317485).

### setUserId(_id_)
Sets the user ID property.
```js
window.cordova.plugins.firebase.analytics.setUserId("12345");
```
This feature must be used in accordance with [Google's Privacy Policy](https://www.google.com/policies/privacy).

### setUserProperty(_name_, _value_)
Sets a user property to a given value.
```js
window.cordova.plugins.firebase.analytics.setUserProperty("name1", "value1");
```

Be aware of [automatically collected user properties](https://support.google.com/firebase/answer/6317486?hl=en&ref_topic=6317484).

### setEnabled(_enabled_)
Sets whether analytics collection is enabled for this app on this device.
```js
window.cordova.plugins.firebase.analytics.setEnabled(false);
```

### setCurrentScreen(_name_)
Sets the current screen name, which specifies the current visual context in your app. This helps identify the areas in your app where users spend their time and how they interact with your app.
```js
window.cordova.plugins.firebase.analytics.setCurrentScreen("User profile");
```

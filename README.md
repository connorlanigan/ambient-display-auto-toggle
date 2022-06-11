![](/app/src/main/res/mipmap-xhdpi/ic_launcher.png)

# Ambient Display Auto Toggle for Android

An Android app that turns off Ambient Display (also called "Always On Display") while the device is
charging, so that it doesn't light up the room at night.

You can toggle Ambient Display manually in the Android system settings under
_Display_ > _Lock screen_ > _Always show time and info_. This app automates that switch.

## Installing & setup

This app requires Android 9 or higher.

Download the APK
from [the GitHub release page](https://github.com/connorlanigan/ambient-display-auto-toggle/releases)
onto your device. You might have to enable "Installing from unknown sources" to install the APK.

Since the app needs a special permission to be able to control the Always On setting, you need to
grant this permission manually from a PC. Follow these steps:

- Enable ADB debugging on your
  device ([instructions](https://developer.android.com/studio/command-line/adb#Enabling))
- Connect your device to a computer which has the ADB command-line installed
- Run the following command on the computer:  
  `adb shell pm grant com.connorlanigan.ambientdisplayautotoggle android.permission.WRITE_SECURE_SETTINGS`
- Launch the app at least once

When you connect your phone to a charger, the Android system will notify this app, and the app will
turn off the Ambient Display feature. When you disconnect the charger, the app will be notified
again, and will turn on the Ambient Display feature again.

## Hiding the persistent notification

By default, the app shows a persistent silent notification in your notification bar. This
notification is required by Android for allowing the app to continue receiving events about the
charging status in the background. You can safely hide the notification if you want. To do this:

1. Find the persistent notification in your notification shade
1. Tap and hold the notification, until more options appear
1. Tap _"Turn off notifications"_ in the bottom left
1. Tap on _"App is running in the background"_ to switch the notification off
1. Tap _"Apply"_

The app will continue running in the background. It will not consume any energy though, since it
only acts when the Android system notifies it that a charger has been connected/disconnected.

## License

MIT License

Copyright (c) 2022 Connor Lanigan

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
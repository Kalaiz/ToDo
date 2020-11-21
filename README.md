# ToDo App
![Android CI](https://github.com/Kalaiz/ToDo/workflows/Android%20CI/badge.svg)
## Table of Content:
- [Description](#-description)
- [Tools Used](#%EF%B8%8F-tools-used)
- [Reflection](#%EF%B8%8F-reflection)
- [Installation](#%EF%B8%8F-installation)

### 📜 Description:
A simple TO-DO utility Android app in which one can set priority for each To-Do.This app is built on a MVVM architecture and while building this app this[ Guide ](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html?index=..%2F..android-training#0)on building a MVVM architecture based application was helpful.

<p align="center">
<img src="/Resources/App_Overview.gif" width="15%" height="15%" />
</p>

### 🛠️ Tools Used:
1) Android Architecture components such as LiveData, ViewModel and Room Database.
2) Android Views such as RatingBar and Floating Action Button (FAB).
3) Android App components such as Fragments and Activities.
4) Android Studio for Development.

### ✍️ Reflection:
I have learned the fundamentals of a MVVM architecture and the various advantages of using it, such as a more organised and easily testable code. I also learned the [downsides](https://stackoverflow.com/questions/883895/what-are-the-problems-of-the-mvvm-pattern) of a MVVM architecture: 1) It might not be suited for a simple UI and 2) Architecture component such as Data/View Binding, which are predominantly used along a MVVM architecture are hard to debug as some of the logic are expressed declaratively (XML) rather than imperatively. I also got to know more on the activity and fragment lifecycle, and how they differ. Android Native Views such as RatingBar and FAB were used. I had intially planned to use a slider for letting user to set the priority of each _ToDo_, but felt it was unintuitive and hence went with a RatingBar.

### ⚙️ Installation:
Note: It will be easier to do the following via a phone (web browser in desktop mode).   
1) Login on to github if you arent logged on.
2) Click on the green tick on commit and file panel.
3) A dialog box with the hyperlink details should appear. Click on the hyperlink.
4) Click on artifacts, and you will be able to download the APK.
5) Install the APK. Allow App Installations from Unknown Sources, if requested.

# ToDo App
![Android CI](https://github.com/Kalaiz/ToDo/workflows/Android%20CI/badge.svg)
![Status](https://img.shields.io/badge/status-viewable-green)

## Table of Content:
- [Description](#-description)
- [Tools Used](#%EF%B8%8F-tools-used)
- [Reflection](#%EF%B8%8F-reflection)
- [References](#-references)
- [Installation](#%EF%B8%8F-installation)

### 📜 Description:
A simple _To-Do_ utility Android app in which one can set priority for each To-Do. Each priority set for each _To-Do_ is displayed via an appropriate color. This app is built on a MVVM architecture.

<p align="center">
<img src="/Resources/App_Overview.gif" width="25%" height="25%" />
</p>

### 🛠️ Tools Used:
1) Android Architecture components such as LiveData, ViewModel and Room Database.
2) Android Views such as RatingBar and Floating Action Button (FAB).
3) Android App components such as Fragments and Activities.
4) Android Studio for Development.
5) Java 

### ✍️ Reflection:
I have learned the fundamentals of a MVVM architecture and the various advantages of using it, such as a more organised and easily testable code. I also learned the [downsides](https://stackoverflow.com/questions/883895/what-are-the-problems-of-the-mvvm-pattern) of a MVVM architecture: 1) It might not be suited for a simple UI and 2) Architecture component such as Data/View Binding, which are predominantly used along a MVVM architecture are hard to debug as some of the logic are expressed declaratively (XML) rather than imperatively. I also got to know more on the activity and fragment lifecycle, and how they differ. Android Native Views such as RatingBar and FAB were used. I had intially planned to use a slider for letting user to set the priority of each _To-Do_, but felt it was unintuitive and hence went with a RatingBar.

### 🔖 References:
- While implementing the MVVM architecture, I used this [ Google Codelab ](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html?index=..%2F..android-training#0) as a learning resource.

### ⚙️ Installation:
Note: It will be easier to do the following via a phone (web browser in desktop mode).   
1) Login on to github if you arent logged on.
2) Click on the green tick on commit and file panel.
3) A dialog box with the hyperlink details should appear. Click on the hyperlink.
4) Click on artifacts, and you will be able to download the APK.
5) Install the APK. Allow App Installations from Unknown Sources, if requested.



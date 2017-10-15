#MVVM Architecture Components

A set of main components to build MVVM Architecture on Android.

Main dependencies are:
 * Google Data Binding
 * Recycler View
 * Kotlin

##How to make databinding activity:
 0. Create view model which represents your activity
 1. Extends from BaseDataBindingActivity
 2. Implement `getLayoutId`
 3. Implement `provideViewModel`

##How to make databinding fragment:
  0. Create view model which represents your fragment
  1. Extends from BaseDataBindingFragment
  2. Implement `getLayoutId`
  3. Implement `provideViewModel`
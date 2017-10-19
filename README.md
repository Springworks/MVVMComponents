# MVVM Architecture Components

A set of main components to build MVVM Architecture on Android.

https://travis-ci.org/Springworks/MVVMComponents.svg?branch=master

Main dependencies are:
 * Google Data Binding
 * Recycler View
 * Kotlin

## How to make databinding activity:
 0. Create view model which represents your activity
 1. Extends from BaseDataBindingActivity
 2. Implement `getLayoutId`
 3. Implement `provideViewModel`

## How to make databinding fragment:
  0. Create view model which represents your fragment
  1. Extends from BaseDataBindingFragment
  2. Implement `getLayoutId`
  3. Implement `provideViewModel`
  
## How to use RecyclerViewAdapter:
  0. Extends from BaseRecyclerViewAdapter
  1. Define model type of your list
  2. Create as many different items as you want
   2.1 Create item view model which extends from `ItemViewModel`
   2.2 For each view model create `xml` file which will represent this view model.
   *Important* variable in xml should be named as `viewModel`, otherwise you need to override `getViewModelResID` in viewHolder to provide your own resouce ID.
   2.3 For each view model create view holder which extends from `ItemViewHolder`
  3. define method `onCreateViewHolder`, and return correct view holder depends of viewType
   

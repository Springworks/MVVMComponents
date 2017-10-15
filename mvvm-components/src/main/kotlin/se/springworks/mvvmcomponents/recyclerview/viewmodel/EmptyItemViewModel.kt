package se.springworks.mvvmcomponents.recyclerview.viewmodel

class EmptyItemViewModel : ItemViewModel<Unit>() {

    override fun setItem(item: Unit, position: Int) {
        //do nothing
    }
}

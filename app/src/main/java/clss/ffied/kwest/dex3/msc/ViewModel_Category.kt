package clss.ffied.kwest.dex3.msc

import androidx.lifecycle.*
import clss.ffied.kwest.dex3.entities.Category
import kotlinx.coroutines.launch

class ViewModel_Category(private val repository: CatRepository): ViewModel() {

    val allWords: LiveData<List<Category>> = repository.allCategories.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(category: Category) = viewModelScope.launch {
        repository.insert(category)
    }

}



class CatViewModelFactory(private val repository: CatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel_Category::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModel_Category(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
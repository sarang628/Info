package com.sarang.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.torang_core.data.model.HoursOfOperation
import com.example.torang_core.data.model.Menu
import com.example.torang_core.data.model.Restaurant
import com.example.torang_core.repository.InfoRepository
import com.example.torang_core.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 레스토랑 상세화면의 뷰모델 입니다.
 * @param infoRepository 레스토랑 상세화면 저장소
 * [InfoFragment]
 * [InfoRepository]
 */
@HiltViewModel
open class InfoViewModel @Inject constructor(private val infoRepository: InfoRepository) : ViewModel() {
    /** 레스토랑 라이브데이터 */
    val restaurant = MutableLiveData<Restaurant>()

    /** 메뉴 라이브 데이터 */
    val menus = MutableLiveData<ArrayList<Menu>>()

    /** 운영시간 라이브 데이터 */
    val hoursOfOperstaions = MutableLiveData<ArrayList<HoursOfOperation>>()

    /**
     * 레스토랑 정보 로드 요청
     * @param restaurantId 레스토랑 아이디
     */
    fun load(restaurantId: Int) {
        Logger.d("load restaurant $restaurantId")
        if (restaurantId != 0) {
            loadRestaurant(restaurantId)
            loadMenus(restaurantId)
            loadHours(restaurantId)
        }
    }

    fun loadRestaurant(restaurantId: Int) {
        viewModelScope.launch {
            try {
                restaurant.postValue(infoRepository.loadRestaurant(restaurantId))
            } catch (e: Exception) {
                Logger.e(e.toString())
            }
        }
    }

    internal open fun loadMenus(restaurantId: Int) {
        viewModelScope.launch {
            try {
                menus.postValue(infoRepository.loadMenus(restaurantId))
            } catch (e: Exception) {

            }
        }
    }

    internal open fun loadHours(restaurantId: Int) {
        viewModelScope.launch {
            try {
                hoursOfOperstaions.postValue(infoRepository.loadHours(restaurantId))
            } catch (e: Exception) {

            }
        }

    }
}
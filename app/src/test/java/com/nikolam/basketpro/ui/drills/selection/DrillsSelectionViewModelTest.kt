package com.nikolam.basketpro.ui.drills.selection


import com.nikolam.basketpro.TestUtils.InstantExecutorExtension
import com.nikolam.basketpro.TestUtils.TestSchedulerExtension
import com.nikolam.basketpro.TestUtils.getOrAwaitValue
import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.data.FakeDrillRepository
import com.nikolam.basketpro.model.DrillsType
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@ExtendWith(value = [InstantExecutorExtension::class, TestSchedulerExtension::class])
internal class DrillsSelectionViewModelTest {

    private val fakeDrillRepository = FakeDrillRepository()

    private val mockDrillRepository = mockk<DrillRepository>(relaxed = true)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val drillsSelectionViewModel = DrillsSelectionViewModel(
        mockDrillRepository,
        mainThreadSurrogate
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `will populate livedata with correct value, when repository returns correct data`() {
        val expectedDrillTypeOne = DrillsType("title1", "url1")
        val expectedDrillTypeTwo = DrillsType("title2", "url2")

        every { mockDrillRepository.loadFullDrillType() } returns Observable.just(expectedDrillTypeOne, expectedDrillTypeTwo)

        drillsSelectionViewModel.fetchDrillTypes()

        val data = drillsSelectionViewModel.drillsTypeList.getOrAwaitValue()

        assertEquals(arrayListOf(expectedDrillTypeOne, expectedDrillTypeTwo), data )
    }

    @Test()
    fun `will catch and exception, when repository returns an exception`() {

        every { mockDrillRepository.loadFullDrillType() } returns Observable.error(IOException("Error"))

        drillsSelectionViewModel.fetchDrillTypes()



    }
}
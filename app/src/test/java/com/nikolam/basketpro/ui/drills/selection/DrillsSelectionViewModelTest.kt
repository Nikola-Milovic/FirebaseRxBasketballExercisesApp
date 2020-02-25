package com.nikolam.basketpro.ui.drills.selection


import com.nikolam.basketpro.TestUtils.InstantExecutorExtension
import com.nikolam.basketpro.TestUtils.TestSchedulerExtension
import com.nikolam.basketpro.data.DrillRepository
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(value = [InstantExecutorExtension::class, TestSchedulerExtension::class])
internal class DrillsSelectionViewModelTest {

    private val mockkDrillRepository: DrillRepository = mockk(relaxed = true)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val drillsSelectionViewModel = DrillsSelectionViewModel(
        mockkDrillRepository,
        Dispatchers.Unconfined
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
    fun `can return drills with images`() {
        val expectedDrillTypeOne = DrillsType("title1", "url1")
        val expectedDrillTypeTwo = DrillsType("title2", "url2")

        val fakeResponse = Observable.just(expectedDrillTypeOne, expectedDrillTypeTwo)

        every { mockkDrillRepository.loadDrillTypes() } returns fakeResponse

        drillsSelectionViewModel.fetchDrillTypes()

        assertEquals(listOf(expectedDrillTypeOne, expectedDrillTypeTwo), drillsSelectionViewModel.drillsTypeList.value)
    }



}
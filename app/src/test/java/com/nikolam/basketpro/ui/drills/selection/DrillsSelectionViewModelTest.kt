package com.nikolam.basketpro.ui.drills.selection


import androidx.lifecycle.Observer
import com.nikolam.basketpro.TestUtils.InstantExecutorExtension
import com.nikolam.basketpro.TestUtils.TestSchedulerExtension
import com.nikolam.basketpro.data.DrillRepository
import com.nikolam.basketpro.model.DrillsType
import io.mockk.*
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(value = [InstantExecutorExtension::class, TestSchedulerExtension::class])
internal class DrillsSelectionViewModelTest {


    private val testScope = TestCoroutineScope()

    val mockkDrillRepository = mockk<DrillRepository>()

    lateinit var drillsSelectionViewModel: DrillsSelectionViewModel

    val fakeResponse = Observable.just(DrillsType("title1", "url1"), DrillsType("title2", "url2"))

    val list = arrayListOf(DrillsType("title1", "url1"), DrillsType("title2", "url2"))

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @BeforeEach
    fun setup() {

        Dispatchers.setMain(mainThreadSurrogate)


        //every{ mockkDrillRepository.loadDrillTypes()} returns fakeResponse
        every { mockkDrillRepository.loadDrillTypes() } answers { fakeResponse }

        coEvery{ mockkDrillRepository.getImageUrl("loc") } returns "url"

        drillsSelectionViewModel = DrillsSelectionViewModel(
            mockkDrillRepository,
            mainThreadSurrogate,
            Dispatchers.Unconfined
        )

    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `when feed with correct data, will call updateImages onComplete`() =
        testScope.runBlockingTest {
            val observer = mockkDrillRepository.loadDrillTypes().test()
            every {
                drillsSelectionViewModel.updateImages(
                    arrayListOf(
                        DrillsType("title1", "url1"),
                        DrillsType("title2", "url2")
                    )
                )
            } just Runs

        }

    @Test
    fun `when fed with correct list, will update the imageUrls with correct Url from repository`() {

        var list = arrayListOf<DrillsType>()

        drillsSelectionViewModel.drillsTypeList.observeForever(Observer {
            list = it
        })

        drillsSelectionViewModel.updateImages(list)

        val expectedList = arrayListOf(DrillsType("title1", "url"), DrillsType("title2", "url"))


        assertEquals(
            expectedList,
            list
        )
    }

}
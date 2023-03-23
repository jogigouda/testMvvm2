package com.fitpeo.test.jogendra.viewmodals

import com.fitpeo.test.jogendra.modal.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class PhotosViewModalTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getPhotos()= runTest{
        Mockito.`when`(repository.getPhotosList()).thenReturn(Response.success(emptyList()))
        val sut = PhotosViewModal(repository)
        sut.photosList()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.photosList().getOrAwaitValue()
        Assert.assertEquals(0, result!!.size)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
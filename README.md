# FirebaseRxBasketballExercisesApp
An sample application utilizing Firebase and RxJava to displaybasketball drills (possibly track and other stuff in the future)

*THIS IS AN OLD SAMPLE PROJECT, NOT MAINTAINED*


Features unit tests, MVVM architecture utilizing RxJava. 


Showcase
-------------
App features listing of exercises that are stored in the Firebase database and storage, each exercise points to a youtube video.


![Showcase](screenshots/basktpro.gif "Showcase")




Libraries Used
--------------
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Koin][100] - For Dependancy Injection
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
  * [RxJava][30] - Is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
* Testing 
  * [Hamcrest][18] - A framework for writing matcher objects allowing 'match' rules to be defined declaratively.
  * [JUnit4][19] - Base testing framework.
  * [MockK][20] - A Kotlin mocking library
* Third Party
  * [Youtube player][21] - A youtube player inside android application, thanks to Pierfrancesco Soffritti.
  * [Glide][22] - Used for loading images from the URL into the Image Views.
  * [Firebase][23] - Storage, realtime Database easy to implement and get started.

[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycl
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[34]: https://developer.android.com/guide/components/fragments
[18]: http://hamcrest.org/
[19]: https://junit.org/junit4/
[20]: https://github.com/mockk/mockk
[21]: https://github.com/PierfrancescoSoffritti/android-youtube-player
[22]: https://github.com/bumptech/glide
[23]: https://firebase.google.com/
[30]: https://github.com/ReactiveX/RxJava

[100]: https://github.com/InsertKoinIO/koin

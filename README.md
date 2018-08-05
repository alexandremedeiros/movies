# Movies
Android app that consumes TMdb API

# Architecture
Used clean architecture divided in domain and presentation layers

# Third-party dependencies in presentation layer (app) 
compile 'com.android.support:appcompat-v7:22.1.1'  
compile 'com.android.support:recyclerview-v7:21.0.0'  
compile 'com.android.support:cardview-v7:21.0.0'  
compile 'com.squareup.picasso:picasso:2.5.2'  
compile 'com.melnykov:floatingactionbutton:1.3.0'  
compile 'com.ogaclejapan.smarttablayout:library:1.1.3@aar'  
compile 'com.ogaclejapan.smarttablayout:utils-v4:1.1.3@aar'  
compile 'com.mcxiaoke.photoview:library:1.2.3'  
compile 'com.pnikosis:materialish-progress:1.5'  
compile('com.crashlytics.sdk.android:crashlytics:2.2.4@aar') { transitive = true; }

# Third-party dependencies in domain layer
compile 'com.path:android-priority-jobqueue:1.1.2'  
compile 'com.squareup.retrofit:retrofit:1.9.0'

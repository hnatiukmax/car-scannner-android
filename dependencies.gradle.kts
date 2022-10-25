enum class Lib(val version: String, val lib: String) {
    AppCompat(version = "1.4.2", lib = "androidx.appcompat:appcompat"),
    Material(version = "1.6.1", lib = "com.google.android.material:material"),
    AdapterDelegatesLayoutContainer(version = "4.3.0", lib = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer"),
    AdapterDelegatesViewBinding(version = "4.3.0", lib = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding"),
    //Ktx
    FragmentKtx(version = "1.5.0", lib = "androidx.fragment:fragment-ktx"),
    CoreKtx(version = "1.8.0", lib = "androidx.core:core-ktx"),
    //DI
    HiltAndroid(version = "2.42", lib = "com.google.dagger:hilt-android"),
    HiltCompiler(version = "2.42", lib = "com.google.dagger:hilt-compiler"),
    //Navigation
    Cicerone(version = "7.1", lib = "com.github.terrakok:cicerone"),

    //Network
    Retrofit(version = "2.9.0", lib = "com.squareup.retrofit2:retrofit"),
    RetrofitMoshiConverter(version = "2.9.0", lib = "com.squareup.retrofit2:converter-moshi"),

    KoinCore(version = "3.2.1", lib = "io.insert-koin:koin-core"),
    KoinAndroid(version = "2.1.6", lib = "io.insert-koin:koin-android"),
    KoinViewModel(version = "2.1.6", lib = "io.insert-koin:koin-androidx-viewmodel");

    val libName get() = this.toString().decapitalize()
}

Lib.values()
    .map { it to "${it.lib}:${it.version}" }
    .forEach {
        project.extra[it.first.libName] = it.second
    }
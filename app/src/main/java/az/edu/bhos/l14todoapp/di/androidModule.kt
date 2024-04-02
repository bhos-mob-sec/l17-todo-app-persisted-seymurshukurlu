package az.edu.bhos.l14todoapp.di

import android.content.Context
import androidx.room.Room
import az.edu.bhos.l14todoapp.data.AppDatabase
import az.edu.bhos.l14todoapp.data.TodoDao
import az.edu.bhos.l14todoapp.data.TodoLocalData
import az.edu.bhos.l14todoapp.data.TodoLocalDataImpl
import az.edu.bhos.l14todoapp.data.TodoRemoteData
import az.edu.bhos.l14todoapp.data.TodoRemoteDataImpl
import org.koin.dsl.module
import az.edu.bhos.l14todoapp.flows.MainViewModel
import az.edu.bhos.l14todoapp.flows.edit.EditTodoViewModel
import az.edu.bhos.l14todoapp.data.TodoRepository
import az.edu.bhos.l14todoapp.data.TodoRepositoryImpl
import az.edu.bhos.l14todoapp.data.api.TodoAPI
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

const val APP_CONTEXT = "app_context"

val androidModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::EditTodoViewModel)

    single<TodoLocalData> { TodoLocalDataImpl(get()) }
    factory<TodoRepository> { TodoRepositoryImpl(get(), get()) }

    factory<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://6607bbf2a2a5dd477b1355dd.mockapi.io/")
            .addConverterFactory(
                get<Json>().asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .build()
    }

    factory<TodoAPI> { get<Retrofit>().create(TodoAPI::class.java) }

    factory<TodoRemoteData> {
        TodoRemoteDataImpl(get())
    }

    single(named(APP_CONTEXT)) {
        androidContext()
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            get<Context>(named(APP_CONTEXT)),
            AppDatabase::class.java, "app-database"
        ).build()
    }

    factory<TodoDao> {
        get<AppDatabase>().todoDao()
    }
}
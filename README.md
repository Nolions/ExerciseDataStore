# ExerciseDataStore

Android DataStore 練習

## Preferences DataStore

### dependencies

add dependencies of datastore-preferences on app build.gradle

```groovy
 dependencies {
    ...
    implementation "androidx.datastore:datastore-preferences:1.0.0"
    implementation "androidx.datastore:datastore-preferences-core:1.0.0"
    ...
}
```

### use

*** init ***

```kotlin
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
```

*** write ***

```kotlin
val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
GlobalScope.launch {
    dataStore.edit { settings ->
        val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
        settings[EXAMPLE_COUNTER] = currentCounterValue + 1
    }
}
```

*** read ***

```kotlin
val flow: Flow<Int> = dataStore.data.map {
    it[EXAMPLE_COUNTER] ?: 0
}

flow.asLiveData().observe(this) {
    Log.d("AndroidExerciseDataStore", "example_counter value: $it")
}
```

## Proto DataStore

### dependencies

```groovy
 dependencies {
    ...
    implementation "androidx.datastore:datastore:1.0.0"
    ...
}
```

### use

*** init ***

```kotlin
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
```
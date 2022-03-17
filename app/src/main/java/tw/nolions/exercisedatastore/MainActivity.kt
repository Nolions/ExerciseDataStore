package tw.nolions.exercisedatastore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.nolions.exercisedatastore.SettingsDataStore.dataStore

class MainActivity : AppCompatActivity() {
    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")

    lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton = findViewById(R.id.btn_save)

        buttonSave()
        observeData()
    }

    private fun buttonSave() {
        saveButton.setOnClickListener {
            GlobalScope.launch {
                storeData()
            }
        }
    }

    private suspend fun storeData() {
        dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = currentCounterValue + 1
        }
    }

    private fun observeData() {
        val flow: Flow<Int> = dataStore.data.map {
            it[EXAMPLE_COUNTER] ?: 0

        }

        flow.asLiveData().observe(this) {
            Log.d("aaa", "example_counter value: $it")
        }
    }
}

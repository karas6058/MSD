package org.iptime.kairas.hw09;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;

public class Setting extends PreferenceActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        SharedPreferences setting;
        SharedPreferences.Editor editor;

        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_activity);

            EditTextPreference edit;
            CheckBoxPreference loading;

            edit = (EditTextPreference) findPreference("edit");
            loading = (CheckBoxPreference) findPreference("loading");
            if (loading.isChecked()) {
                MainActivity.onPass = true;
            }

            edit.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (preference.getKey().equals(("edit"))) {
                        LoadingActivity.text = newValue.toString();
                    }
                    return false;
                }
            });
            loading.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (preference.getKey().equals("loading")) {
                        setting = preference.getSharedPreferences();
                        editor = setting.edit();

                        boolean chk = setting.getBoolean("loading", true);

                        if (chk) {
                            editor.putBoolean("loading", true);
                            editor.commit();
                            MainActivity.onPass = setting.getBoolean("loading", true);

                        } else {
                            editor.putBoolean("loading", false);
                            editor.clear();
                            editor.commit();
                            MainActivity.onPass = setting.getBoolean("loading", false);
                        }
                    }
                    return false;
                }
            });
        }
    }
}

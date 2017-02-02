package by.chemerisuk.cordova.firebase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class FirebaseAnalyticsPlugin extends CordovaPlugin {
    private static final String TAG = "FirebaseAnalyticsPlugin";

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void pluginInitialize() {
        Log.d(TAG, "Starting Firebase Analytics plugin");

        Context context = this.cordova.getActivity().getApplicationContext();

        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("logEvent".equals(action)) {
            logEvent(callbackContext, args.getString(0), args.getJSONObject(1));

            return true;
        } else if ("setUserId".equals(action)) {
            setUserId(callbackContext, args.getString(0));

            return true;
        } else if ("setUserProperty".equals(action)) {
            setUserProperty(callbackContext, args.getString(0), args.getString(1));

            return true;
        } else if ("setEnabled".equals(action)) {
            setEnabled(callbackContext, args.getString(0));

            return true;
        } else if ("setCurrentScreen".equals(action)) {
            setCurrentScreen(callbackContext, args.getString(0));

            return true;
        }

        return false;
    }

    private void logEvent(final CallbackContext callbackContext, final String name, final JSONObject params) throws JSONException {
        final Bundle bundle = new Bundle();
        Iterator iter = params.keys();
        while(iter.hasNext()){
            String key = (String)iter.next();
            Object value = params.get(key);

            if (value instanceof Integer || value instanceof Double) {
                bundle.putFloat(key, ((Number)value).floatValue());
            } else {
                bundle.putString(key, value.toString());
            }
        }

        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    this.firebaseAnalytics.logEvent(name, bundle);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setUserId(final CallbackContext callbackContext, final String id) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    this.firebaseAnalytics.setUserId(id);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setUserProperty(final CallbackContext callbackContext, final String name, final String value) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    this.firebaseAnalytics.setUserProperty(name, value);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setEnabled(final CallbackContext callbackContext,final String enabled) {
        cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				try {
					this.firebaseAnalytics.setAnalyticsCollectionEnabled(enabled == "true");
					callbackContext.success();
				} catch (Exception e) {
					callbackContext.error(e.getMessage());
				}
			}
		});
    }

    private void setCurrentScreen(final CallbackContext callbackContext,final String name) {        
        cordova.getThreadPool().execute(new Runnable() {
			public void run() {
				try {
					this.firebaseAnalytics.setCurrentScreen(this.cordova.getActivity(), name, null);
					callbackContext.success();
				} catch (Exception e) {
					callbackContext.error(e.getMessage());
				}
			}
		});
    }
}

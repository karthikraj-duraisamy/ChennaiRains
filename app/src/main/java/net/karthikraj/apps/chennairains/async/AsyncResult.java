package net.karthikraj.apps.chennairains.async;

import org.json.JSONObject;

public interface AsyncResult
{
    void onResult(JSONObject object);
}
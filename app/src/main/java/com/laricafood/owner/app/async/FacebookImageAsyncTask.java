package com.laricafood.owner.app.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.facebook.Profile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class FacebookImageAsyncTask extends AsyncTask<Void, Void, Bitmap>
{
    private ImageView profilePicture;

    public FacebookImageAsyncTask (ImageView profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    @Override
    protected Bitmap doInBackground (Void... params)
    {
        try
        {

            URL uri = new URL(Profile.getCurrentProfile().getProfilePictureUri(130,130).toString());

            InputStream is = (InputStream) uri.getContent();
            return BitmapFactory.decodeStream(is);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute (Bitmap bitmap)
    {
        super.onPostExecute(bitmap);
        if(bitmap != null)
        {
            profilePicture.setImageBitmap(bitmap);
        }
    }
}

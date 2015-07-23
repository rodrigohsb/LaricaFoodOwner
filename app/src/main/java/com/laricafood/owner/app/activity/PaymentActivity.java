package com.laricafood.owner.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.async.NewAccountAsyncTask;
import com.laricafood.owner.app.billing.IabHelper;
import com.laricafood.owner.app.billing.IabResult;
import com.laricafood.owner.app.billing.Inventory;
import com.laricafood.owner.app.billing.Purchase;
import com.laricafood.owner.app.util.Constants;

import java.util.UUID;

/**
 * Created by Rodrigo on 08/07/15.
 */
public class PaymentActivity extends AppCompatActivity
{

    static final String TAG = "PaymentActivity";

    private Context ctx;

    IabHelper mHelper;

    private static final String SKU_MONTHLY = "monthly";

    private static final int RC_REQUEST = 10001;

    private static String PAYLOAD = "";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ctx = this;

        mHelper = new IabHelper(ctx, Constants.APPLICATION_PUBLIC_KEY);
        mHelper.enableDebugLogging(true);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
        {
            public void onIabSetupFinished (IabResult result)
            {
                if (!result.isSuccess())
                {
                    AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                    bld.setMessage("Problem setting up in-app billing: " + result);
                    bld.setNeutralButton("OK", null);
                    bld.create().show();

                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null)
                {
                    return;
                }

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });

        findViewById(R.id.paymentButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!mHelper.subscriptionsSupported())
                {
                    AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                    bld.setMessage("Subscriptions not supported on your device yet. Sorry!");
                    bld.setNeutralButton("OK", null);
                    Log.d(TAG, "Showing alert dialog: Subscriptions not supported on your device yet. Sorry!");
                    bld.create().show();
                    return;
                }

                /* TODO: for security, generate your payload here for verification.
                 * See the comments on verifyDeveloperPayload() for more info.
                 * Since this is a SAMPLE, we just use an empty string, but on a production app you should carefully generate this.
                 */

                PAYLOAD = UUID.randomUUID().toString();

                mHelper.launchPurchaseFlow((Activity) ctx, SKU_MONTHLY, IabHelper.ITEM_TYPE_SUBS, RC_REQUEST, mPurchaseFinishedListener, PAYLOAD);
            }
        });

    }

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener()
    {
        public void onIabPurchaseFinished (IabResult result, Purchase purchase)
        {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null)
            {
                return;
            }

            if (result.isFailure())
            {
                AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                bld.setMessage("Error purchasing: " + result);
                bld.setNeutralButton("OK", null);
                bld.create().show();
                return;
            }

            if (!verifyDeveloperPayload(purchase))
            {
                AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                bld.setMessage("Error purchasing. Authenticity verification failed.");
                bld.setNeutralButton("OK", null);
                bld.create().show();
                return;
            }

            if (SKU_MONTHLY.equals(purchase.getSku()))
            {
                AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                bld.setMessage("Thank you for subscribing to infinite gas!");
                bld.setNeutralButton("OK", null);
                bld.create().show();

                new NewAccountAsyncTask(ctx).execute();
            }
            else
            {
                AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
                bld.setMessage("purchase.getSku() = " + purchase.getSku());
                bld.setNeutralButton("OK", null);
                bld.create().show();
            }
        }
    };

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
    {
        public void onQueryInventoryFinished (IabResult result, Inventory inventory)
        {
            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null)
            {
                return;
            }
        }
    };

    /**
     * Verifies the developer payload of a purchase.
     */
    boolean verifyDeveloperPayload (Purchase p)
    {
        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and verifying it here might seem like a good approach,
         * but this will fail in the case where the user purchases an item on one device and then uses your app on a different device,
         * because on the other device you will not have access to the random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them, so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the one who initiated the purchase flow
         *    (so that items purchased by the user on one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app installations is recommended.
         */

        return PAYLOAD == p.getDeveloperPayload();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data))
        {
            // not handled, so handle it ourselves (here's where you'd perform any handling of activity results not related to in-app billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
        else
        {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy();
    }

    @Override
    public void onBackPressed ()
    {
        finish();
    }
}

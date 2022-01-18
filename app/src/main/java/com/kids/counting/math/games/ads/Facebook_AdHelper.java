package com.kids.counting.math.games.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.kids.counting.math.games.Addition;
import com.kids.counting.math.games.Competition;
import com.kids.counting.math.games.Completemunu;
import com.kids.counting.math.games.Constant;
import com.kids.counting.math.games.CountingActivity;
import com.kids.counting.math.games.Division;
import com.kids.counting.math.games.Multiplication;
import com.kids.counting.math.games.R;
import com.kids.counting.math.games.Subtraction;

import java.util.ArrayList;
import java.util.List;

public class Facebook_AdHelper extends Activity{

   static AdListener adListener;
   static AdView bannerView;
   static InterstitialAd mInterstitialAd;
   static String TAG = "facebook_ads";
    static NativeAdLayout nativeAdLayout;
    static LinearLayout adView;
    static NativeAd nativeAd;
    static boolean connected = false;

    public static void iniSDK(Activity activity){
        AudienceNetworkAds.initialize(activity); //...... facebook sdk
        if (isOnline(activity)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadFacebookInterstitialAd(activity);
                }
            }, 1000);
        }
        else {
            Log.e(TAG,"No Internet cannot pass request");
        }

    }

    public static void loadFacebookBannerAd(Activity activity){
        if (isOnline(activity)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bannerView = new AdView(activity, activity.getString(R.string.fb_banner_id),
                            AdSize.BANNER_HEIGHT_50);
                    LinearLayout adContainer = activity.findViewById(R.id.fb_bannerlayout);
                    adContainer.addView(bannerView);
                    adListener = new AdListener() {
                        @Override
                        public void onError(Ad ad, AdError adError) {
                            Log.e(TAG, "fbbanner_ad loading failed" + adError.getErrorMessage());
                            Log.e(TAG, "fbbanner_ad loading failed" + adError.getErrorCode());

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            Log.e(TAG, "fbbanner_ad loaded success");
                            adContainer.addView(bannerView);
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            Log.e(TAG, "fbbanner_ad ,, ad clicked");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            Log.e(TAG, "fbbanner_ad ,, logged impression");
                        }
                    };
                    bannerView.loadAd();
                }
            }, 1000);
        }
        else {
            Log.e(TAG, "fb banner, no internet ");
        }
    }

    private static void loadAgainFbInterstitialAd(Activity activity){
                    mInterstitialAd = new InterstitialAd(activity,
                            activity.getString(R.string.fb_interstitial_Id));
                    InterstitialAdListener interstitialAdListener  =
                            new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            Log.e(TAG, "interstitial ad : dismissed");
                            getSwitch(activity);
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            Log.e(TAG, "interstitial ad error: "+adError.getErrorMessage());
                            getSwitch(activity);
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            Log.e(TAG, "interstitial ad loaded success");
                            if (mInterstitialAd != null){
                                mInterstitialAd.show();
                                loadFacebookInterstitialAd(activity);
                                Log.e(TAG, "fb interstitial , shown success 2nd attempt");
                            }
                            else {
                                getSwitch(activity);
                            }
                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    };
                    mInterstitialAd.loadAd(mInterstitialAd.buildLoadAdConfig().
                            withAdListener(interstitialAdListener).build());
                }

    public static void loadFacebookInterstitialAd(Activity activity){
                    mInterstitialAd = new InterstitialAd(activity,
                            activity.getString(R.string.fb_interstitial_Id));
                    InterstitialAdListener interstitialAdListener  = new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            Log.e(TAG, "interstitial ad : dismissed");
                            getSwitch(activity);
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            Log.e(TAG, "interstitial ad error: "+adError.getErrorMessage());
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            mInterstitialAd= (InterstitialAd) ad;
                            Log.e(TAG, "interstitial ad loaded success : 1st attempt");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    };
                    mInterstitialAd.loadAd(mInterstitialAd.buildLoadAdConfig().
                            withAdListener(interstitialAdListener).build());


    }

    public static void showFbInterstitialAd(Activity activity){
        if (mInterstitialAd != null && mInterstitialAd.isAdLoaded()){
            mInterstitialAd.show();
            Log.e(TAG,"fb interstitial already loaded and shown: 1st attempt");
            //loadFacebookInterstitialAd(activity);
        }
        else if (isOnline(activity)){
            Log.e(TAG, "fb interstitial : 1st attempt null and loading again");
            loadAgainFbInterstitialAd(activity);
        }
        else {
            getSwitch(activity);
            Log.e(TAG, "interstitial ad: null and not connected to internet");
        }
    }


    //.............................................................
    private static void AddIntent(Activity activity){
        Intent intent= new Intent(activity, Addition.class);
        activity.startActivity(intent);
    }

    private static void competitionIntent(Activity activity){
        Intent intent= new Intent(activity, Completemunu.class);
        activity.startActivity(intent);
    }

    private static void countingintent(Activity activity){
        Intent intent= new Intent(activity, CountingActivity.class);
        activity.startActivity(intent);
    }

    private static void nextintent(Activity activity){
        Intent intent1= activity.getIntent();
        int activity1=intent1.getIntExtra("Activity",0);
        if (activity1 == 1) {
            Intent intent = new Intent(activity, Addition.class);
            activity.startActivity(intent);
            activity.finish();
        }
        if (activity1 == 2) {
            Intent intent = new Intent(activity, Subtraction.class);
            activity.startActivity(intent);
            activity.finish();
        }
        if (activity1 == 3) {
            Intent intent = new Intent(activity, Division.class);
            activity.startActivity(intent);
            activity.finish();
        }
        if (activity1 == 4) {
            Intent intent = new Intent(activity, Multiplication.class);
            activity.startActivity(intent);
            activity.finish();
        }
        if (activity1 == 5) {
            Intent intent = new Intent(activity, Competition.class);
            Intent intent2= activity.getIntent();
            int complete=intent2.getIntExtra("complete",0);
            if (complete==1){
                intent.putExtra("compete",1);
            }
            if (complete==2){
                intent.putExtra("compete",2);
            }
            if (complete==3){
                intent.putExtra("compete",3);
            }
            if (complete==4){
                intent.putExtra("compete",4);
            }

            activity.startActivity(intent);
        }

    }



    private static void getSwitch(Activity activity){
        switch (Constant.intentconstant){
            case 1:
                AddIntent(activity);
                break;

            case 2:
                competitionIntent(activity);
                break;

            case 3:
                countingintent(activity);
                break;

            case 4:
                nextintent(activity);
                break;

        }
    }




    ///...... native ad
    public static void loadFacebookNativeAd(Activity activity){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nativeAd = new NativeAd(activity, activity.getResources().getString(R.string.fb_native_id));
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                        // Native ad finished downloading all assets
                        Log.e(TAG, "Native ad finished downloading all assets.");
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Native ad failed to load
                        Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                        Log.e(TAG, "Native ad failed to load: " + adError.getErrorCode());
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Native ad is loaded and ready to be displayed
                        Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                        if (nativeAd == null || nativeAd != ad) {
                            return;
                        }
                        // Inflate Native Ad into Container
                        inflateAd(nativeAd,activity);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Native ad clicked
                        Log.d(TAG, "Native ad clicked!");
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Native ad impression
                        Log.d(TAG, "Native ad impression logged!");
                    }
                };

                // Request an ad
                nativeAd.loadAd(
                        nativeAd.buildLoadAdConfig()
                                .withAdListener(nativeAdListener)
                                .build());

            }
        }, 1000);

    }

    public static void inflateAd(NativeAd nativeAd,Activity activity) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = activity.findViewById(R.id.native_ad_container);
        LayoutInflater inflater = LayoutInflater.from(activity);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adView = (LinearLayout) inflater.inflate(R.layout.facebook_native_ad,
                nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = activity.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity,
                nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }



    //....... create method to check either mobile network (Sim data) or wifi is connected or not
    public static boolean isOnline(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }


    @Override
    protected void onDestroy() {
        if (bannerView != null) {
            bannerView.destroy();
        }
        if (mInterstitialAd != null){
            mInterstitialAd.destroy();
        }
        super.onDestroy();
    }

}

package com.dev7.oldster.adsnativerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-4037961928861681/8155334655";
    public static final int ITEMS_PER_AD = 4;
    private static final int NATIVE_EXPRESS_AD_HEIGHT = 150;
    private RecyclerView mRecyclerView;
    private List<Object> mCommentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mCommentItem = new ArrayList<>();
        addCommentItem();
        addNativeExpressAds();
        setUpAndLoadNativeExpressAds();

        RecyclerView.Adapter adapter = new AdsNativeAdapter(this, mCommentItem);
        mRecyclerView.setAdapter(adapter);
    }

    private void addNativeExpressAds() {

        // Loop through the items array and place a new Native Express ad in every ith position in
        // the items List.
        for (int i = ITEMS_PER_AD; i <= mCommentItem.size(); i += ITEMS_PER_AD) {
            final NativeExpressAdView adView = new NativeExpressAdView(MainActivity.this);
            mCommentItem.add(i, adView);
        }
    }

    /**
     * Sets up and loads the Native Express ads.
     */
    private void setUpAndLoadNativeExpressAds() {
        // Use a Runnable to ensure that the RecyclerView has been laid out before setting the
        // ad size for the Native Express ad. This allows us to set the Native Express ad's
        // width to match the full width of the RecyclerView.
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                final float scale = MainActivity.this.getResources().getDisplayMetrics().density;
                // Set the ad size and ad unit ID for each Native Express ad in the items list.
                for (int i = ITEMS_PER_AD; i <= mCommentItem.size(); i += ITEMS_PER_AD) {
                    final NativeExpressAdView adView =
                            (NativeExpressAdView) mCommentItem.get(i);
                    final CardView cardView = (CardView) findViewById(R.id.ad_card_view);
                    final int adWidth = cardView.getWidth() - cardView.getPaddingLeft()
                            - cardView.getPaddingRight();
                    AdSize adSize = new AdSize((int) (adWidth / scale), NATIVE_EXPRESS_AD_HEIGHT);
                    adView.setAdSize(adSize);
                    adView.setAdUnitId(AD_UNIT_ID);
                }

                // Load the first Native Express ad in the items list.
                loadNativeExpressAd(ITEMS_PER_AD);
            }
        });
    }

    /**
     * Loads the Native Express ads in the items list.
     */
    private void loadNativeExpressAd(final int index) {

        if (index >= mCommentItem.size()) {
            return;
        }

        Object item = mCommentItem.get(index);
        if (!(item instanceof NativeExpressAdView)) {
            throw new ClassCastException("Expected item at index " + index + " to be a Native"
                    + " Express ad.");
        }

        final NativeExpressAdView adView = (NativeExpressAdView) item;

        // Set an AdListener on the NativeExpressAdView to wait for the previous Native Express ad
        // to finish loading before loading the next ad in the items list.
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // The previous Native Express ad loaded successfully, call this method again to
                // load the next ad in the items list.
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // The previous Native Express ad failed to load. Call this method again to load
                // the next ad in the items list.
                Log.e("MainActivity", "The previous Native Express ad failed to load. Attempting to"
                        + " load the next Native Express ad in the items list.");
                loadNativeExpressAd(index + ITEMS_PER_AD);
            }
        });

        // Load the Native Express ad.
        adView.loadAd(new AdRequest.Builder().build());
    }

    private void addCommentItem() {
        mCommentItem.add(new CommentData("Old'ster Aunt", "27/02/60", "Good Morning!!"));
        mCommentItem.add(new CommentData("Mintra Sriprom", "28/02/60", "Good Afternoon!!"));
        mCommentItem.add(new CommentData("Pop Nuttapong", "01/03/60", "Good Evening!!"));
        mCommentItem.add(new CommentData("Ken eiei", "02/03/60", "Good Night!!"));
        mCommentItem.add(new CommentData("Old'ster Aunt", "27/02/60", "Good Morning!!"));
        mCommentItem.add(new CommentData("Mintra Sriprom", "28/02/60", "Good Afternoon!!"));
        mCommentItem.add(new CommentData("Pop Nuttapong", "01/03/60", "Good Evening!!"));
        mCommentItem.add(new CommentData("Ken eiei", "02/03/60", "Good Night!!"));
        mCommentItem.add(new CommentData("Old'ster Aunt", "27/02/60", "Good Morning!!"));
        mCommentItem.add(new CommentData("Mintra Sriprom", "28/02/60", "Good Afternoon!!"));
        mCommentItem.add(new CommentData("Pop Nuttapong", "01/03/60", "Good Evening!!"));
        mCommentItem.add(new CommentData("Ken eiei", "02/03/60", "Good Night!!"));
        mCommentItem.add(new CommentData("Old'ster Aunt", "27/02/60", "Good Morning!!"));
        mCommentItem.add(new CommentData("Mintra Sriprom", "28/02/60", "Good Afternoon!!"));
        mCommentItem.add(new CommentData("Pop Nuttapong", "01/03/60", "Good Evening!!"));
        mCommentItem.add(new CommentData("Ken eiei", "02/03/60", "Good Night!!"));
        mCommentItem.add(new CommentData("Old'ster Aunt", "27/02/60", "Good Morning!!"));
        mCommentItem.add(new CommentData("Mintra Sriprom", "28/02/60", "Good Afternoon!!"));
        mCommentItem.add(new CommentData("Pop Nuttapong", "01/03/60", "Good Evening!!"));
        mCommentItem.add(new CommentData("Ken eiei", "02/03/60", "Good Night!!"));

    }
}

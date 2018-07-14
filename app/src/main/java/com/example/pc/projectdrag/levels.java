package com.example.pc.projectdrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.sip.SipAudioCall;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Source;

public class levels extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;
    private ImageView nextImg;
    private ImageView previousImg;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Context context = this;


        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);

        nextImg = (ImageView) findViewById(R.id.nextImg);
        previousImg = (ImageView) findViewById(R.id.previousImg);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(context);
        mSlideViewPager.setAdapter(sliderAdapter);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        addDotsIndicator(0);
        if(Globals.lang != null) {
            switch (Globals.lang.getId()) {
                case R.id.radioAnglais:
                    mNextBtn.setText("Next");
                    mBackBtn.setText("");
                    break;
                case R.id.radioArab:
                    mNextBtn.setText("التالي");
                    mBackBtn.setText("");
                    break;
                case R.id.radioFrancais:
                    mNextBtn.setText("Suivant");
                    mBackBtn.setText("");
                    break;
            }
        }

    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        Context context = this;
        for(int i=0;i<mDots.length; i++){
            mDots[i] = new TextView(context);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(41);
            mDots[i].setTextColor(ContextCompat.getColor(context, R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){
            mDots[position].setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1){

        }

        @Override
        public void onPageSelected(int i){
            addDotsIndicator(i);

            mCurrentPage = i;

            if(i==0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                previousImg.setVisibility(View.INVISIBLE);
                nextImg.setVisibility(View.VISIBLE);
                if(Globals.lang != null) {
                    switch (Globals.lang.getId()) {
                        case R.id.radioAnglais:
                            mNextBtn.setText("Next");
                            mBackBtn.setText("");
                            break;
                        case R.id.radioArab:
                            mNextBtn.setText("التالي");
                            mBackBtn.setText("");
                            break;
                        case R.id.radioFrancais:
                            mNextBtn.setText("Suivant");
                            mBackBtn.setText("");
                            break;
                    }
                }
            } else if (i== mDots.length -1){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                previousImg.setVisibility(View.VISIBLE);
                nextImg.setVisibility(View.INVISIBLE);
                if(Globals.lang != null) {
                    switch (Globals.lang.getId()) {
                        case R.id.radioAnglais:
                            mNextBtn.setText("Finish");
                            mBackBtn.setText("Back");
                            break;
                        case R.id.radioArab:
                            mNextBtn.setText("النهاية");
                            mBackBtn.setText("السابق");
                            break;
                        case R.id.radioFrancais:
                            mNextBtn.setText("Fin");
                            mBackBtn.setText("Precedant");
                            break;
                    }
                }
            } else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                previousImg.setVisibility(View.VISIBLE);
                nextImg.setVisibility(View.VISIBLE);
                if(Globals.lang != null){
                    switch (Globals.lang.getId()){
                        case R.id.radioAnglais :
                            mNextBtn.setText("Next");
                            mBackBtn.setText("Back");
                            break;
                        case R.id.radioArab:
                            mNextBtn.setText("التالي");
                            mBackBtn.setText("السابق");
                            break;
                        case R.id.radioFrancais:
                            mNextBtn.setText("Suivant");
                            mBackBtn.setText("Precedant");
                            break;
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int i){

        }
    };


}
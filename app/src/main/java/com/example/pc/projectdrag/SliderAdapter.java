package com.example.pc.projectdrag;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;
    }

    //Arrays
    public int[] slide_images = {
            R.drawable.level1icon,
            R.drawable.level2icon,
            R.drawable.level3icon
    };

    public String[] slide_headings = {
            "Niveau 1",
            "Niveau 2",
            "Niveau 3 (Locked)"
    };

    public String[] slide_descs = {
            "Dans ce niveau vous testez votre precision d'entrer le cercle "
                    +"dans l'obstacle",
            "Ce niveau teste votre capacité de ditinguer entre les couleurs",
            "Dans ce niveau vous devez etre capable d'entrer le cercle dans "+
                    "l'obstacle, meme si ce dernier change sa place a chaque fois"
    };
    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout) o;
    }

    @Override
    public  Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        if(Globals.lang!=null) {
            switch (Globals.lang.getId()) {
                case R.id.radioAnglais:
                    slide_headings = new String[]{
                            "Level 1",
                            "Level 2",
                            "Level 3 (Locked)"
                    };
                    slide_descs = new String[]{
                            "In this level we test your precision to enter the ring"
                                    + "in the obstacle",
                            "This level test your capacity to distinguish between color",
                            "In this level you should to enter the circle in the obstacle" +
                                    "even if the obstacle change his place",
                    };
                    break;
                case R.id.radioArab:
                    slide_headings = new String[]{
                            "المرحلة 1",
                            "المرحلة 2",
                            "المرحلة 3 (مقفل)"
                    };
                    slide_descs = new String[]{
                            "في هذه المرحلة يتم اختبار دقتك من خلال ادخال الحلقة في العمود",
                            "هذه المرحلة يتم اختبار قدرتك على التفريق بين الألوان",
                            "مهمتك في هاته المرحلة هي ادخال الحلقة في العمود، مع العلم أن العمود يغير مكانه"
                    };
                    break;
            }
        }

        ImageView slideImageView =  (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(position == 0){
                    Intent readMore = new Intent(v.getContext(), level2.class);
                    v.getContext().startActivity(readMore);
                } else if(position == 1){
                    Intent readMore = new Intent(v.getContext(), level1.class);
                    v.getContext().startActivity(readMore);
                } else if(position == 2){
                    switch (Globals.lang.getId()){
                        case R.id.radioAnglais :
                            Toast.makeText(context, "Under construction", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.radioArab:
                            Toast.makeText(context, "في طور الإنجاز", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.radioFrancais:
                            Toast.makeText(context, "En cours de construction", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });

        return view;
    }



    @Override
    public  void  destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout) object);
    }


}

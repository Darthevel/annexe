package com.ehret.gridlayoutexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import com.google.common.collect.Lists;

import java.util.List;

public class MainActivity extends Activity {

    private GridLayout myGridLayout;

    private List<EventPlanning> planningJour = Lists.newArrayList(
            new EventPlanning(10, 12, "Conference", R.drawable.typeevent1_background),
            new EventPlanning(14, 16, "Atelier", R.drawable.typeevent2_background),
            new EventPlanning(16, 17, "Keynote", R.drawable.typeevent3_background));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        myGridLayout = (GridLayout) findViewById(R.id.gridView);
        dessinerVue();
    }


    @Override
    protected void onResume() {
        super.onResume();
        dessinerVue();
    }


    protected void dessinerVue() {
        myGridLayout.removeAllViews();
        //myGridLayout.setBackgroundColor(getResources().getColor(android.R.color.black));

        //On commence par créer une ligne contenant les titres
        myGridLayout.addView(createTextView("H", 0, 0, R.color.grey_dark, false, false));
        myGridLayout.addView(createTextView("Jour 1", 0, 1, R.color.grey_dark, false, false));
        myGridLayout.addView(createTextView("Jour 2", 0, 2, R.color.grey_dark, false, false));

        //On utilise les heures dans la premiere colonne de 9H à 20H
        for (int row = 1; row < 13; row++) {
            //Heure sur 4 lignes
            myGridLayout.addView(createTextView(String.valueOf(row + 8).concat("H"), row, 0, R.color.grey_dark, row == 12, false));
        }

        //On construit maintenant le planning

        //pour simplifier on applique le même planing pour les 2 jours
        for (int column = 1; column < 3; column++) {
            for (int row = 1; row < 13; row++) {
                EventPlanning eventfound = null;
                int rowspan = 1;
                for (EventPlanning event : planningJour) {
                    if (row + 8 == event.getStart()) {
                        eventfound = event;
                        rowspan = event.getEnd() - event.getStart();
                    }
                }
                if (eventfound == null) {
                    myGridLayout.addView(createTextView(" ", row, column,R.color.grey_light, row == 12, column == 2));
                } else {
                    myGridLayout.addView(createButton(eventfound.getLabel(), row, column, rowspan, eventfound.getDrawable(), row == 11, column == 2));
                    if (rowspan > 1) {
                        row = row + rowspan - 1;
                    }
                }
            }
        }
        myGridLayout.addView(createTextView("Test", 0, 2, R.color.grey_dark, false, false));

        //On ajoute un espace en derniere ligne pour combler l'espace
        myGridLayout.addView(createSpace(13,0,1,4));
        //et en derniere colonne
        myGridLayout.addView(createSpace(0,3,12,1));


    }

    private Space createSpace(int row, int column, int rowspan, int columnspan) {
        Space space = new Space(this);
        space.setLayoutParams(new GridLayout.LayoutParams(
                GridLayout.spec(row,rowspan, GridLayout.FILL),
                GridLayout.spec(column,columnspan, GridLayout.FILL)));
        space.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        return space;
    }

    /**
     * Cette méthode permet d'ajouter un textView
     *
     * @param label
     * @param row
     * @param column
     * @param color
     * @param borderBottom
     * @param borderRight
     * @return
     */
    private TextView createTextView(String label, int row, int column, int color, boolean borderBottom, boolean borderRight) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(label);
        textView.setBackgroundColor(getResources().getColor(color));
        textView.setPadding(5, 5, 5, 5);
        setLayoutAndBorder(textView,
                new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.FILL),
                        GridLayout.spec(column, GridLayout.FILL)),
                borderBottom, true, true, borderRight);
        return textView;
    }

    /**
     * Cette méthode permet d'ajouter un textView
     *
     * @param label
     * @param row
     * @param column
     * @param drawable
     * @param borderBottom
     * @param borderRight
     * @return
     */
    private Button createButton(final String label, int row, int column, int rowspan, int drawable, boolean borderBottom, boolean borderRight) {
        Button button = new Button(this);
        button.setGravity(Gravity.CENTER);
        button.setText(label);
        button.setBackgroundResource(drawable);
        button.setPadding(5, 5, 5, 5);
        setLayoutAndBorder(button,
                new GridLayout.LayoutParams(
                        GridLayout.spec(row, rowspan, GridLayout.FILL),
                        GridLayout.spec(column, GridLayout.FILL)),
                borderBottom, true, true, borderRight);
        button.setClickable(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), label, Toast.LENGTH_LONG).show();
            }
        });
        return button;
    }

    private void setLayoutAndBorder(View view, GridLayout.LayoutParams params,
                                    boolean borderBottom, boolean borderTop, boolean borderLeft, boolean borderRight) {
        params.bottomMargin = borderBottom ? 1 : 0;
        params.leftMargin = borderLeft ? 1 : 0;
        params.rightMargin = borderRight ? 1 : 0;
        params.topMargin = borderTop ? 1 : 0;
        view.setLayoutParams(params);
    }
}

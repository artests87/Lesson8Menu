package com.example.artests.lesson8menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int IDM_NEW=101;
    public static final int IDM_OPEN=102;
    public static final int IDM_SAVE=103;
    public static final int IDM_GROUP=100;
    Menu menu;
    Boolean isElementVisible;
    final static String DOG_KEY="DOG_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null) {
            isElementVisible = savedInstanceState.getBoolean(DOG_KEY,true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu=menu;
        SubMenu subMenuFile=menu.addSubMenu(R.string.subMenuFile);
        subMenuFile.add(IDM_GROUP, IDM_NEW, Menu.NONE, R.string.subMenuFileNew);
        subMenuFile.add(IDM_GROUP, IDM_OPEN, Menu.NONE, R.string.subMenuFileOpen);
        subMenuFile.add(IDM_GROUP,IDM_SAVE, Menu.NONE,R.string.subMenuFileSave).setIntent(new Intent(this,Main2Activity.class));
        subMenuFile.setGroupCheckable(IDM_GROUP, true, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        item.setChecked(!item.isChecked());
        return super.onOptionsItemSelected(item);
    }



    public void onSettingsMenuClick(MenuItem item) {
        TextView infoTextView=(TextView)findViewById(R.id.textView1);
        int id=item.getItemId();
        switch (id){
            case R.id.action_cat1:
                infoTextView.setText("You choose cat");
                break;
            case R.id.action_cat2:
                infoTextView.setText("You choose pussy cat");
                break;
            case R.id.action_cat3:
                infoTextView.setText("You choose kitten");
                break;
            default:
                infoTextView.setText("You choose something");
                break;

        }
    }

    public void onClick(View view) {
        boolean visibleTemp=false;
        if (isElementVisible!=null){
            visibleTemp=!isElementVisible;
        }
        isElementVisible=visibleTemp;
        menu.findItem(R.id.action_cat3).setVisible(visibleTemp);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isElementVisible!=null) {
            menu.findItem(R.id.action_cat3).setVisible(isElementVisible);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(DOG_KEY, menu.findItem(R.id.action_cat3).isVisible());
    }
}

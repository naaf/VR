package fr.dant.vr;





import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import fr.dant.vr.entity.Announce;


public class NewsFeed extends BasicToolBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_action_bar_toolbar);
        toolbar.setTitle("Vie Residentielle");

        setSupportActionBar(toolbar);

        ArrayList<Announce> androidList = new ArrayList<Announce>();

        initList(androidList);

        AnnounceAdopter adapter = new AnnounceAdopter(this, R.layout.list_announce, androidList);
        final ListView list = (ListView) findViewById(R.id.list_announce);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_basic_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    private void initList(ArrayList<Announce> androidList) {
        Announce announce1 = new Announce();
        announce1.setNom("Ashraf");
        announce1.setDate(new Date());
        announce1.setTitre("test");
        announce1.setCorp("Restabat ut Caesar post haec properaret accitus et abstergendae causa suspicionis sororem suam, eius uxorem, Constantius ad se tandem desideratam venire multis fictisque blanditiis hortabatur. quae licet ambigeret metuens saepe cruentum, spe tamen quod eum lenire poterit ut germanum profecta, cum Bithyniam introisset, in statione quae Caenos Gallicanos appellatur, absumpta est vi febrium repentina. cuius post obitum maritus contemplans cecidisse fiduciam qua se fultum existimabat, anxia cogitatione, quid moliretur haerebat.");

        androidList.add(announce1);

        Announce announce2 = new Announce();
        announce2.setNom("Ashraf");
        announce2.setDate(new Date());
        announce2.setTitre("test");
        announce2.setRole("Gardien");
        announce2.setCorp("Restabat ut Caesar post haec properaret accitus et abstergendae causa suspicionis sororem suam, eius uxorem, Constantius ad se tandem desideratam venire multis fictisque blanditiis hortabatur. quae licet ambigeret metuens saepe cruentum, spe tamen quod eum lenire poterit ut germanum profecta, cum Bithyniam introisset, in statione quae Caenos Gallicanos appellatur, absumpta est vi febrium repentina. cuius post obitum maritus contemplans cecidisse fiduciam qua se fultum existimabat, anxia cogitatione, quid moliretur haerebat.");
        androidList.add(announce2);

        Announce announce3 = new Announce();
        announce3.setNom("Ashraf");
        announce3.setDate(new Date());
        announce3.setTitre("test");
        announce3.setCorp("Restabat ut Caesar post haec properaret accitus et abstergendae causa suspicionis sororem suam, eius uxorem, Constantius ad se tandem desideratam venire multis fictisque blanditiis hortabatur. quae licet ambigeret metuens saepe cruentum, spe tamen quod eum lenire poterit ut germanum profecta, cum Bithyniam introisset, in statione quae Caenos Gallicanos appellatur, absumpta est vi febrium repentina. cuius post obitum maritus contemplans cecidisse fiduciam qua se fultum existimabat, anxia cogitatione, quid moliretur haerebat.");
        androidList.add(announce3);
    }
}

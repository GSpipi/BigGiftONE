package com.twogroup.biggift.main.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.lkp.PinterestFragment;
import com.twogroup.biggift.main.lkp.TitleHangFragment;

public class PinterestActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private NavigationView navigation;
    private ActionBarDrawerToggle toggle;
    private PinterestFragment pinterestFragment;
    private TitleHangFragment titleHangFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinterest);
        initView();


    }

    private void initView() {
        drawer = ((DrawerLayout) findViewById(R.id.id_drawer));
        navigation= (NavigationView) findViewById(R.id.id_navigation);
        navigation.setNavigationItemSelectedListener(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this,drawer,0,0);
        drawer.setDrawerListener(toggle);
        //同步状态
        toggle.syncState();
        pinterestFragment = new PinterestFragment();
        titleHangFragment = new TitleHangFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.id_content, pinterestFragment)
                .add(R.id.id_content, titleHangFragment)

                .detach(titleHangFragment)
                .attach(pinterestFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item) || toggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.pubuliu:
                fragmentTransaction.detach(titleHangFragment).attach(pinterestFragment);
                break;
            case R.id.xuangua:
                fragmentTransaction.detach(pinterestFragment).attach(titleHangFragment);
                break;

        }
        drawer.closeDrawer(Gravity.LEFT);
        fragmentTransaction.commit();
        return true;
    }
}

package com.synolexoil.synolexom;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class productsList extends AppCompatActivity {

    /*Appbar styling*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_products_list, menu);
        return true;
    }

    /*Appbar functioning*/
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.action_mainActivity:
                //Main activity intent launcher
                Intent intent = new Intent(productsList.this, MainActivity.class);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(menuItem);

        }
    }


    @Override
    protected void attachBaseContext(Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /*List Variables init*/
/*    private ListView m_listview;*/


    private List<Products> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        /*Appbar styling*/
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/

        /*Action bar set title*/
        getSupportActionBar().setTitle("لیست محصولات");
        /*Activity Layout Direction*/
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MoviesAdapter(movieList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Products movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getTitle() + " انتخاب شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }

    private void prepareMovieData() {
        Products movie = new Products("Ronak SAE 20W-50 API SG~1l", "", "985.473 ریال");
        movieList.add(movie);

        movie = new Products("Ronak SAE 20W-50 API SG~4L", "", "2.119.592 ريال");
        movieList.add(movie);

        movie = new Products("Ronak SAE 20W-50 API SG~208L", "", "13.726.139 ريال");
        movieList.add(movie);

        movie = new Products("Ronak SAE 25W-50 API SJ~4L", "", "2.409.359 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-50 API SG~3.5L", "", "2.001.086 ريال");
        movieList.add(movie);

        movie = new Products("Taxi SAE 20W-50 API SG~4.5L", "", "2.300.174 ريال");
        movieList.add(movie);

        movie = new Products("Taxi SAE 25W-50 API SJ~4.5L", "", "2.761.591 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 15W-40 CNG~4L", "", "2.773.947 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-40 CNG~4.5L", "", "3.149.879 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SL~1L", "", "1.668.203 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SL~3.5L", "", "2.395.805 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SL~4L", "", "2.926.306 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SL~208L", "", "16.966.111 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-50 API SL~1L", "", "1.261.081 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-50 API SL~4L", "", "2.633.195 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SM~1L", "", "2.260.760 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SM~4L", "", "4.366.134 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 10W-40 API SM~208L", "", "20.357.172 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-50 API SM~1L", "", "1.504.762 ريال");
        movieList.add(movie);

        movie = new Products("Arias SAE 20W-50 API SM~4L", "", "3.095.802 ريال");
        movieList.add(movie);

        movie = new Products("َRush SAE 5W-30 API SN~5L", "", "5.773.489 ريال");
        movieList.add(movie);

        movie = new Products("Rush SAE 5W-30 API SN~208L", "", "22.854.338 ريال");
        movieList.add(movie);

        movie = new Products("Diaco SAE 5W-30 API SN~1L", "", "4.790.739 ريال");
        movieList.add(movie);

        movie = new Products("Diaco SAE 5W-30 API SN~4L", "", "9.204.321 ريال");
        movieList.add(movie);

        movie = new Products("Rush SAE 5W-40 API SN~5L", "", "5.532.745 ريال");
        movieList.add(movie);

        movie = new Products("Rush SAE 5W-40 API SN~208L", "", "23.670.819 ريال");
        movieList.add(movie);

        movie = new Products("Diaco SAE 5W-40 API SN~1L", "", "4.721.469 ريال");
        movieList.add(movie);

        movie = new Products("Diaco SAE 5W-40 API SN~4L", "", "9.133.149 ريال");
        movieList.add(movie);

        movie = new Products("Motorcycle SAE 10W-40 JASO MA2~1L", "", "1.665.729 ريال");
        movieList.add(movie);

        movie = new Products("Motorcycle SAE 20W-50 JASO MA2~1L", "", "1.466.510 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 50 API CD~20L", "", "1.258.599 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 50 API CD~208L", "", "12.585.985 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 15W-40 API CH-4~20L", "", "1.688.207 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 15W-40 API CH-4~208L", "", "16.882.068 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 20W-50 API CH-4~20L", "", "1.682.763 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 20W-50 API CH-4~208L", "", "16.827.635 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 15W-40 API CI-4~20L", "", "1.776.694 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 15W-40 API CI-4~208L", "", "17.766.936 ريال");
        movieList.add(movie);

        movie = new Products("Ariso SAE 15W-40 API CI-4 Plus~20L", "", "1.956.524 ريال");
        movieList.add(movie);

        movie = new Products("Synolex AddBlue~20L", "", "331.982 ريال");
        movieList.add(movie);

        movie = new Products("Sahara UTTO 10W-30 GL-4~20L", "", "1.682.367 ريال");
        movieList.add(movie);

        movie = new Products("Synchro 75W-90 GL-5~1L", "", "2.452.833 ريال");
        movieList.add(movie);

        movie = new Products("Synchro 85W-90 GL-4~1L", "", "1.542.752 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic ATF DII~208L", "", "20.037.822 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic ATF DIII~1L", "", "1.856.474 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic ATF DIII~20L", "", "2.106.152 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic ATF DIII~208L", "", "20.350.913 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic SP III~1L", "", "1.891.836 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic SP IV~1L", "", "5.270.738 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic CVT~1L", "", "3.070.518 ريال");
        movieList.add(movie);

        movie = new Products("Fluidmatic Super CVT~1L", "", "5.262.191 ريال");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }



}


package com.udacity.example.gmailcopylayoutchallenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView icon = findViewById(R.id.empty_image_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        toolbar.setTitle("Inbox");

        icon.setVisibility(View.INVISIBLE);

        int[] image = new int[]{R.drawable.tj , R.drawable.g , R.drawable.u , R.drawable.tj , R.drawable.g , R.drawable.u , R.drawable.tj , R.drawable.g , R.drawable.u };
        String[] from = new String[]{"Tavish Jain" , "Google" , "Udacity" , "Tavish Jain" , "Google" , "Udacity" , "Tavish Jain" , "Google" , "Udacity" };
        String[] title = new String[]{"Test Title" , "Test Title" , "Test Title" , "Test Title" , "Test Title" , "Test Title" , "Test Title" , "Test Title" , "Test Title" , };
        String[] message = new String[]{"Test Message" , "Test Message" , "Test Message" , "Test Message" , "Test Message" , "Test Message" , "Test Message" , "Test Message" , "Test Message" , };
        String[] date = new String[]{"01 Apr" , "31 Mar" , "30 Mar" , "29 Mar" , "28 Mar" , "27 Mar" , "26 Mar" , "25 Mar" , "24 Mar" };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You clicked on FAB !!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        final RecyclerAdapter mAdapter = new RecyclerAdapter(this , from , title , message , date , image);
        mRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Paint p = new Paint();
                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0){
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        }).attachToRecyclerView(mRecyclerView);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_inbox) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Inbox");
            return true;
        }else if (id == R.id.action_starred) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Starred");
            return true;
        }else if (id == R.id.action_important) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Important");
            return true;
        }else if (id == R.id.action_sent) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Sent");
            return true;
        }else if (id == R.id.action_outbox) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Outbox");
            return true;
        }else if (id == R.id.action_drafts) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Drafts");
            return true;
        }else if (id == R.id.action_all_mail) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("All mail");
            return true;
        }else if (id == R.id.action_spam) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Spam");
            return true;
        }else if (id == R.id.action_bin) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Bin");
            return true;
        }else if (id == R.id.action_personal) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Personal");
            return true;
        }else if (id == R.id.action_receipts) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Receipts");
            return true;
        }else if (id == R.id.action_travel) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Travel");
            return true;
        }else if (id == R.id.action_work) {
            drawer.closeDrawer(GravityCompat.START);
            toolbar.setTitle("Work");
            return true;
        }else if (id == R.id.action_calendar) {
            drawer.closeDrawer(GravityCompat.START);
            Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_contacts) {
            drawer.closeDrawer(GravityCompat.START);
            Toast.makeText(this, "Contacts", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_settings) {
            drawer.closeDrawer(GravityCompat.START);
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_help_and_feedback) {
            drawer.closeDrawer(GravityCompat.START);
            Toast.makeText(this, "Help and Feedback", Toast.LENGTH_SHORT).show();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
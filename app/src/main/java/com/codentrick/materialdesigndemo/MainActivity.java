package com.codentrick.materialdesigndemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;

    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.content);

        list = new ArrayList<>();
        list.add(new Model("Item", "http://images.all-free-download.com/images/graphiclarge/pink_mallow_flower_flowers_220945.jpg"));
        list.add(new Model("Item", "http://magazine8.com/wp-content/uploads/2014/02/most-beautiful-flowers.jpg"));
        list.add(new Model("Item", "http://ohtoptens.com/wp-content/uploads/2015/06/Flower-Images-and-Wallpapers3.jpg"));
        list.add(new Model("Item", "https://pixabay.com/static/uploads/photo/2013/07/18/10/59/flowers-163717_640.jpg"));
        list.add(new Model("Item", "http://big-flowers.com/wp-content/uploads/2015/03/90urp1283y4fn.jpg"));
        list.add(new Model("Item", "http://anhdep.taothiep.vn/wallpaper-flower/Most-pretty-white-and-purple-flowers.jpg"));
        list.add(new Model("Item", "http://media02.hongkiat.com/ww-flower-wallpapers/blueflowers.jpg"));
        list.add(new Model("Item", "http://www.goodlightscraps.com/content/flowers/flower-scraps-51.jpg"));
        list.add(new Model("Item", "http://www.noupe.com/wp-content/uploads/trans/cdn_smash/wp-content/uploads/2010/01/flowers_69.jpg"));
        list.add(new Model("Item", "http://imagemania.in/wp-content/gallery/flower-images/60-beautiful-flower-pictures-15.jpg"));
        list.add(new Model("Item", "http://images6.fanpop.com/image/photos/32600000/Asiatic-Lily-Washington-Flower-Wallpaper-jpgbeautiful-flower-jpgbright-garden-jpgMost-Beautiful-Colo-flowers-32600563-500-375.jpg"));
        list.add(new Model("Item", "http://www.almanac.com/sites/new.almanac.com/files/sword%20lily.jpg"));
        list.add(new Model("Item", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg/1024px-Nelumno_nucifera_open_flower_-_botanic_garden_adelaide2.jpg"));

        initToolbar();
        initFAB();
        initDrawerLayout();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Material Design Demo");
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Snackbar.make(coordinatorLayout, "MenuItemClicked", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_menu_white_48dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initFAB() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initDrawerLayout() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(coordinatorLayout, menuItem.getTitle() + "pressed", Snackbar.LENGTH_SHORT).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, Model model) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(DetailActivity.MODLE_TEXT, model.getText());
                bundle.putString(DetailActivity.MODLE_IMAGE, model.getImage());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {

        private ArrayList<Model> models;
        private OnItemClickListener listener;

        public RecyclerViewAdapter(ArrayList<Model> models) {
            this.models = models;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler, viewGroup, false);
            v.setOnClickListener(this);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position) {
            Model item = models.get(position);
            viewHolder.text.setText(item.getText());
            viewHolder.image.setImageBitmap(null);
            Picasso.with(viewHolder.image.getContext()).load(item.getImage()).into(viewHolder.image);
            viewHolder.itemView.setTag(item);
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(view, (Model) view.getTag());
            }
        }

        public void setOnClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Model model);

    }
}

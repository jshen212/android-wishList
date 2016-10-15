package mywishlist.androidstudio.com.mywishlist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.MyWish;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class DisplayWishesActivity extends AppCompatActivity {
    private DatabaseHandler dba;
    private ArrayList<MyWish> dbWishes = new ArrayList<>();
    private WishAdapter wishAdapter;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wishes);

        listView = (ListView) findViewById(R.id.list);

        refreshData();
    }

    private void refreshData() {
        dbWishes.clear();
        dba = new DatabaseHandler(getApplicationContext());

        ArrayList<MyWish> wishesFromDB = dba.getWishes();

        for(int i = 0; i < wishesFromDB.size(); i++) {
            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContent();

            MyWish myWish = new MyWish();
            myWish.setTitle(title);
            myWish.setContent(content);
            myWish.setRecordDate(dateText);

            dbWishes.add(myWish);
        }
        dba.close();

        // setup adapter
        wishAdapter = new WishAdapter(DisplayWishesActivity.this, R.layout.wish_row, dbWishes);
        listView.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();
    }

    public class WishAdapter extends ArrayAdapter<MyWish> {
        Activity activity;
        int layoutResource;
        MyWish wish;
        ArrayList<MyWish> mData = new ArrayList<>();

        public WishAdapter(Activity act, int resource, ArrayList<MyWish> data) {
            super(act, resource, data);
            activity = act;
            layoutResource = resource;
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public int getPosition(MyWish item) {
            return super.getPosition(item);
        }

        @Override
        public MyWish getItem(int position) {
            return mData.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder = null;

            if( row == null || (row.getTag()) == null) {
                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResource, null);
                holder = new ViewHolder();
                holder.mTitle = (TextView) row.findViewById(R.id.name);
                holder.mDate = (TextView) row.findViewById(R.id.dateText);

                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.myWish = getItem(position);
            holder.mTitle.setText(holder.myWish. getTitle());
            holder.mDate.setText(holder.myWish.getRecordDate());

            return row;
        }

        class ViewHolder {
            MyWish myWish;
            TextView mTitle;
            TextView mId;
            TextView mContent;
            TextView mDate;
        }
    }


}

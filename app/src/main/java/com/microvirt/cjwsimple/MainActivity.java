package com.microvirt.cjwsimple;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.microvirt.cjwsimple.asop.HandlerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private RecyclerView main_rv;
    private List<Class> list;
    private MainRVAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        adapter = new MainRVAdapter(list, this);
        main_rv = (RecyclerView) findViewById(R.id.main_rv);
        main_rv.setLayoutManager(new LinearLayoutManager(this));
        main_rv.setAdapter(adapter);

        initData();
        startActivity(new Intent(this, HandlerActivity.class));
    }

    private void initData() {
        File file = new File(".");
        Log.e("Andy", file.getName()+", absolute path:"+file.getAbsolutePath());
        if (file.isDirectory()){
            for (File tmp : file.listFiles()){
                if (tmp.getName().endsWith("Activity")){
                    list.add(tmp.getClass());
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private static class MainRVViewHolder extends RecyclerView.ViewHolder{
        private TextView item_main;
        public MainRVViewHolder(View itemView) {
            super(itemView);
            item_main = (TextView) itemView.findViewById(R.id.main_rv_item);
        }
    }

    private static class MainRVAdapter extends RecyclerView.Adapter<MainRVViewHolder>{

        private LayoutInflater inflater;
        private List<Class> list;
        private Context context;

        public MainRVAdapter(List<Class> list, Context context) {
            inflater = LayoutInflater.from(context);
            this.list = list;
            this.context = context;
        }

        @Override
        public MainRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_main_rv, parent, false);
            return new MainRVViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainRVViewHolder holder, final int position) {
            holder.item_main.setText(list.get(position).getName());
            holder.item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}

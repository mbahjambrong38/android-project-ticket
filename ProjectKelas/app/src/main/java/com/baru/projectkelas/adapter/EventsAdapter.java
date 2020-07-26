package com.baru.projectkelas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baru.projectkelas.R;
import com.baru.projectkelas.db.db;

import java.util.Vector;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventsAdapter extends BaseAdapter {

    Context ctx;

    public static Vector<Integer> eventId;
    public static Vector<String> judul;
    public static Vector<String> date;
    public static Vector<String> time ;
    public static Vector<String> deskripsi;
    public static Vector<String> category;
    public static Vector<Integer> gambar;

    public EventsAdapter(Context ctx, String category) {
        this.ctx = ctx;

        eventId = new Vector<>();
        judul = new Vector<>();
        date = new Vector<>();
        time = new Vector<>();
        deskripsi = new Vector<>();
        this.category = new Vector<>();
        gambar = new Vector<>();

        for (int i = 0; i < db.VEC_EVENT.size(); i++){
            if (category.equals(db.VEC_EVENT.get(i).category)){
                eventId.add(db.VEC_EVENT.get(i).eventId);
                judul.add(db.VEC_EVENT.get(i).judul);
                date.add(db.VEC_EVENT.get(i).date);
                time.add(db.VEC_EVENT.get(i).time);
                deskripsi.add(db.VEC_EVENT.get(i).deskripsi);
                this.category.add(db.VEC_EVENT.get(i).category);
                gambar.add(db.VEC_EVENT.get(i).gambar);
            }
        }
    }

    @Override
    public int getCount() {
        return judul.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_events, null);

        CircleImageView imgEvent = view.findViewById(R.id.img_circle_img);
        TextView txtJudul = view.findViewById(R.id.txt_judul_event);
        TextView txtDate = view.findViewById(R.id.txt_date_event);

        imgEvent.setImageResource(gambar.get(i));
        txtJudul.setText(judul.get(i));
        txtDate.setText(date.get(i));

        return view;
    }
}

package com.baru.projectkelas.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baru.projectkelas.R;
import com.baru.projectkelas.db.db;

import org.w3c.dom.Text;

import java.util.Vector;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends BaseAdapter {
    Context ctx;
    int memberId;

    public static Vector<Integer> V_CONFIRMATION_ORDER_ID;
    public static Vector<Integer> V_MEMBER_ID;
    public static Vector<Integer> V_ORDER_ID;
    public static Vector<String> V_JUDUL_EVENT;
    public static Vector<Integer> V_PRICE;
    public static Vector<String> V_DATE;
    public static Vector<Integer> V_QTY;
    public static Vector<String> V_STATUS;
    public static Vector<Integer> V_IMG;

    public HistoryAdapter(Context ctx, int memberId) {
        this.ctx = ctx;
        this.memberId = memberId;
        int eventId = 0;

        V_MEMBER_ID = new Vector<>();
        V_CONFIRMATION_ORDER_ID = new Vector<>();
        V_ORDER_ID = new Vector<>();
        V_JUDUL_EVENT = new Vector<>();
        V_PRICE = new Vector<>();
        V_DATE = new Vector<>();
        V_STATUS = new Vector<>();
        V_QTY = new Vector<>();
        V_IMG = new Vector<>();

        for (int i = 0; i < db.VEC_ORDER.size(); i++){
            if (db.VEC_ORDER.get(i).memberId == memberId) {
                int checkConf = 0;
                V_ORDER_ID.add(db.VEC_ORDER.get(i).orderId);
                V_MEMBER_ID.add(db.VEC_ORDER.get(i).memberId);
                V_PRICE.add(db.VEC_ORDER.get(i).price);
                V_QTY.add(db.VEC_ORDER.get(i).qty);

                for (int ii = 0; ii < db.VEC_CONFIRMATION.size(); ii++){
                    if (db.VEC_ORDER.get(i).orderId == db.VEC_CONFIRMATION.get(ii).orderId) {
                        checkConf = 1;
                        break;
                    }else {
                        checkConf = 0;
                    }
                }

                if (checkConf == 0) {
                    V_STATUS.add("UNCOMPLETED");
                }else {
                    V_STATUS.add("COMPLETED");
                }

                for (int ii = 0; ii < db.VEC_PRODUCT.size(); ii++){
                    if (db.VEC_PRODUCT.get(ii).productId == db.VEC_ORDER.get(i).productId) {
                        eventId = db.VEC_PRODUCT.get(ii).eventId;
                        for (int iii = 0; iii < db.VEC_EVENT.size(); iii++){
                            if (eventId == db.VEC_EVENT.get(iii).eventId){
                                V_DATE.add(db.VEC_EVENT.get(iii).date);
                                V_JUDUL_EVENT.add(db.VEC_EVENT.get(iii).judul);
                                V_IMG.add(db.VEC_EVENT.get(iii).gambar);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getCount() {
        return V_ORDER_ID.size();
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
        view = inflater.inflate(R.layout.list_history, null);

        for (int ii = 0; ii < V_IMG.size(); ii++){
            Log.d("TEMPAT GAMBAR", String.format("%d", V_IMG.get(ii)));
        }
        Log.d("VECTOR GAMBAR", String.format("%d", V_IMG.size()));

        TextView txtJudul = view.findViewById(R.id.txt_judul_history);
        TextView txtDate = view.findViewById(R.id.txt_date_history);
        TextView txtStatus = view.findViewById(R.id.txt_status_history);
        TextView txtPrice = view.findViewById(R.id.txt_price_history);
        TextView txtQty = view.findViewById(R.id.txt_qty_history);
        CircleImageView img = view.findViewById(R.id.img_circle_img_history);

        img.setImageResource(V_IMG.get(i));
        txtJudul.setText(V_JUDUL_EVENT.get(i));
        txtDate.setText(V_DATE.get(i));
        txtPrice.setText(V_PRICE.get(i).toString());
        txtStatus.setText(V_STATUS.get(i));
        txtQty.setText(V_QTY.get(i).toString());

        return view;
    }
}

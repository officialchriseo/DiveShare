package ng.com.blogspot.httpofficialceo.diveshare;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//import ng.com.blogspot.httpofficialceo.diveshare.adapter.SMSListAdapter;
import ng.com.blogspot.httpofficialceo.diveshare.model.SMSData;


public class MessagesFragment extends Fragment {

//    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
//
//    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);
       // getAllSMSLog();


        return rootView;

    }

//    public void getAllSMSLog(){
//        List<SMSData> smsList = new ArrayList<SMSData>();
//
//        Uri uri = Uri.parse("content://sms/inbox");
//
//        Cursor c = getActivity().getContentResolver().query(uri, null, null, null, null);
//
//        getActivity().startManagingCursor(c);
//
//        if (c.moveToFirst()){
//            for (int i = 0; i < c.getCount(); i++){
//                SMSData sms = new SMSData();
//                sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
//                sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
//                smsList.add(sms);
//                c.moveToNext();
//            }
//        }
//        c.close();
//
//        setListAdapter(new SMSListAdapter(getContext(), smsList));
//
//
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//
//        SMSData sms = (SMSData) getListAdapter().getItem(position);
//
//        Toast.makeText(getActivity().getApplicationContext(), sms.getBody(), Toast.LENGTH_SHORT).show();
//    }
}

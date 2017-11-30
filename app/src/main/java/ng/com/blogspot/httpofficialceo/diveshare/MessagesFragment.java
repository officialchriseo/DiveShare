package ng.com.blogspot.httpofficialceo.diveshare;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MessagesFragment extends Fragment {

//    Context context = getActivity().getApplicationContext();

    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
//                Manifest.permission.READ_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanatio
//
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale( (Activity) getContext().getApplicationContext(),
//                    Manifest.permission.READ_SMS)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions((Activity) getActivity().getApplicationContext(),
//                        new String[]{Manifest.permission.READ_SMS},
//                        MY_PERMISSIONS_REQUEST_READ_SMS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//
//
//        ListView smsList = (ListView) container.findViewById(R.id.messages_list);
//
//
//        if (fetchInbox() != null){
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, fetchInbox());
//            smsList.setAdapter(adapter);
//        }

        return inflater.inflate(R.layout.fragment_messages, container, false);

    }

//    public ArrayList<String> fetchInbox(){
//
//        ArrayList<String> sms = new ArrayList<String>();
//
//        Uri uri = Uri.parse("content://sms/inbox");
//
//        try {
//
//            Cursor cursor = (Cursor) getActivity().getApplicationContext().getContentResolver()
//                    .query(uri, new String[]{"id", "address", "date", "body"}, null, null, null);
//
//        } catch (Exception e){
//
//        }
//
//        cursor.moveToFirst();
//
//        while (cursor.moveToFirst()){
//            String address = cursor.getString(1);
//            String body = cursor.getString(3);
//
//            sms.add("Address => " + address + "\n Sms => " + body);
//        }
//
//        return sms;
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_SMS: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }
//

}

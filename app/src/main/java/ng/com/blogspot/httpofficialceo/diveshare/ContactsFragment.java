package ng.com.blogspot.httpofficialceo.diveshare;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;


public class ContactsFragment extends Fragment {

    private String personName;
    private String personNumber;


    public static final int RequestPermissionCode = 1;
    Toast myToast;
    ListView listView;
    ArrayList<String> contactName = new ArrayList<String>();
    ArrayList<String> contactNumber = new ArrayList<String>();
    MyAdapter ma;
    Cursor phones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        getAllCallLogs(getActivity().getContentResolver());

        listView = (ListView) rootView.findViewById(R.id.listview1);

        //onScrollStateChanged();

        ma = new MyAdapter();
        listView.setAdapter(ma);
        //  listView.setOnItemClickListener(this);
        listView.setItemsCanFocus(false);
        listView.setTextFilterEnabled(true);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });


        return rootView;


    }

    public void getAllCallLogs(ContentResolver cr) {

        try {
            phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        } catch (SecurityException e) {

        }

        if (phones != null) {
            try {
                HashSet<String> normalizedNumbersAlreadyFound = new HashSet<>();
                int indexOfNormalizedNumber = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER);
                int indexOfDisplayName = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int indexOfDisplayNumber = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                while (phones.moveToNext()) {

                    String normalizedNumber = phones.getString(indexOfNormalizedNumber);
                    if (normalizedNumbersAlreadyFound.add(normalizedNumber)) {
                        String displayName = phones.getString(indexOfDisplayName);
                        String displayNumber = phones.getString(indexOfDisplayNumber);

                        contactName.add(displayName);
                        contactNumber.add(displayNumber);
                    } else {

                    }

                }
            } finally {
                phones.close();
            }

        }

    }


//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//        if (position == 0){
//            cb.setChecked(false);
//        }else{
//            cb.setChecked(true);
//        }
//
////        ma.toggle(position);
//
//    }

    public void onScrollStateChanged(AbsListView view, int scrollState) {
        FloatingActionButton btn =  view.findViewById(R.id.fab);
        int btn_initPosY = btn.getScrollY();
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
            btn.animate().cancel();
            btn.animate().translationYBy(150);
        } else {
            btn.animate().cancel();
            btn.animate().translationY(btn_initPosY);
        }
    }

    private static class Holder {
        TextView tv1, tv2;
        CheckBox cb;
        CircleImageView contactImage;
        CardView contactsCard;

    }

    private class MyAdapter extends BaseAdapter implements
            CompoundButton.OnCheckedChangeListener {

        LayoutInflater mInflater;
        //  ArrayList<Boolean> positionArray;
        private SparseBooleanArray mCheckState;


        MyAdapter() {
            mCheckState = new SparseBooleanArray(contactName.size());
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            positionArray = new ArrayList<Boolean>(contactName.size());
//            for (int i = 0; i < contactName.size(); i++){
//                positionArray.add(false);
//            }
        }

        @Override
        public int getCount() {
            return contactName.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            Holder holder;

            View vi = convertView;

            if (convertView == null)
                vi = mInflater.inflate(R.layout.contact_items_listview, null);

            holder = new Holder();

            holder.tv1 =  vi.findViewById(R.id.contact_name);
            holder.tv2 =  vi.findViewById(R.id.contact_number);
            holder.contactImage =  vi.findViewById(R.id.contact_image);
            holder.contactsCard =  vi.findViewById(R.id.contact_card_view);
            holder.cb =  vi.findViewById(R.id.checkBox1);

            vi.setTag(holder);
            holder = (Holder) vi.getTag();
            holder.cb.setOnCheckedChangeListener(this);
            holder.cb.setFocusable(false);
            holder.tv1.setText(contactName.get(position));
            holder.tv2.setText(contactNumber.get(position));
            holder.cb.setTag(position);
            holder.cb.setChecked(mCheckState.get(position, false));
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String number;
                    String name;
                    CheckBox checkBox = (CheckBox) buttonView;
                    if (isChecked){
                        number = contactNumber.get(position).toString();
                        name = contactName.get(position).toString();
                    }


                  //  mCheckState.put((Integer) buttonView.getTag(), isChecked);

                }
            });

            holder.contactsCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("DIALER");
                    alertDialog.setMessage("Permission to proceed calling " + contactName.get(position).toUpperCase() + " ?");
                    alertDialog.setIcon(R.drawable.ic_phone_in_talk_black_24dp);

                    alertDialog.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            String number = "tel:" + contactNumber.get(position);
                            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
                            getContext().startActivity(callIntent);

                        }
                    });

                    alertDialog.setNegativeButton("RETURN", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (myToast != null) {
                                myToast.cancel();
                            }
                            myToast = Toast.makeText(getContext(), "Action aborted!", Toast.LENGTH_SHORT);
                            myToast.show();
                            return;
                        }
                    });
                    alertDialog.show();

                }
            });




            return vi;
        }

        public boolean isChecked(int position) {

            return mCheckState.get(position, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckState.put(position, isChecked);
        }

        public void toggle(int position) {
            setChecked(position, !isChecked(position));
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        }

    }

}


//    FragmentManager fragmentManager = getActivity().getFragmentManager();
//    FragmentTransaction transaction = fragmentManager.beginTransaction();
//                           transaction.replace(R.id.main_content, new ContactBarCodeFragment(),"uyyuyfyfu");
//                                   transaction.addToBackStack(null);
//                                   transaction.commit();

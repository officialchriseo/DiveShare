package ng.com.blogspot.httpofficialceo.diveshare;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class ContactsFragment extends Fragment {

    ListView listView ;
    ArrayList<String> contactName = new ArrayList<String>();
    ArrayList<String> contactNumber= new ArrayList<String>();
    MyAdapter ma;
    CheckBox cb;
    Cursor phones;

    public  static final int RequestPermissionCode  = 1 ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        getAllCallLogs(getActivity().getContentResolver());

        listView = (ListView) rootView.findViewById(R.id.listview1);

        ma = new MyAdapter();
        listView.setAdapter(ma);
      //  listView.setOnItemClickListener(this);
        listView.setItemsCanFocus(false);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        return rootView;


    }

    public void getAllCallLogs(ContentResolver cr){


        try {
             phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        }catch (SecurityException e){

        }

        if (phones != null){
            try {
                HashSet<String> normalizedNumbersAlreadyFound = new HashSet<>();
                int indexOfNormalizedNumber = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER);
                int indexOfDisplayName = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int indexOfDisplayNumber = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                while (phones.moveToNext()){

                    String normalizedNumber = phones.getString(indexOfNormalizedNumber);
                    if (normalizedNumbersAlreadyFound.add(normalizedNumber)){
                        String displayName = phones.getString(indexOfDisplayName);
                        String displayNumber = phones.getString(indexOfDisplayNumber);

                        contactName.add(displayName);
                        contactNumber.add(displayNumber);
                    }else{

                    }

                }
            }finally {
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

    class MyAdapter extends BaseAdapter{

        private SparseBooleanArray mCheckState;
        LayoutInflater mInflater;
        TextView tv1, tv2;

        Button confirmShareButton;
        CircleImageView contactImage;
        CardView contactsCard;

        MyAdapter(){
            mCheckState = new SparseBooleanArray(contactName.size());
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

            View vi = convertView;

            if (convertView == null)
                vi = mInflater.inflate(R.layout.contact_items_listview, null);

            tv1 = (TextView) vi.findViewById(R.id.contact_name);
            tv2 = (TextView) vi.findViewById(R.id.contact_number);
            contactImage = (CircleImageView) vi.findViewById(R.id.contact_image);
            // confirmShareButton = (Button) vi.findViewById(R.id.confirm_share_button);
            contactsCard = (CardView) vi.findViewById(R.id.contact_card_view);
            cb = (CheckBox) vi.findViewById(R.id.checkBox1);
            tv1.setText(contactName.get(position));
            tv2.setText(contactNumber.get(position));
           contactsCard.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   cb.setTag(position);
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

//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            mCheckState.put((Integer) buttonView.getTag(), isChecked);
//        }

    }
}




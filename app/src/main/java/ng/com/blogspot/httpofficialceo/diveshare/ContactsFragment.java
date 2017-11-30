package ng.com.blogspot.httpofficialceo.diveshare;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactsFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView ;
    ArrayList<String> contactName = new ArrayList<String>();
    ArrayList<String> contactNumber= new ArrayList<String>();
    MyAdapter ma;
   // ArrayAdapter<String> arrayAdapter ;

    String name, phonenumber ;
    public  static final int RequestPermissionCode  = 1 ;

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        getAllCallLogs(getActivity().getContentResolver());

        listView = (ListView) rootView.findViewById(R.id.listview1);

        ma = new MyAdapter();
        listView.setAdapter(ma);
        listView.setOnItemClickListener(this);
        listView.setItemsCanFocus(false);
        listView.setTextFilterEnabled(true);






        return rootView;


    }

    public void getAllCallLogs(ContentResolver cr){
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        while (phones.moveToNext()){
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contactName.add(name);
            contactNumber.add(phoneNumber);
        }

        phones.close();
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



//        ma.toggle(position);

    }

    class MyAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{

        private SparseBooleanArray mCheckState;
        LayoutInflater mInflater;
        TextView tv1, tv2;
        CheckBox cb;
        Button confirmShareButton;
        CircleImageView contactImage;

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
        public View getView(int position, View convertView, ViewGroup parent) {

            View vi = convertView;

            if (convertView == null)
                vi = mInflater.inflate(R.layout.contact_items_listview, null);

            tv1 = (TextView) vi.findViewById(R.id.contact_name);
            tv2 = (TextView) vi.findViewById(R.id.contact_number);
            contactImage = (CircleImageView) vi.findViewById(R.id.contact_image);
           // confirmShareButton = (Button) vi.findViewById(R.id.confirm_share_button);
            cb = (CheckBox) vi.findViewById(R.id.checkBox1);
            tv1.setText(contactName.get(position));
            tv2.setText(contactNumber.get(position));
            cb.setTag(position);

//            if (cb.isChecked()){
//                confirmShareButton.setVisibility(View.VISIBLE);
//            }else{
//                confirmShareButton.setVisibility(View.GONE);
//            }

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
            mCheckState.put((Integer) buttonView.getTag(), isChecked);
        }
    }
}


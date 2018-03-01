package ng.com.blogspot.httpofficialceo.diveshare;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ebongcreative.max.ssp.SSP;
import ebongcreative.max.ssp.SharedPreferenceStorage;
import ng.com.blogspot.httpofficialceo.diveshare.adapter.ContactAdapter;
import ng.com.blogspot.httpofficialceo.diveshare.model.ContactModel;


public class ContactsFragment extends Fragment {

    public static final int RequestPermissionCode = 1;
    Toast myToast;
    ListView listView;
    ArrayList<String> contactName = new ArrayList<String>();
    ArrayList<String> contactNumber = new ArrayList<String>();
    ArrayList<String> selectedItems = new ArrayList<>();
    MyAdapter ma;
    Cursor phones;
    SSP simplePreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        getAllCallLogs(getActivity().getContentResolver());

        listView = (ListView) rootView.findViewById(R.id.listview1);

        ma = new MyAdapter();
        listView.setAdapter(ma);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);
        listView.setTextFilterEnabled(true);


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


    private static class Holder {
        TextView tv1, tv2, contText;
       // CheckBox cb;
        CircleImageView contactImage;
        CardView contactsCard;
        ImageView iconImp;

    }

    private class MyAdapter extends BaseAdapter implements
            CompoundButton.OnCheckedChangeListener {

        LayoutInflater mInflater;
        //  ArrayList<Boolean> positionArray;
        private SparseBooleanArray mCheckState;


        MyAdapter() {
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

            Holder holder;

            View vi = convertView;

            if (convertView == null)
                vi = mInflater.inflate(R.layout.contact_items_listview, null);

            holder = new Holder();

            holder.tv1 = vi.findViewById(R.id.contact_name);
            holder.tv2 = vi.findViewById(R.id.contact_number);
            holder.contactImage = vi.findViewById(R.id.contact_image);
            holder.contactsCard = vi.findViewById(R.id.contact_card_view);
            holder.contText = vi.findViewById(R.id.cont_text);
            holder.iconImp = vi.findViewById(R.id.icon_star);
            holder.iconImp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getContext(), BarCodeActivity.class);
                    myIntent.putExtra("CONTACT_NAME", contactName.get(position));
                    myIntent.putExtra("CONTACT_NUMBER", contactNumber.get(position));
                    getContext().startActivity(myIntent);
                }
            });
            holder.contText.setText(contactName.get(position).substring(0,1));
            //holder.iconText.setText(contacts.getName().substring(0, 1));


            vi.setTag(holder);
            holder = (Holder) vi.getTag();
            holder.tv1.setText(contactName.get(position));
            holder.tv2.setText(contactNumber.get(position));


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

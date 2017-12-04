//package ng.com.blogspot.httpofficialceo.diveshare.adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//import ng.com.blogspot.httpofficialceo.diveshare.R;
//import ng.com.blogspot.httpofficialceo.diveshare.model.SMSData;
//
///**
// * Created by official on 12/2/17.
// */
//
//public class SMSListAdapter extends ArrayAdapter<SMSData> {
//    private final Context context;
//    private final List<SMSData> smsList;
//
//    public SMSListAdapter (Context context, List<SMSData> smsList){
//        super(context, R.layout.fragment_messages, smsList);
//        this.context = context;
//        this.smsList = smsList;
//    }
//
//
//    @Override
//    public View getView(int position, View convertView,  ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View rowView = inflater.inflate(R.layout.fragment_messages, parent, false);
//        TextView senderNumber = (TextView) rowView.findViewById(R.id.smsNumberText);
//        senderNumber.setText(smsList.get(position).getNumber());
//        return rowView;
//    }
//}

package ng.com.blogspot.httpofficialceo.diveshare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ng.com.blogspot.httpofficialceo.diveshare.R;
import ng.com.blogspot.httpofficialceo.diveshare.model.ContactModel;

/**
 * Created by official on 12/1/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<ContactModel> contactModels;

    private Context mContext;
    public ContactAdapter(List<ContactModel> contactModels, Context mContext){
        this.contactModels = contactModels;

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_items_listview, null);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final ContactModel contactModel = contactModels.get(position);
        holder.tvContactName.setText(contactModel.getContactName());
        holder.tvPhoneNumber.setText(contactModel.getContactPhoneNumber());
      //  holder.iconText.setText(contactModel.getContactName().substring(0, 1));
       // holder.checkBox.setOnCheckedChangeListener(null);



    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{


        CircleImageView ivContactImage;
        TextView tvContactName;
        TextView tvPhoneNumber;
        CheckBox checkBox;
        TextView iconText;

        public ContactViewHolder(View itemView) {

            super(itemView);
          //  ivContactImage = (CircleImageView) itemView.findViewById(R.id.contact_image);
            tvContactName = (TextView) itemView.findViewById(R.id.contact_name);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.contact_number);
           //checkBox = (CheckBox) itemView.findViewById(R.id.checkBox1);
           // iconText = (TextView) itemView.findViewById(R.id.icon_text);




        }
    }
}

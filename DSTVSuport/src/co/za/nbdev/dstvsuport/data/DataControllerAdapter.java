package co.za.nbdev.dstvsuport.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.za.nbdev.dstvsuport.ContactInfo;
import co.za.nbdev.dstvsuport.R;

import com.bontsi.ngdevelopmentframework.loactions.LocationUtility;
import com.bontsi.ngdevelopmentframework.presenters.IFragmentPresenter;

/**
 * Created by kaede on 2015/10/15.
 */
public class DataControllerAdapter extends
		RecyclerView.Adapter<DataControllerAdapter.ViewHolder> {
	private final IFragmentPresenter dataColler;
	List<ContactInfo> datas = new ArrayList<ContactInfo>();
	Context context;

	private final String PROVIDER = "Service Provider: ";
	private final String CITY = "City: ";
	private final String TOWN = "Town: ";
	private final String MOBILE_NUMBER = "Mobile No: ";
	private final String TELEPHON_NUMBER = "Telephone: ";
	private final String PHYSICAL_ADDRESS = "Physical Address";
	private final String CONTACT_PERSON = "Contact Person";
	private final String EMAIL = "E-Mail: ";

	public DataControllerAdapter(IFragmentPresenter iFragmentPresenter,
			Context context) {
		dataColler = iFragmentPresenter;
		this.context = context;
	}

	public void setDatas(List<ContactInfo> datas) {
		if (datas != null && datas.size() > 0) {
			this.datas.clear();
			this.datas.addAll(datas);
			notifyDataSetChanged();
		}
	}

	public ContactInfo getItem(int position) {
		return datas.get(position);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View v = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.fragment_navigation_drawer, parent, false);
		final ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		try {
			holder.vProvider.setText(PROVIDER + getDistance(position));
			holder.vProvider.setText(PROVIDER
					+ datas.get(position).getServiceProvider());
			holder.vCity.setText(CITY + datas.get(position).getCity());
			holder.vTown.setText(TOWN + datas.get(position).getTown());
			holder.vMobile.setText(MOBILE_NUMBER
					+ datas.get(position).getMobileNumber());
			holder.vTelephone.setText(TELEPHON_NUMBER
					+ datas.get(position).getTelephone());
			holder.vAddress.setText(PHYSICAL_ADDRESS
					+ datas.get(position).getPhysicalAddress());
			holder.vContactPerson.setText(CONTACT_PERSON
					+ datas.get(position).getContactPerson());
			holder.vEmail.setText(EMAIL + datas.get(position).getEmail());
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dataColler.onItemClick(position);
				}
			});

		} catch (final Exception e) {
			e.getStackTrace();
		}

	}

	private String getDistance(int position) {
		// TODO Auto-generated method stub
		final LocationUtility utility = new LocationUtility(context);
		final String location = datas.get(position).getPhysicalAddress();
		final List<Address> address = utility.getAddress(location);
		return null;
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		protected TextView vDistance;
		protected TextView vProvider;
		protected TextView vCity;
		protected TextView vTown;
		protected TextView vMobile;
		protected TextView vTelephone;
		protected TextView vAddress;
		protected TextView vContactPerson;
		protected TextView vEmail;

		public ViewHolder(View v) {
			super(v);
			vProvider = (TextView) v.findViewById(R.id.txtDistance);
			vProvider = (TextView) v.findViewById(R.id.txtProvider);
			vCity = (TextView) v.findViewById(R.id.txtCity);
			vTown = (TextView) v.findViewById(R.id.txtTown);
			vMobile = (TextView) v.findViewById(R.id.txtMobile);
			vTelephone = (TextView) v.findViewById(R.id.txtTelephone);
			vAddress = (TextView) v.findViewById(R.id.txtAddress);
			vContactPerson = (TextView) v.findViewById(R.id.txtContactPerson);
			vEmail = (TextView) v.findViewById(R.id.txtEmail);
		}
	}

}

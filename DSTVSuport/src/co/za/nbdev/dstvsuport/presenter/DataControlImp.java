package co.za.nbdev.dstvsuport.presenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import co.za.nbdev.dstvsuport.ContactInfo;

import com.bontsi.ngdevelopmentframework.presenters.IFragmentPresenter;
import com.bontsi.ngdevelopmentframework.utils.ExcelUtil;
import com.bontsi.ngdevelopmentframework.view.IFragmentView;

public class DataControlImp implements IFragmentPresenter<ContactInfo> {

	private final IFragmentView iFragmentView;
	private final String HEADINGS = "Service Provider Name";

	public DataControlImp(IFragmentView iFragmentView) {
		this.iFragmentView = iFragmentView;
	}

	@Override
	public void loadOders(ContactInfo item) {
		final List<ContactInfo> list = new ArrayList<ContactInfo>();
		final ExcelUtil excelUtil = new ExcelUtil(iFragmentView.getActivity()
				.getApplicationContext(), "Installer Information.xls");
		final HSSFSheet sheet = excelUtil.getExcelSheet(0);
		if (sheet != null) {

			final Iterator sheetIterator = sheet.rowIterator();

			while (sheetIterator.hasNext()) {
				final ContactInfo info = new ContactInfo();
				final HSSFRow row = (HSSFRow) sheetIterator.next();
				if (!row.getCell(0).toString().equals(HEADINGS)) {

					info.setServiceProvider(row.getCell(0).toString());
					info.setTown(row.getCell(1).toString());
					info.setCity(row.getCell(2).toString());
					info.setMobileNumber(row.getCell(3).toString());
					info.setTelephone(row.getCell(4).toString());
					info.setPhysicalAddress(row.getCell(5).toString());
					info.setContactPerson(row.getCell(6).toString());
					info.setEmail(row.getCell(13).toString());
					list.add(info);
				}
			}
		}
		iFragmentView.onLoadData(list);

	}

	@Override
	public void onItemClick(int position) {
		iFragmentView.onItemClick(position);

	}

	@Override
	public void screenActionEvent(String screen, ContactInfo item) {
		// TODO Auto-generated method stub

	}

}

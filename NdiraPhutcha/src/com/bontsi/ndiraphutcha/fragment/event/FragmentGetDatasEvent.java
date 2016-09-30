package com.bontsi.ndiraphutcha.fragment.event;

import java.util.List;

import com.bontsi.ndiraphutcha.tables.OrderDetails;

/**
 * Created by kaede on 2015/10/13.
 */
public class FragmentGetDatasEvent {
	List<OrderDetails> datas;

	public FragmentGetDatasEvent(List<OrderDetails> datas) {
		this.datas = datas;
	}

	public List<OrderDetails> getDatas() {
		return datas;
	}
}

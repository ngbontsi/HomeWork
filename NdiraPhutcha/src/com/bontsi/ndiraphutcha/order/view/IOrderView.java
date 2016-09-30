package com.bontsi.ndiraphutcha.order.view;

/**
 * Created by kaede on 2015/5/18.
 */
public interface IOrderView {
	public void onClearText();

	public void onMakeAnOrderResult(Boolean result, int code);

	public void onSetProgressBarVisibility(int visibility);

	public void toast(String msg);
}

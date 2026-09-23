package com.example.designpatterns.adapter.objectdapter;

//适配器类
public class VoltageAdapter  implements IVoltage5V {
	private Voltage220V voltage220V;//降低耦合程度，以聚合的方式进行适配
	public VoltageAdapter(Voltage220V voltage220V){
		this.voltage220V=voltage220V;
	}

	@Override
	public int output5V() {
		// TODO Auto-generated method stub
		//获取到220V电压
		int srcV = voltage220V.output220V();
		int dstV = srcV / 44 ; //转成 5v
		return dstV;
	}

}

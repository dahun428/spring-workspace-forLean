package kr.co.jhta.di.service.step6;

import kr.co.jhta.di.service.Output;

public class Reporter {

	private Output output;
	
	public Reporter() {}
	
	
	public Reporter(Output output2) {
		this.output = output2;
	}

	public void setOutput(Output output) {
		this.output = output;
	}
	
	
	public void report(String title, String text) {
		System.out.println("output객체 ----> " + output);
		output.write(title);
		output.write(text);
	}
}

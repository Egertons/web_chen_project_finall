package com.example.mvc.framework.model;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private String view;
	private Map<String,Object> model;
	public ModelAndView(String v){
		this.view=v;
		this.model=new HashMap<String,Object>();
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public void addObject(String key,Object value){
		this.model.put(key, value);
	}
	public Map<String, ?> getModel() {
		return model;
	}

}

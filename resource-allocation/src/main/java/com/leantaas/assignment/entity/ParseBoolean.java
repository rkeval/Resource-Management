package com.leantaas.assignment.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParseBoolean {

private boolean result;

public ParseBoolean(boolean result) {
	this.result=result;
}

public boolean getBooleanValue() {
	return result;
}

public void setBooleanValue(boolean booleanValue) {
	this.result = booleanValue;
}

public ParseBoolean() {
}


}
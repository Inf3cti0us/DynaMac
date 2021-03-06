/******************************************************
* Created by Marneus901                                *
* � 2012 MarneusScripts.com                            *
* **************************************************** *
* Access to this source is unauthorized without prior  *
* authorization from its appropriate author(s).        *
* You are not permitted to release, nor distribute this* 
* work without appropriate author(s) authorization.    *
********************************************************/
package org.dynamac.bot.api.wrappers;

import org.dynamac.enviroment.Data;
import org.dynamac.enviroment.hook.ClassHook;
import org.dynamac.enviroment.hook.FieldHook;


public class NPCDef {
	public Object currentObject;
	private ClassHook currentHook;
	public NPCDef(Object o){
		currentObject=o;
		currentHook = Data.indentifiedClasses.get("NPCDef");
	}
	public String[] getActions(){
		FieldHook fh = currentHook.getFieldHook("getActions");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (String[])data;
		}
		return null;
	}
	public int getID(){
		FieldHook fh = currentHook.getFieldHook("getID");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (Integer)data * fh.getMultiplier();
		}
		return -1;
	}
	public String getName(){
		FieldHook fh = currentHook.getFieldHook("getName");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return data.toString();
		}
		return "";
	}
	public HashTable getNodeTable(){
		FieldHook fh = currentHook.getFieldHook("getNodeTable");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return new HashTable(data);
		}
		return null;
	}
}

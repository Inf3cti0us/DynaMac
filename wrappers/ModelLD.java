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

import org.dynamac.bot.api.methods.Calculations;
import org.dynamac.enviroment.Data;
import org.dynamac.enviroment.hook.ClassHook;
import org.dynamac.enviroment.hook.FieldHook;


public class ModelLD extends Model{
	public Object currentObject;
	public ClassHook currentHook;
	public ModelLD(Object o){
		super(o);
		currentObject = o;
		currentHook = Data.indentifiedClasses.get("ModelLD");
	}
	
/*    public Point getCenter(){
        int minX = 9999999;
        int minY = 9999999;
        
        int maxX = -9999999;
        int maxY = -9999999;
        
    	int maxZ = -99999999, minZ = 99999999;
   
    	maxX = getVerticiesX()[0];
    	minX = getVerticiesX()[0];
    	for(int i = 0; i < getVerticiesX().length; i++) {
    		if(getVerticiesX()[i] > maxX)
    			maxX = getVerticiesX()[i];
    		else if(getVerticiesX()[i] < minX)
    			minX = getVerticiesX()[i];
    	}
    	maxY = getVerticiesY()[0];
    	minY = getVerticiesY()[0];
    	for(int i = 0; i < getVerticiesY().length; i++) {
    		if(getVerticiesY()[i] > maxY)
    			maxY = getVerticiesY()[i];
    		else if(getVerticiesY()[i] < minY)
    			minY = getVerticiesY()[i];
    	}
    	
    	maxZ = getVerticiesZ()[0];
    	minZ = getVerticiesZ()[0];
    	for(int i = 0; i < getVerticiesZ().length; i++) {
    		if(getVerticiesZ()[i] > maxZ)
    			maxZ = getVerticiesZ()[i];
    		else if(getVerticiesZ()[i] < minZ)
    			minZ = getVerticiesZ()[i];
    	}
    	
    	System.out.println("MinX" + minX);
    	System.out.println("MaxX" + maxX);

    	System.out.println("MinY" + minY);
    	System.out.println("MaxY" + maxY);
    	
        int deltaX = (maxX-minX) >> 1;
        int deltaY = (maxY-(-minY)) >> 1;
        int deltaZ = (maxZ-minZ) >> 1;

        return new Point(minX+deltaX, minY+deltaY);    
    }*/
	
	public int[] getVerticiesX(){
		FieldHook fh = currentHook.getFieldHook("getVerticiesX");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (int[])data;
		}
		return new int[]{};
	}
	public int[] getVerticiesY(){
		FieldHook fh = currentHook.getFieldHook("getVerticiesY");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (int[])data;
		}
		return new int[]{};
	}
	public int[] getVerticiesZ(){
		FieldHook fh = currentHook.getFieldHook("getVerticiesZ");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (int[])data;
		}
		return new int[]{};
	}
	public short[] getTriangleX(){
		FieldHook fh = currentHook.getFieldHook("getTriangleX");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (short[])data;
		}
		return new short[]{};
	}
	public short[] getTriangleY(){
		FieldHook fh = currentHook.getFieldHook("getTriangleY");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (short[])data;
		}
		return new short[]{};
	}
	public short[] getTriangleZ(){
		FieldHook fh = currentHook.getFieldHook("getTriangleZ");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (short[])data;
		}
		return new short[]{};
	}
	public short[] getTriangleColor(){
		FieldHook fh = currentHook.getFieldHook("getTriangleColor");
		if(fh!=null){
			Object data = fh.getData(currentObject);
			if(data!=null)
				return (short[])data;
		}
		return new short[]{};
	}
	public int[][] projectVertices(int localX, int localY) {
		if(Calculations.matrixCache.length==0)
			return new int[][]{{}};
		float[] data = Calculations.matrixCache;
		double locX = (localX+0.5)*512;
		double locY = (localY+0.5)*512;
		int numVertices = Math.min(getVerticiesX().length, Math.min(getVerticiesY().length, getVerticiesZ().length));
		int[][] screen = new int[numVertices][3];

		float xX = data[0];
		float yX = data[1];
		//2 same as 3 practically
		float zX = data[3];
		float xY = data[4];
		float yY = data[5];
		//6 same as 7 practically
		float zY = data[7];
		float xZ = data[8];
		float yZ = data[9];
		//10 same as 11
		float zZ = data[11];
		float xOff = data[12];
		float yOff = data[13];
		//14 same as 15
		float zOff = data[15];

		int height = Calculations.tileHeight((int)locX, (int)locY);
		for (int index = 0; index < numVertices; index++) {
			int vertexX = (int) (getVerticiesX()[index] + locX);
			int vertexY = getVerticiesY()[index] + height;
			int vertexZ = (int) (getVerticiesZ()[index] + locY);
			
			float _z = (zOff + (zX * vertexX + zY * vertexY + zZ * vertexZ));
			float _x = (xOff + (xX * vertexX + xY * vertexY + xZ * vertexZ));
			float _y = (yOff + (yX * vertexX + yY * vertexY + yZ * vertexZ));

			float fx = ((float)256.0 + ((float)256.0 * _x) / _z);
			float fy = ((float)166.0 + ((float)167.0 * _y) / _z);
			if(fx<520 && fx>0 && fy<390 && fy>50){
				screen[index][0] = (int)fx;
				screen[index][1] = (int)fy;
				screen[index][2] = 1;
			}
			else{
				screen[index][0] = -1;
				screen[index][1] = -1;
				screen[index][2] = 0;
			}
		}
		return screen;
	}
}

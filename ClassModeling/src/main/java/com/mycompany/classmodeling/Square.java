
package com.mycompany.classmodeling;


public class Square {
    private float sideLength;
    private float area;
    private float edgeWidth;
    private boolean solidFill;
    private String color;
    
    public Square (Float sideLength, String color){
        this.sideLength=sideLength;
        this.color=color;
        
        
    }

    /**
     * @return the sideLength
     */
    public float getSideLength() {
        return sideLength;
    }

    /**
     * @param sideLength the sideLength to set
     */
    public void setSideLength(float sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(float area) {
        this.area = area;
    }

    /**
     * @return the edgeWidth
     */
    public float getEdgeWidth() {
        return edgeWidth;
    }

    /**
     * @param edgeWidth the edgeWidth to set
     */
    public void setEdgeWidth(float edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

    /**
     * @return the solidFill
     */
    public boolean isSolidFill() {
        return solidFill;
    }

    /**
     * @param solidFill the solidFill to set
     */
    public void setSolidFill(boolean solidFill) {
        this.solidFill = solidFill;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /* POSSIBLE BEHAVIORS and METHODS:
-Find size
-privide color
-privide area of square
*/
    
    
            }

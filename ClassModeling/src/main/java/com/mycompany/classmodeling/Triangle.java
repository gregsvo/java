
package com.mycompany.classmodeling;

import com.sun.prism.shader.Solid_TextureFirstPassLCD_Loader;
import javafx.scene.layout.BorderWidths;


public class Triangle {
    private Float sideLength;
    private Float borderWidth;
    private boolean solidFill;
    private String color;
    
    public Triangle(Float sideLength, Float borderWidth, boolean solidFill, String color){
        
    }

    /**
     * @return the sideLength
     */
    public Float getSideLength() {
        return sideLength;
    }

    /**
     * @param sideLength the sideLength to set
     */
    public void setSideLength(Float sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * @return the borderWidth
     */
    public Float getBorderWidth() {
        return borderWidth;
    }

    /**
     * @param borderWidth the borderWidth to set
     */
    public void setBorderWidth(Float borderWidth) {
        this.borderWidth = borderWidth;
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
-privide area of triangle
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

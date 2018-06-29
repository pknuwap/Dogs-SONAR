package screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicComponent{
    private float alpha=1f;
    private String text="";
    private Font font=new Font("Consolas",Font.PLAIN,20);
    private Color textColor=Color.WHITE;
    private int id=-1,x=0,y=0,width=10,height=10;
    private BufferedImage img;
    private boolean isClickable=false;
    public boolean isClickable() {
        return isClickable;
    }
    public void setClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void setImg(String imgSrc){
        this.img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D bGr = getImg().createGraphics();
        try {
            bGr.drawImage(ImageIO.read(new File(imgSrc)).getScaledInstance(getWidth(), getHeight(),Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bGr.dispose();
    }
    public void setImg(BufferedImage target) {
        this.img=target;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Font getFont() {
        return font;
    }
    public void setFont(Font font) {
        this.font = font;
    }
    public Color getTextColor() {
        return textColor;
    }
    public float getAlpha() {
        return alpha;
    }
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    public void setAlpha(float alpha) {
        if(alpha>1) {
            alpha=1f;
        }else if(alpha<0) {
            alpha=0f;
        }
        this.alpha = alpha;
    }
}
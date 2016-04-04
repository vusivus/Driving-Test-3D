/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.factory;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.ui.Picture;

/**
 *
 * @author GameDev
 */
public class GuiData {

   public static String pic_one = "Textures/numbers/one.png";
   public static String pic_two = "Textures/numbers/two.png";
   public static String pic_three = "Textures/numbers/three.png";
   public static String pic_go = "Textures/numbers/go.png";
   public static String pic_speedometer = "Textures/speedometer.dds";
   public static String pic_frame = "Textures/frame.png";
    
   public static final String LAP_TEXT = "lap";
   public  static final String SPEED_TEXT = "speed";
   public  static final String TIME_TEXT = "time";
   public  static final String POSITION_TEXT = "position";
   public  static final String LAP_TIME_TEXT = "lap-time";
   public  static final String COUNT_TEXT = "count-dwon";
   public  static final String POINTS_TEXT = "points";

    public static Picture getPic(String name, AssetManager assetManager, Camera cam) {
        Picture pic = null;
        if (name.equals(pic_one)) {
            pic = new Picture("one");
            pic.setImage(assetManager, pic_one, true);
            pic.setHeight(cam.getHeight() * .25f);
            pic.setWidth(cam.getWidth() * .25f);
            pic.setPosition(cam.getWidth() * .4f, cam.getHeight() * .5f);
        }
        if (name.equals(pic_two)) {
            pic = new Picture("two");
            pic.setImage(assetManager, pic_two, true);
            pic.setHeight(cam.getHeight() * .25f);
            pic.setWidth(cam.getWidth() * .25f);
            pic.setPosition(cam.getWidth() * .4f, cam.getHeight() * .5f);
        }

        if (name.equals(pic_three)) {
            pic = new Picture("three");
            pic.setImage(assetManager, pic_three, true);
            pic.setHeight(cam.getHeight() * .25f);
            pic.setWidth(cam.getWidth() * .25f);
            pic.setPosition(cam.getWidth() * .4f, cam.getHeight() * .5f);
        }
        if (name.equals(pic_go)) {
            pic = new Picture("go");
            pic.setImage(assetManager, pic_go, true);
            pic.setHeight(cam.getHeight() * .25f);
            pic.setWidth(cam.getWidth() * .25f);
            pic.setPosition(cam.getWidth() * .4f, cam.getHeight() * .5f);
        }
        if (name.equals(pic_speedometer)) {
            pic = new Picture("speed");
            pic.setImage(assetManager, pic_speedometer, true);
            pic.setHeight(cam.getHeight() * .2f);
            pic.setWidth(cam.getWidth() * .2f);
            pic.setPosition(cam.getWidth() * .8f, cam.getHeight() * .01f);
        }
        if (name.equals(pic_frame)) {
            pic = new Picture("speed");
            pic.setImage(assetManager, pic_frame, true);
            pic.setHeight(cam.getHeight() * .1f);
            pic.setWidth(cam.getWidth());
            pic.setPosition(0, cam.getHeight() * .9f);
        }
        return pic;
    }

    public static BitmapText getText(String name, Camera cam, BitmapFont font) {
        BitmapText text = null;
        if (LAP_TEXT.equals(name)) {
            text = new BitmapText(font, false);
            text.setSize(28);
            text.setColor(ColorRGBA.White);
            text.setLocalTranslation(new Vector3f(.75f * cam.getWidth(), .95f * cam.getHeight(), 0));
        }
        if (name.equals(SPEED_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(16);
            text.setColor(ColorRGBA.Cyan);
            text.setLocalTranslation(new Vector3f(.87f * cam.getWidth(), .085f * cam.getHeight(), 0));

        }
        if (name.equals(TIME_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(24);
            text.setColor(ColorRGBA.White);
            text.setLocalTranslation(new Vector3f(.35f * cam.getWidth(), .95f * cam.getHeight(), 0));
        }
        if (name.equals(POSITION_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(24);
            text.setColor(ColorRGBA.White);
            text.setLocalTranslation(new Vector3f(.05f * cam.getWidth(), .95f * cam.getHeight(), 0));
        }
        if (name.equals(LAP_TIME_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(24);
            text.setColor(ColorRGBA.Yellow);
            text.setLocalTranslation(new Vector3f(.03f * cam.getWidth(), .90f * cam.getHeight(), 0));
        }
        if (name.equals(COUNT_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(20);
            text.setColor(ColorRGBA.Cyan);
            text.setLocalTranslation(new Vector3f(.1f * cam.getWidth(), .5f * cam.getHeight(), 0));
        }
        if (name.equals(POINTS_TEXT)) {
            text = new BitmapText(font, false);
            text.setSize(18);
            text.setColor(ColorRGBA.White);
            text.setLocalTranslation(new Vector3f(.03f * cam.getWidth(), .1f * cam.getHeight(), 0));
        }
        return text;
    }
}

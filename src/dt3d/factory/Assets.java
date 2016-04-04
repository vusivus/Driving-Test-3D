/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.factory;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Spatial;

/**
 *
 * @author GameDev
 */
public class Assets {
 
    public static Spatial getCar(String name,AssetManager assetManager){
    Spatial spatial = null;
     if(name.equals("car_01")){
         spatial = assetManager.loadModel(AssetsData.car_01);
     }
     
     if(name.equals("car_02")){
         spatial =   assetManager.loadModel(AssetsData.car_02);      
     } 
     
     if(name.equals("car_03")){
         spatial =  assetManager.loadModel(AssetsData.car_03);     
     } 
      
    if(name.equals("car_04")){
         spatial =  assetManager.loadModel(AssetsData.car_04);      
     }  
     if(name.equals("car_05")){
         spatial =  assetManager.loadModel(AssetsData.car_05);
        
     }  
   if(name.equals("car_06")){
         spatial =  assetManager.loadModel(AssetsData.car_06);
        
     } 
        return spatial;
    }
    
public static Spatial getTrack(int level,AssetManager assetManager){
      Spatial track = null;
       if(level==1){
        track =  assetManager.loadModel(AssetsData.blues);
      }   
 return track;
 }

}

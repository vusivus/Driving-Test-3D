/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.factory;

import com.jme3.cinematic.MotionPath;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sanet
 */
public class MotionPaths {
    public MotionPath getPath(int num){
     MotionPath path = new MotionPath();
         if(num==3){       
                path=new MotionPath();
     
    path.addWayPoint(new Vector3f(-36.3f,0f,35f));
     path.addWayPoint(new Vector3f(-25.8f,0f,49.1f));
         path.addWayPoint(new Vector3f(-5f,0f,48.7f));
     path.addWayPoint(new Vector3f(27f,0f,47.5f));
     path.addWayPoint(new Vector3f(38.4f,0f,40f));
     path.addWayPoint(new Vector3f(42.4f,0f,13.2f));
     path.addWayPoint(new Vector3f(17.8f,0f,2.8f));
     path.addWayPoint(new Vector3f(13.3f,0f,25f));
       path.addWayPoint(new Vector3f(2.5f,0f,31.7f));
    path.addWayPoint(new Vector3f(-13.3f,0f,27.3f));
     path.addWayPoint(new Vector3f(-21.7f,-0f,4.3f));
     path.addWayPoint(new Vector3f(6.3f,0f,-6.5f));
       path.addWayPoint(new Vector3f(23.3f,0f,-10.6f));
     path.addWayPoint(new Vector3f(28.4f,-0f,-23.3f));
     path.addWayPoint(new Vector3f(22.2f,-0f,-45.8f));
      path.addWayPoint(new Vector3f(-13.9f,-0f,-40f));
     path.addWayPoint(new Vector3f(-27f,0f,-32f)); 
       path.addWayPoint(new Vector3f(-41.1f,0f,-35.2f));
      path.addWayPoint(new Vector3f(-45.4f,0f,-26.7f));   
      path.addWayPoint(new Vector3f(-40.2f,0f,-13.7f));
     
         }
       if(num==1){    
     path.addWayPoint(new Vector3f(-214f,-5f,-86f));
         path.addWayPoint(new Vector3f(-170f,-13f,-50f));
     path.addWayPoint(new Vector3f(-97f,-23f,-8f));
     path.addWayPoint(new Vector3f(90f,-9f,24f));
     path.addWayPoint(new Vector3f(152f,-2f,16f));
     path.addWayPoint(new Vector3f(212f,1.5f,38f));
       path.addWayPoint(new Vector3f(308f,-2f,125f));
    path.addWayPoint(new Vector3f(370f,-4f,224f));
     path.addWayPoint(new Vector3f(515f,-3f,247f));
     path.addWayPoint(new Vector3f(682f,0f,200f));
       path.addWayPoint(new Vector3f(970f,-1.5f,126f));
     path.addWayPoint(new Vector3f(1056f,-1.7f,40f));
     path.addWayPoint(new Vector3f(1078f,1.5f,-207));
      path.addWayPoint(new Vector3f(1054f,4.5f,-398f));
     path.addWayPoint(new Vector3f(964f,1.2f,-560f));
     path.addWayPoint(new Vector3f(873f,0.2f,-639f));
     path.addWayPoint(new Vector3f(740f,-1f,-629f));
      path.addWayPoint(new Vector3f(524f,-4f,-563f)); 
      path.addWayPoint(new Vector3f(387f,-3.3f,-633f));
      path.addWayPoint(new Vector3f(202f,-2f,-788f));
      path.addWayPoint(new Vector3f(196f,-4f,-788f));
         path.addWayPoint(new Vector3f(-115f,0f,-779f));
         path.addWayPoint(new Vector3f(-268f,0f,-706f));
         path.addWayPoint(new Vector3f(-314f,0.2f,-674f));
     path.addWayPoint(new Vector3f(-337f,-1.3f,-608f));
     path.addWayPoint(new Vector3f(-353f,1.2f,-368f));
     path.addWayPoint(new Vector3f(-383f,1f,-228f));
     path.addWayPoint(new Vector3f(-325f,0f,-184f));
     path.addWayPoint(new Vector3f(-283f,-1.7f,-147f));
       }
     if(num==2){       
  path=new MotionPath();
          path.addWayPoint(new Vector3f(-1,0.15f,61));        
        path.addWayPoint(new Vector3f(-30,0.15f,83));
          path.addWayPoint(new Vector3f(-49,0.15f,75));
         path.addWayPoint(new Vector3f(-62,0.15f,52));
         path.addWayPoint(new Vector3f(-45,0.15f,19));
           path.addWayPoint(new Vector3f(-22,0.15f,-1));
            path.addWayPoint(new Vector3f(-24,0.15f,-13));
         path.addWayPoint(new Vector3f(-65,0.15f,-41)); 
         path.addWayPoint(new Vector3f(-71,0.15f,-62));
           path.addWayPoint(new Vector3f(-69,0.15f,-87));
         path.addWayPoint(new Vector3f(-21,0.15f,-116)); 
         path.addWayPoint(new Vector3f(56,.15f,-103)); 
          path.addWayPoint(new Vector3f(84,0.15f,-68));
            path.addWayPoint(new Vector3f(94,0.15f,51));
             path.addWayPoint(new Vector3f(84,0.15f,73));
          path.addWayPoint(new Vector3f(60,0.15f,87));
           path.addWayPoint(new Vector3f(34,0.15f,74));
            path.addWayPoint(new Vector3f(25,0.15f,51));  
          path.addWayPoint(new Vector3f(25,0.15f,-72)); 
           path.addWayPoint(new Vector3f(-17,0.15f,-85));
           path.addWayPoint(new Vector3f(-31,0.15f,-55));
           path.addWayPoint(new Vector3f(-24,0.15f,-30)); 
             path.addWayPoint(new Vector3f(-5,0.15f,-20));
           path.addWayPoint(new Vector3f(1,0.15f,-6)); 
           path.addWayPoint(new Vector3f(.6f,0.15f,28.1f)); 
         
     }
     
        return path;
    }
}

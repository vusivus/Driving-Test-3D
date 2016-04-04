/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.control.vehicle;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.cinematic.MotionPath;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import java.util.concurrent.Future;
import dt3d.factory.GameFactory;
import dt3d.factory.GuiData;
import dt3d.math.GameMath;

/**
 *
 * @author Vusman
 */
public class CheckPointManager extends AbstractAppState {

    public CheckPointManager(VehicleControl vehicleControl, MotionPath path, Spatial vehicle) {
        this.vehicle = vehicleControl;
        this.path = path;
        maxCheckPoints = this.path.getNbWayPoints();
        currentCheckPoint = 1;
        currentWayPoint = 0;
        currentLap = 0;
        lapNumber = 2;
        auto = vehicle.getControl(AutomaticVehicleControl.class);
    }
    //---- path parameters ------------------    
    float checkRadius = 8;
    int lapNumber;
    int currentCheckPoint;
    public int currentWayPoint;
    int currentLap;
    int nextWayPoint;
    int maxCheckPoints;
    float distance;
    Future locFuture = null;
    boolean moving = true;
    VehicleControl vehicle;
    MotionPath path;
    BitmapText laptime;
    AutomaticVehicleControl auto;
    GameMath math = new GameMath();
    Vector3f targetLoc = new Vector3f();
    Vector3f currentLoc = new Vector3f();
//==============================================================================

    public void setCheckRadius(float checkRadius) {
        this.checkRadius = checkRadius;
        /*
         * Is 4 by default ,depends on the scaling of your world objects
         */
    }
//==============================================================================

    public Integer getMaxCheckPoints() {
        return maxCheckPoints;
    }
//==============================================================================

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        SimpleApplication simpleApp = (SimpleApplication) app;
        BitmapFont guiFont = simpleApp.getAssetManager().loadFont("Interface/Fonts/menu_button.fnt");
        //------ Gui Text ----------------
        GameFactory factory = new GameFactory();
        laptime = GuiData.getText("laptime", simpleApp.getCamera(), guiFont);
        simpleApp.getGuiNode().attachChild(laptime);
    }
//==============================================================================

    @Override
    public void update(float tpf) {
        //---- get the vehcile's current location ----------------------------------    
        currentLoc = vehicle.getPhysicsLocation();
        targetLoc = path.getWayPoint(currentWayPoint);
        //--- calculate distance to next checkpoint -----------------------------------
        distance = currentLoc.subtract(targetLoc).length();

        //------- check if checkpoint reached -----------------------------------------       
        if (distance < checkRadius && moving) { //check point reached and laps not finished
            //--- current check point is not last checkpoint --------------------------
            if (currentWayPoint != maxCheckPoints - 1) {
                ++currentWayPoint;
                ++currentCheckPoint;
                laptime.setText("");
                laptime.setText("" + currentWayPoint);
                auto.setIndex(currentWayPoint);
            }

            if (currentWayPoint == maxCheckPoints - 1) { //if last checkpoint

                if (currentLap != lapNumber) {  //if not last lap
                    currentWayPoint = 0;
                    currentCheckPoint = 1;
                    ++currentLap;
                } else if (currentLap == lapNumber) {   // last lap finished
                    moving = false;
                }
            }

        }
    }
//========= define number of laps ==============================================

    public void setLapNumber(int lapNumber) {
        this.lapNumber = lapNumber;
    }
//==============================================================================

    @Override
    public void cleanup() {
    }
//==============================================================================

    public int getCurrentLap() {
        return currentLap;
    }
//==============================================================================

    public int getNextWayPoint() {
        nextWayPoint = currentWayPoint + 1;
        if (nextWayPoint == maxCheckPoints - 1) {
            nextWayPoint = 0;
        }
        return nextWayPoint;
    }
//==============================================================================
    public int getCurrentWayPoint() {
        return this.currentWayPoint;
    }
//==============================================================================
    public int getCurrentCheckPoint() {
        return currentCheckPoint;
    }
//==============================================================================
    public float getDistance() {
        return this.distance;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.vehicle;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.Trigger;

/**
 *
 * @author GameDev
 */
public class CarInput extends AbstractAppState implements ActionListener {

    public static String steer_acceleration = "accelerate";
    public static String steer_left = "left";
    public static String steer_right = "right";
    public static String steer_reverse = "reverse";
    public static String steer_brake = "brake";
    public static String reset_car = "reset";
    boolean accelerating, high;
//----- vehicle paramenters --------------------------------------
    float steer_angle = 0.125f, steeringValue;
    float accelerationValue, accelerationForce = 50f;
    float carSpeed;
    String carName;
//------ key triggers  --------------------------------------------
    Trigger acc_key = new KeyTrigger(KeyInput.KEY_UP);
    Trigger brake_key = new KeyTrigger(KeyInput.KEY_DOWN);
    Trigger left_key = new KeyTrigger(KeyInput.KEY_LEFT);
    Trigger right_key = new KeyTrigger(KeyInput.KEY_RIGHT);
    Trigger reverse_key = new KeyTrigger(KeyInput.KEY_Z);
//---------- vehicle sounds ---------------------------------------
    AudioNode onHigh, brake, turn, offHigh, onLow, offLow, idle;
//-------- other classes -------------------------------------------
    InputManager inputManager;
    SimpleApplication app;
    VehicleControl car_con;
    AssetManager assetManager;

//==============================================================================
    public CarInput(String carName, VehicleControl car_con) {
        this.carName = carName;
        this.car_con = car_con;
    }
//==============================================================================    

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        inputManager = this.app.getInputManager();
        this.assetManager = this.app.getAssetManager();
        initSounds();
        initKeys();
    }

    public void initKeys() {
        inputManager.addMapping(CarInput.steer_acceleration, acc_key);
        inputManager.addMapping(CarInput.steer_brake, brake_key);
        inputManager.addMapping(CarInput.steer_left, left_key);
        inputManager.addMapping(CarInput.steer_right, right_key);
        inputManager.addMapping(CarInput.reset_car, new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping(CarInput.steer_reverse, reverse_key);
        inputManager.addListener(this, new String[]{
            CarInput.reset_car, CarInput.steer_acceleration, CarInput.steer_brake,
            CarInput.steer_left, CarInput.steer_reverse, CarInput.steer_right
        });
    }

    public void initSounds() {
        onHigh = new AudioNode(assetManager, "Sounds/engine/" + carName + "/onhigh.wav", false);
        onHigh.setLooping(true);
        offHigh = new AudioNode(assetManager, "Sounds/engine/" + carName + "/offhigh.wav", false);
        offHigh.setLooping(true);
        onLow = new AudioNode(assetManager, "Sounds/engine/" + carName + "/onlow.wav", false);
        onLow.setLooping(true);
        offLow = new AudioNode(assetManager, "Sounds/engine/" + carName + "/offlow.wav", false);
        offLow.setLooping(true);
        idle = new AudioNode(assetManager, "Sounds/engine/" + carName + "/idle.wav", false);
        turn = new AudioNode(assetManager, "Sounds/turn.wav");
        brake = new AudioNode(assetManager, "Sounds/brake.wav");
    }

    @Override
    public void update(float tpf) {
        carSpeed = car_con.getCurrentVehicleSpeedKmHour();

        if (carSpeed > 0 && carSpeed < 80) {
            high = false;
        }
        if (carSpeed >= 80) {
            high = true;
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        try{
       offLow.stop();
           offHigh.stop();
           onHigh.stop();
           onLow.stop();
   }
       catch(Exception x){
           
       } 
        inputManager.removeListener(this);
    }

    public void onAction(String name, boolean isPressed, float tpf) {
        if (name.equals(CarInput.steer_left)) {
            if (isPressed) {
                steeringValue += steer_angle;

            } else {

                steeringValue -= steer_angle;
            }
            car_con.steer(steeringValue);
        }
        if (name.equals(CarInput.steer_right)) {
            if (isPressed) {

                steeringValue -= steer_angle;
            } else {

                steeringValue += steer_angle;
            }
            car_con.steer(steeringValue);
        }
        if (name.equals(CarInput.steer_brake)) {
            if (isPressed) {
                car_con.brake(0);
            }
        }

        if (name.equals(CarInput.steer_acceleration)) {
            if (isPressed) {
                accelerationValue += (accelerationForce);
                accelerating = true;
            } else {
                accelerationValue -= accelerationForce;
                accelerating = false;
            }
            car_con.accelerate(accelerationValue);
        }
        if (name.equals(CarInput.reset_car)) {
            if (isPressed) {
            }
        }
    }
    private AnalogListener analogue = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("acc")) {
                if (accelerating) {

                    if (high) {
                        onHigh.play();
                        try {
                            offHigh.stop();
                            onLow.stop();
                        } catch (Exception e) {
                        };
                    } else if (!high) {
                        onLow.play();
                        try {
                            offLow.stop();
                            onHigh.stop();
                        } catch (Exception e) {
                        };
                    }

                } else if (!accelerating) {

                    if (high) {
                        offHigh.play();
                        try {
                            onHigh.stop();
                            offLow.stop();
                        } catch (Exception e) {
                        };
                    } else if (!high) {
                        offLow.play();
                        try {
                            offHigh.stop();
                            onLow.stop();
                        } catch (Exception e) {
                        };
                    }

                }


            }
            if (name.equals("left") || name.equals("right")) {
                if (value > .25f) {
                    turn.playInstance();
                }
            }
        }
    };
}

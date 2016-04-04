/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.game;

import dt3d.vehicle.CarInput;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.Timer;
import com.jme3.ui.Picture;
import dt3d.controls.screen.StartScreenControllerState;
import dt3d.factory.Assets;
import dt3d.factory.GameFactory;
import dt3d.factory.GuiData;

/**
 *
 * @author GameDev
 */
public class Test extends AbstractAppState {
//--------- player data --------------------------------

    String playerName;
    String carName;
    Node player;
    VehicleControl player_con;
    CarInput input;
    ChaseCamera chase;
    int gameLevel;
//--------- world data ------------------------------------
    DirectionalLight light;
    Spatial racingTrack, start;
    AmbientLight sun;
    RigidBodyControl track_con;
    boolean countDone, gameFinished;
//-------- gui data -----------------------------------------
    BitmapFont guiFont;
    BitmapText timeText, speedText, laptime, locText, countText, posText, ptText;
    ViewPort guiViewPort;
    Picture one, two, three, go, speedo, frame;
//------- Simple app classes -------------------------------------  
    SimpleApplication app;
    AssetManager assetManager;
    InputManager inputManager;
    Camera cam;
    AppStateManager stateManager;
    BulletAppState physics;
    Timer timer;
//-------- sr3d classes --------------------------------- 
    StartScreenControllerState screenState;
//-------- local nodes ---------------------------------------
    Node localRootNode = new Node("gameRoot");
    Node localGuiNode = new Node("gameGui");
    Node rootNode, guiNode;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;
        initPointers();         // Declare local app pointers
        initScene();            // Load racing track
        initGuiDisplay();       // plac gui content
        initPlayer(carName);    // load player car 
        attachObjects();        // place loaded objects in scene 
        timer.reset();          // restart the timer
    }
//============================ Declare Local App Pointers ======================

    public void initPointers() {
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.guiNode = this.app.getGuiNode();
        this.inputManager = this.app.getInputManager();
        this.cam = this.app.getCamera();
        this.timer = this.app.getTimer();
        this.guiViewPort = this.app.getGuiViewPort();
        this.inputManager = this.app.getInputManager();
        rootNode.attachChild(localRootNode);
        guiNode.attachChild(localGuiNode);
//========== Local class pointers ==============
        physics = this.stateManager.getState(BulletAppState.class);
        screenState = new StartScreenControllerState();

//====== Reset certain variables ================
        countDone = false;
    }
//==============================================================================

    public void initGuiDisplay() {
        guiFont = assetManager.loadFont("Interface/Fonts/menu_button.fnt");
        //------ Gui Text ----------------
        laptime = GuiData.getText(GuiData.LAP_TIME_TEXT, cam, guiFont);  //Text for displaying current lap
        timeText = GuiData.getText(GuiData.TIME_TEXT, cam, guiFont);
        speedText = GuiData.getText(GuiData.SPEED_TEXT, cam, guiFont);
        locText = GuiData.getText(GuiData.LAP_TEXT, cam, guiFont);
        countText = GuiData.getText(GuiData.COUNT_TEXT, cam, guiFont);
        posText = GuiData.getText(GuiData.POSITION_TEXT, cam, guiFont);
        ptText = GuiData.getText(GuiData.POSITION_TEXT, cam, guiFont);
        //----Gui pictures -------------------
        speedo = GuiData.getPic(GuiData.pic_speedometer, assetManager, cam);
        localGuiNode.attachChild(timeText);
        localGuiNode.attachChild(speedText);

        //------- Count down pictures ---------- 
        one = GuiData.getPic(GuiData.pic_one, assetManager, cam);
        two = GuiData.getPic(GuiData.pic_two, assetManager, cam);
        three = GuiData.getPic(GuiData.pic_three, assetManager, cam);
        go = GuiData.getPic(GuiData.pic_go, assetManager, cam);
    }
//========= Loads the player vehicle ===========================================

    public void initPlayer(String car) {
        player = (Node) Assets.getCar(car, assetManager);
        player.setName("player");
        player.setUserData("level", gameLevel);
        player.setUserData("speed", 100f);
        player.setLocalTranslation(GameFactory.getStartPoint(gameLevel));
        player_con = player.getControl(VehicleControl.class);
        player_con.setPhysicsLocation(GameFactory.getStartPoint(gameLevel));
        //---- Initializeplayer input -----------------
        input = new CarInput(carName, player_con);
        //=== initialize the scene camera to follow car =====
        chase = new ChaseCamera(cam, player, inputManager);
        chase.setDefaultHorizontalRotation(FastMath.HALF_PI);  //is always behind car
        chase.setDefaultVerticalRotation(FastMath.INV_PI);    // is always above the car lookign down
        chase.setSmoothMotion(true);
        chase.setMaxDistance(6);
        chase.setTrailingSensitivity(1);
        chase.setChasingSensitivity(10f);
        chase.setDefaultDistance(4f);
        chase.setMaxVerticalRotation(FastMath.QUARTER_PI);
        chase.setLookAtOffset(new Vector3f(0, 2, 0));
    }
//============= Load the racing track ==========================================

    public void initScene() {
        racingTrack = Assets.getTrack(gameLevel, assetManager); //load the track for the current level
        racingTrack.setName("track");
        track_con = new RigidBodyControl(0.0f);
        racingTrack.addControl(track_con);
        light = new DirectionalLight();
        light.setDirection(GameFactory.getStartPoint(gameLevel));
        sun = new AmbientLight();
    }
//====== Attaches loaded objects to the root node ==============================

    public void attachObjects() {

        //---- Attach objects to the root node ------
        localRootNode.attachChild(racingTrack);
        localRootNode.addLight(light);
        localRootNode.addLight(sun);
        localRootNode.attachChild(speedo);
        localRootNode.attachChild(player);
        //----------- Add physical objects to the physics space ------               
        physics.getPhysicsSpace().add(track_con);
        physics.getPhysicsSpace().add(player_con);

        //---- Attach gui text ----------------------
        localGuiNode.attachChild(timeText);
        localGuiNode.attachChild(speedText);
        //----- Then enable the user input -------------------
        stateManager.attach(input);
    }
//========= Initializes the game level and level finish test ===================

    public void setLevel(int level, boolean finished) {
        this.gameLevel = level;
        this.gameFinished = finished;
    }
//==============================================================================

    @Override
    public void update(float tpf) {
        float times = timer.getTimeInSeconds();
        if (times > 9 && times <= 10) {     // when 9 seconds have passed
            if (!countDone) {  // to attach it once only
                localGuiNode.attachChild(three);
                countDone = true;
            }
        }
        if (times > 11 && times <= 12) {
            if (countDone) {
                localGuiNode.detachChild(three);
                localGuiNode.attachChild(two);
                countDone = false;
            }
        }
        if (times > 13 && times <= 14) {
            if (!countDone) {
                localGuiNode.detachChild(two);
                localGuiNode.attachChild(one);
                countDone = true;
            }
        }
        if (times > 15 && times <= 16) {
            if (countDone) {
                localGuiNode.detachChild(one);
                localGuiNode.attachChild(go);
                countDone = false;
            }
        }
        if (times > 17) {
            if (!countDone) {
                localGuiNode.detachChild(go);
            }
            //--- When count down finished, display time -----
            updateGuiText();

        }
    }
//===== Determines elapsed time ================================================

    public void updateGuiText() {
    }
//======= Sets the player name enter from game menu ============================

    public void setName(String name) {
        this.playerName = name;
    }

    @Override
    public void cleanup() {
        super.cleanup();
        cam.setRotation(Quaternion.IDENTITY);  // reset camera rotation
        cam.setLocation(Vector3f.ZERO);
//------ removes objects from physics space --------    
        try {
            physics.getPhysicsSpace().remove(player_con);
            physics.getPhysicsSpace().remove(track_con);
        } catch (Exception e) {
        }
        //---- detach objects from local root node ---
        localRootNode.removeLight(sun);
        localRootNode.detachChild(player);
        localRootNode.removeLight(light);
        localRootNode.detachChild(racingTrack);
        localRootNode.detachChild(speedo);
//---- detach objects from local gui node -------
        localGuiNode.detachChild(timeText);
//---- then detach from root nodes ---------
        guiNode.detachChild(localGuiNode);
        rootNode.detachChild(localRootNode);
    }

    public void setCar(String car) {
        this.carName = car;
    }
}

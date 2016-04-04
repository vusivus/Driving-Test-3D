/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.controls.screen;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import com.jme3.audio.AudioRenderer;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.InputManager;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.xml.xpp3.Attributes;
import java.util.Properties;
import dt3d.factory.Assets;
import dt3d.factory.GameFactory;
import dt3d.game.Test;

/**
 *
 * @author Sanet
 */
public class StartScreenControllerState extends AbstractAppState
        implements ScreenController, Controller {

    private AssetManager assetManager;
    private InputManager inputManager;
    private Camera cam;
    Spatial floor;
    private AudioRenderer audioRenderer;
    private ViewPort viewPort;
    Node guiNode, rootNode;
    Node localRootNode = new Node("startRoot");
    AudioNode audio;
    RigidBodyControl control;
    DirectionalLight dl;
    AmbientLight ai;
    private SimpleApplication app;
    Test gameState;
    GameFactory factory;
    String[] car_names = new String[]{"car_01", "car_02", "car_03", "car_04", "car_05", "car_06"};
    Spatial[] cars = new Spatial[6];
    VehicleControl[] car_con = new VehicleControl[6];
    Nifty nifty;
    Quaternion rot = new Quaternion();
    ViewPort guiViewPort;
    PhysicsSpace space;
    String playerName;
    TextField textfield;
    Element popup;
    boolean starting = false;
    ListBox settingsList;
    int index = 0;
    ViewPort carView;
    NiftyJmeDisplay niftyDisplay;
    private AppStateManager stateManager;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.stateManager = stateManager;
        initPointers();
        //--- Initialize Nifty display
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/Nifty/startScreen.xml", "start", this);
        guiViewPort.addProcessor(niftyDisplay);

        showCar();
    }
//======== Init all class pointers =============================================

    public void initPointers() {
        //-- SimpleApp pointers -------
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.cam = this.app.getCamera();

        this.guiNode = this.app.getGuiNode();
        this.rootNode = this.app.getRootNode();
        this.audioRenderer = this.app.getAudioRenderer();
        this.viewPort = this.app.getViewPort();
        this.guiViewPort = this.app.getGuiViewPort();
        rootNode.attachChild(localRootNode);
        //--- Class Pointers ------------------
        factory = new GameFactory();
        gameState = new Test();

    }
//=========== Display he car on the car select menu ============================

    public void showCar() {
        for (int i = 0; i < 6; i++) {
            cars[i] = Assets.getCar(car_names[i], assetManager);
        } //--- get the cars from the factory class
        cam.setLocation(new Vector3f(0, 1, 6));
        Camera camera = cam.clone();
        camera.setViewPort(1f, 1f, 1f, 1f);       //-- full screen view
        carView = this.app.getRenderManager().createPostView("carview", camera);
        //-- load the garage ---------------       
        floor = assetManager.loadModel("Models/garage/garage.j3o");
        floor.setLocalTranslation(Vector3f.ZERO);
        localRootNode.attachChild(floor);
        localRootNode.attachChild(cars[index]);

        carView.attachScene(localRootNode);
    }
//==== change to next car ======================================================

    public void nextCar() {
        if (index != 5) {
            localRootNode.detachChild(cars[index]);
            ++index;
            localRootNode.attachChild(cars[index]);
        }
    }
//===== change to previous car =================================================

    public void prevCar() {
        if (index != 0) {
            localRootNode.detachChild(cars[index]);
            --index;
            localRootNode.attachChild(cars[index]);
        }
    }
//==============================================================================

    @Override
    public void update(float tpf) {
    }
//===== close menu and start game ==============================================

    public void startGame() {
        gameState.setName(playerName);
        gameState.setCar(car_names[index]);
        gameState.setLevel(1, false);

        stateManager.detach(this);
        stateManager.attach(gameState);
    }
//========= exit game ==========================================================

    public void quit() {
        System.exit(1);
    }
//==============================================================================

    @Override
    public void cleanup() {
        super.cleanup();
        localRootNode.detachChild(cars[index]);
        localRootNode.detachChild(floor);
        rootNode.detachChild(localRootNode);
        carView.detachScene(localRootNode);

        nifty.exit();
        guiViewPort.removeProcessor(niftyDisplay);
        this.app.getTimer().reset();
        cam.setRotation(Quaternion.IDENTITY);
        cam.setViewPort(0, 1, 0, 1);


    }

    public void bind(Nifty nifty, Screen screen) {
    }

    public void onStartScreen() {
    }

    public void onEndScreen() {
    }

    public void bind(Nifty nifty, Screen screen, Element element, Properties parameter, Attributes controlDefinitionAttributes) {
    }

    public void init(Properties parameter, Attributes controlDefinitionAttributes) {
    }

    public void onFocus(boolean getFocus) {
    }

    public boolean inputEvent(NiftyInputEvent inputEvent) {
        boolean v = false;
        return v;
    }
}
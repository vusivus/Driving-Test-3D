package dt3d;

import dt3d.controls.screen.StartScreenControllerState;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    BulletAppState bulletAppState;
    StartScreenControllerState screenState;

    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setSettingsDialogImage("Interface/Nifty/images/settings.jpg");
        settings.setTitle("Driving Test 3D");
        Main app = new Main();
        app.setSettings(settings);

        app.start();

    }

    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        bulletAppState = new BulletAppState();
        bulletAppState.setSpeed(60);
        bulletAppState.setThreadingType(BulletAppState.ThreadingType.PARALLEL);
        stateManager.attach(bulletAppState);
        screenState = new StartScreenControllerState();
        stateManager.attach(screenState);
        flyCam.setEnabled(false);

    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}

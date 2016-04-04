/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dt3d.factory;

import com.jme3.asset.AssetManager;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;

/**
 *
 * @author Sanet
 */
public class GameFactory {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Vector3f getStartPoint(int track) {
        Vector3f loc = null;
        if (track == 3) {
            loc = new Vector3f(0, 1, 0);
        }
        if (track == 4) {
            loc = new Vector3f(-40.2f, 0, -13.7f);
        }
        if (track == 2) {
            loc = new Vector3f(-66, 0f, 14);
        }
        if (track == 1) {
            loc = new Vector3f(-279f, -1f, -145f);
        }
        return loc;
    }

    public ParticleEmitter getEffect(AssetManager assetManager, String name) {
        ParticleEmitter effect = null;
        if ("checkPoint".equals(name)) {
            effect = new ParticleEmitter(name, ParticleMesh.Type.Triangle, 10);
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
            mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/Debris.png"));
            effect.setMaterial(mat);
            effect.setImagesX(3);
            effect.setImagesY(3);
            effect.setRotateSpeed(4);
            effect.setSelectRandomImage(true);
            effect.setGravity(0, 4, 0);
        }
        return effect;

    }
}

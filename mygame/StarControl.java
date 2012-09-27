package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author zach
 */
public class StarControl extends AbstractControl{
    BulletAppState bulletAppState;
    public int s; //size of the stars
    
    
    StarControl(int size)
    {
        s=size;
    }
    public boolean isValidLocation(float x, float y, float z){
        
        return true;
    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
    }
    
    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
    }

    public int size(){return this.s;}


    public Control cloneForSpatial(Spatial spatial) {
        final StarControl control = new StarControl(size());
    /* Optional: use setters to copy userdata into the cloned control */
    // control.setIndex(i); // example
    control.setSpatial(spatial);
    return control;
    }
    
    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
      
    }

    @Override
    protected void controlUpdate(float tpf) {
        /** Implement your spatial's behaviour here.
    * From here you can modify the scene graph and the spatial
    * (transform them, get and set userdata, etc).
    * This loop controls the spatial while the Control is enabled. */
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
        /* Optional: rendering manipulation (for advanced users) */
    }
}

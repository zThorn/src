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
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author zach
 */
public class StarControl implements Control{
    BulletAppState bulletAppState;
    
    public StarControl(){
             
    }
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        
    }
    
    public void createStar(Node LevelState, SimpleApplication app,float x, float y, float z, Vector3f vec){
          
      
      Box box1 = new Box(vec,x,y,z);
      Spatial star = new Geometry("Box", box1 );
      Material mat1 = new Material(app.getAssetManager(), 
                                    "Common/MatDefs/Misc/Unshaded.j3md");
      mat1.setColor("Color", ColorRGBA.Blue);
      star.setMaterial(mat1);
      
      RigidBodyControl starControl = new RigidBodyControl();

      starControl.setSpatial(star);
      starControl.setPhysicsLocation(vec);
      starControl.setMass(0);
      starControl.setFriction(1);
      starControl.setKinematic(false);
      app.getStateManager().getState(BulletAppState.class).getPhysicsSpace().add(starControl);
      app.getRootNode().attachChild(star);
    }
    
    public boolean isValidLocation(float x, float y, float z){
        
        return true;
    }
    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(float tpf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void render(RenderManager rm, ViewPort vp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //Will be used in the future to populate a LevelState with stars.
    public void randomStar(Node levelState, SimpleApplication app)
    {
        
        int x,y,z,s;
        Random ran = new Random();
        x=ran.nextInt(500);
        y=ran.nextInt(500);
        z=ran.nextInt(500);
        s=ran.nextInt(10);
        s+=4;
        Vector3f temp = new Vector3f(x,y,z);    //used for generating a position(0-100,0-100,0-100)
        StarControl star = new StarControl();
        star.createStar(levelState,app,s,s,s,temp); //Creates a star in levelState with size 2,2,2, in position temp
                
    }
    
}

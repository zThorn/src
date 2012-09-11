package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author adam & zach
 */
public class Level extends AbstractAppState{
    private SimpleApplication app;
    private Node    levelRoot = new Node("levelRoot");
    
     @Override
    public void initialize(AppStateManager stateManager, Application app) {
      super.initialize(stateManager, app); 
      this.app = (SimpleApplication)app;          // cast to a more specific class
      
      Box box1 = new Box( Vector3f.ZERO, 1,1,1);
    Geometry blue = new Geometry("Box", box1);
    Material mat1 = new Material(app.getAssetManager(), 
            "Common/MatDefs/Misc/Unshaded.j3md");
    mat1.setColor("Color", ColorRGBA.Blue);
    blue.setMaterial(mat1);
    levelRoot.attachChild(blue);
    this.app.getRootNode().attachChild(levelRoot);
   }
 
   @Override
    public void cleanup() {
      super.cleanup();
    }
 
    @Override
    public void setEnabled(boolean enabled) {
      // Pause and unpause
      super.setEnabled(enabled);
    }
 
    @Override
    public void update(float tpf) {
    }
}

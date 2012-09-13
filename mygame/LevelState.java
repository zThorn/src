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
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.Random;

/**
 *
 * @author adam & zach
 */
public class LevelState extends AbstractAppState{
    private SimpleApplication app;
    private Node levelRoot = new Node("levelRoot");
    
     @Override
    public void initialize(AppStateManager stateManager, Application app) {
      super.initialize(stateManager, app); 
      this.app = (SimpleApplication)app;          // cast to a more specific class
      
      StarControl testStar = new StarControl(); //Just a generic test of StarControl
      testStar.createStar(levelRoot,this.app,null,1,1,1);
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
    
    public void generateLevel(int num)
    {

    }
 
    @Override
    public void update(float tpf) {
    }
}

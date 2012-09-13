package mygame;

import com.jme3.app.SimpleApplication;
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



/**
 *
 * @author adam&&zach
 */
public class StarControl implements Control{

    public StarControl(){
    
    
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        
    }
    
    public void createStar(Node LevelState, SimpleApplication app,Vector3f vec,float x, float y, float z){
        
      Box box1 = new Box(vec, x,y,z);
      Spatial wall = new Geometry("Box", box1 );
      Material mat1 = new Material(app.getAssetManager(), 
                                    "Common/MatDefs/Misc/Unshaded.j3md");
      mat1.setColor("Color", ColorRGBA.Blue);
      setSpatial(wall);
      wall.setMaterial(mat1);
      LevelState.attachChild(wall);
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
    public void generateStarfield()
    {

    }
    
}

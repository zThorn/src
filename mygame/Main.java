package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

/**
 * test
 * @author adam && zach
 * 
 * COLLISION GROUPS:
 * 1 - Player RigidBody Collids w/ 3
 * 2 - Player GhostControl collides 2/ 3
 * 3 - Stars 
 */
public class Main extends SimpleApplication {
    
    Material defaultMat;
    private boolean shouldImpulse = false;
    PlayerState playerState;
    public BulletAppState bulletAppState;
    LevelState levelState = new LevelState();
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
        
    }

    
    @Override
    public void simpleInitApp() 
    {
        defaultMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        defaultMat.setColor("Color", ColorRGBA.Blue);
            
        
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);       
        
        bulletAppState.getPhysicsSpace().setGravity(Vector3f.ZERO);

        stateManager.attach(levelState);

        
        playerState = new PlayerState(this);
        stateManager.attach(playerState);
        
        //Set up custom movement
        flyCam.setMoveSpeed(0f);
        inputManager.addMapping("Move", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, new String[]{"Move"});
        inputManager.addMapping("test", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addListener(actionListener, new String[]{"test"});
    }

    @Override
    public void simpleUpdate(float tpf) {
        bulletAppState.getPhysicsSpace().enableDebug(assetManager);
        Spatial player = playerState.playerRoot;
        cam.setLocation(player.getLocalTranslation());
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
          if (name.equals("Move") && !keyPressed) {
            shouldImpulse = true;
          }
          
          if (name.equals("test") && !keyPressed) {
            playerState.test();
          }
        }
    };
    
    public boolean doImpulse()
    {
        return shouldImpulse;
    }
    
    public void doneImpulse()
    {
        shouldImpulse = false;
    }

}
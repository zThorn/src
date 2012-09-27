package mygame;

import com.jme3.app.state.AbstractAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Adam
 */
public class PlayerState extends AbstractAppState{
    Node playerRoot = new Node("playerRoot"); //This will contain both our gun and player model.
    PlayerCollisionControl playerCollider;
    Main app;
    Vector3f movementVector;
    
    private boolean updateStarDetection = false;
    
    
    PlayerState(Main app)
    {
        this.app = app;
        playerCollider = new PlayerCollisionControl(app, this);
        playerRoot.setLocalTranslation(app.getCamera().getLocation());        
        app.getRootNode().attachChild(playerRoot);
        
        playerRoot.addControl(playerCollider);        
        this.app.bulletAppState.getPhysicsSpace().add(playerCollider);
        this.app.bulletAppState.getPhysicsSpace().addCollisionListener(playerCollider);
        
        movementVector = new Vector3f();
        
        /*Box box1 = new Box(Vector3f.ZERO, 1f, 1f,1f);
      Spatial star = new Geometry("Box", box1 );
      Material mat1 = new Material(app.getAssetManager(), 
                                    "Common/MatDefs/Misc/Unshaded.j3md");
      mat1.setColor("Color", ColorRGBA.Blue);
      star.setMaterial(mat1);
      
      playerRoot.attachChild(star);*/
    }
    
    @Override
    public void update(float tpf)
    {
        if(updateStarDetection)
        {
            if(!playerCollider.canCollide() && playerCollider.isAttached())
            {
                playerCollider.setAttached(null);
                playerCollider.enableCollision(true);
                updateStarDetection = false;
            }
        }
        
        if(app.doImpulse())
        {
            if(playerCollider.isAttached())
            {
                playerCollider.enableCollision(false);
                updateStarDetection = true;
            }
            
            playerCollider.setLinearVelocity(app.getCamera().getDirection().mult(40));         
            
            app.doneImpulse();
        }
        
        playerRoot.setLocalTranslation(playerCollider.getPhysicsLocation());
    }

    void resetGhost() {
        movementVector.zero();
    }
    
}

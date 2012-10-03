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
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class PlayerState extends AbstractAppState{
    public static float PlayerWidth     = 1.5f;
    public static float PlayerHeight    = 4f;
    
    Node playerRoot = new Node("playerRoot"); //This will contains the player ghost and rigid body (for collision detection) as well as th player weapon model.
    PlayerCollisionControl playerCollider;
    Main app;
    
    
    PlayerState(Main app)
    {
        this.app = app;
        playerCollider = new PlayerCollisionControl(app, this);
        playerRoot.setLocalTranslation(app.getCamera().getLocation());        
        app.getRootNode().attachChild(playerRoot);
        
        playerCollider.attachToWorld(playerRoot, this.app.bulletAppState.getPhysicsSpace());
    }
    public boolean canJump()
    {
        if(!playerCollider.isColliding()) return true;
        
        
        
        return false;
    }
    
    @Override
    public void update(float tpf)
    {
        
        if(app.doImpulse() /*&& canJump()*/)
        {
            playerCollider.setLinearVelocity(app.getCamera().getDirection().mult(100));         
            
            app.doneImpulse();
        }
        
        playerRoot.setLocalTranslation(playerCollider.getPhysicsLocation());
    }
    
    public void test()
    {        
        System.out.print("\nColliding: " + playerCollider.isColliding());
        System.out.print("\nCan collide: " + playerCollider.canCollide());
        System.out.print("\nCollision normal: " +playerCollider.getCollisionNormal());
               
        Vector3f norm = playerCollider.getCollisionNormal();
        System.out.print("Norm: " + norm);
        
    }
    
}

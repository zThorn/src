package mygame;

import com.jme3.app.state.AbstractAppState;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Adam
 */
public class PlayerState extends AbstractAppState{
    Node playerRoot = new Node("playerRoot"); //This will contain both our gun and player model.
    CapsuleCollisionShape playerBounds = new CapsuleCollisionShape(.5f, 4f);
    Main app;
    GhostControl ghostPlayer = new GhostControl(playerBounds);
    Vector3f movementVector;
    
    
    PlayerState(Main app)
    {
        this.app = app;
        playerRoot.setLocalTranslation(app.getCamera().getLocation());        
        app.getRootNode().attachChild(playerRoot);
        
        playerRoot.addControl(ghostPlayer);
        this.app.bulletAppState.getPhysicsSpace().add(ghostPlayer);
        
        movementVector = new Vector3f();
    }
    
    @Override
    public void update(float tpf)
    {
        //Movement mode tells the update function whether to check for WASD or to wait for an impulse
        if(app.doImpulse())
        {
            movementVector = movementVector.add(app.getCamera().getDirection().mult(20));
            app.doneImpulse();
        }
        
        //Need to change this to be more efficient. Get child has to query all children in playerRoot
        
        if(movementVector.length() > 0)
            playerRoot.move(movementVector.mult(tpf));
        
        if(ghostPlayer.getOverlappingCount() > 0)
            movementVector.zero();
    }
    
}

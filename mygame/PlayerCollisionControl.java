package mygame;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Adam & Zach
 */
public class PlayerCollisionControl extends RigidBodyControl
        implements PhysicsCollisionListener{
    
    static public final CapsuleCollisionShape playerBounds = new CapsuleCollisionShape(1.5f, 4f);
    private final Main app;
    private final PlayerState playerState;
    
    private Spatial attachedTo = null;
    private boolean collisionsEnabled = true;
    
    public PlayerCollisionControl(Main app, PlayerState player)
    {
        super(playerBounds);
        this.app = app;
        this.playerState = player;               
    }
    
    public boolean isAttached() { return !(attachedTo == null); }
    public Spatial getAttached(){ return attachedTo; }
    public void setAttached(Spatial att){ attachedTo = att; }
    
    public void enableCollision(boolean enabled)
    {
        collisionsEnabled = enabled;        
        System.out.print("collisionsEnabled = " + collisionsEnabled + "\n");
        System.out.print("isAttached() = " + isAttached() + "\n");
    }
    
    public boolean canCollide() { return collisionsEnabled; }

    public void collision(PhysicsCollisionEvent event) {
        if(!canCollide()) return;
        
        Node player = null;
        StarControl starControl = null;
        Spatial star = null;
        
        if(event.getNodeA().getName().equals("playerRoot"))
        {
            player = (Node)event.getNodeA();
        }else if(event.getNodeB().getName().equals("playerRoot"))
        {
            player = (Node)event.getNodeB();
        }
        
        if(event.getNodeA().getName().equals("star"))
        {
            starControl = event.getNodeA().getControl(StarControl.class);
            star = event.getNodeA();
        }else if(event.getNodeB().getName().equals("star"))
        {
            starControl = event.getNodeB().getControl(StarControl.class);
            star = event.getNodeB();
        }
        
        if(player != null && (starControl != null || star != null))
        { 
            attachedTo = star;
            setLinearVelocity(Vector3f.ZERO);
        }            
    }    
}

package mygame;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.PhysicsTickListener;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Adam & Zach
 */
public class PlayerCollisionControl extends RigidBodyControl
        implements PhysicsTickListener, PhysicsCollisionListener{
    
    static public final CapsuleCollisionShape playerBounds = new CapsuleCollisionShape(1.5f, 4f);
    private final Main app;
    private final PlayerState playerState;
    
    private boolean collisionsEnabled = true;
    private boolean isColliding;
    private GhostControl playerGhost;
    private Vector3f collisionNormal;
    
    public PlayerCollisionControl(Main app, PlayerState player)
    {
        super(playerBounds);
        this.app = app;
        this.playerState = player;  
        
        playerGhost = new GhostControl(playerBounds);
        
        this.setCollisionGroup(COLLISION_GROUP_01);
        this.setCollideWithGroups(COLLISION_GROUP_03);
        playerGhost.setCollisionGroup(COLLISION_GROUP_02);
        playerGhost.setCollideWithGroups(COLLISION_GROUP_03);
    }
    
    public void enableCollision(boolean enabled)
    {
        collisionsEnabled = enabled;
    }
    
    public boolean canCollide() { return collisionsEnabled; }    
    public boolean isColliding() { return isColliding; }
    public Vector3f getCollisionNormal() { return collisionNormal; }
            
    @Override
    public void update(float tpf)
    {
        
    }
    
    @Override
    public void prePhysicsTick(PhysicsSpace space, float f) {
        if(isColliding())
            setLinearVelocity(Vector3f.ZERO);      
        
    }
    
    @Override
    public void physicsTick(PhysicsSpace space, float f) {
        if(playerGhost.getOverlappingCount() > 0 && canCollide() && !isColliding())
        {
            isColliding = true;
            System.out.print("Ghost collision detected: isColliding("+ playerGhost.getOverlappingCount() +") = " + isColliding());
        }else if(isColliding() && playerGhost.getOverlappingCount() == 0) {            
            isColliding = false;
            collisionNormal = Vector3f.ZERO;
            
            System.out.print("Ghost collision not detected: isColliding("+ playerGhost.getOverlappingCount() +") = " + isColliding());
        }
        
        
    }

    void attachToWorld(Node root, PhysicsSpace physicsSpace) {         
        root.addControl(this);
        root.addControl(playerGhost);
        physicsSpace.add(this);
        physicsSpace.addTickListener(this);
        physicsSpace.addCollisionListener(this);
        physicsSpace.add(playerGhost);        
    }
    
    @Override
    public void collision(PhysicsCollisionEvent event) {
        if(!canCollide()) return;
        
        Spatial star = null;
        
        if(event.getNodeA().getName().equals("star"))
        {
            star = event.getNodeA();
        }else if(event.getNodeB().getName().equals("star"))
        {
            star = event.getNodeB();
        }
        
        if(star != null)
        {
            collisionNormal = event.getNormalWorldOnB();
        }else{
            System.out.print("Unknwon collision between: " + event.getNodeA().toString() + " and " + event.getNodeB().toString() + "\n");
        }
    }
}

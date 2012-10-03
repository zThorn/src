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
    
    static public final CapsuleCollisionShape playerBounds = new CapsuleCollisionShape(PlayerState.PlayerWidth, PlayerState.PlayerHeight);
    static public final CapsuleCollisionShape playerGhostBounds = new CapsuleCollisionShape(PlayerState.PlayerWidth + .5f, PlayerState.PlayerHeight + .5f);
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
        
        this.setMass(2f);
        
        playerGhost = new GhostControl(playerGhostBounds);
        
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
        if(isColliding() /*&& getLinearVelocity() != Vector3f.ZERO*/)
        {
            setLinearVelocity(Vector3f.ZERO);  
        }
        
    }
    
    @Override
    public void physicsTick(PhysicsSpace space, float f) {
        if(playerGhost.getOverlappingCount() > 0 && canCollide() && !isColliding())
        {
            isColliding = true;
            System.out.println("Ghost collision detected: isColliding("+ playerGhost.getOverlappingCount() +") = " + isColliding());            
        }else if(isColliding() && playerGhost.getOverlappingCount() == 0) {            
            isColliding = false;
            collisionNormal = null;
            System.out.println("Ghost collision not detected: isColliding("+ playerGhost.getOverlappingCount() +") = " + isColliding());
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
            float starSize = (float)star.getControl(StarControl.class).size();
            float x, y, z;
            
            if(collisionNormal.getX() == 1 || collisionNormal.getX() == -1)
            {
                x = collisionNormal.getX();
            }else{
                x = 0;
            }
            
            if(collisionNormal.getY() == 1 || collisionNormal.getY() == -1)
            {
                y = collisionNormal.getY();
            }else{
                y = 0;
            }
            
            if(collisionNormal.getZ() == 1 || collisionNormal.getZ() == -1)
            {
                z = collisionNormal.getZ();
            }else{
                z = 0;
            }
            
            Vector3f starEdge = star.getLocalTranslation().add((starSize+(PlayerState.PlayerWidth/2))*x, (starSize+(PlayerState.PlayerWidth/2))*y, (starSize+(PlayerState.PlayerHeight/2))*z);
            System.out.println("Local: " + star.getLocalTranslation() + "\nedge: " + starEdge);
            setPhysicsLocation(starEdge);
            
        }else{
            System.out.print("Unknwon collision between: " + event.getNodeA().toString() + " and " + event.getNodeB().toString() + "\n");
        }
    }
}

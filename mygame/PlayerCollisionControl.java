package mygame;

import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
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
    
    public PlayerCollisionControl(Main app, PlayerState player)
    {
        super(playerBounds);
        this.app = app;
        this.playerState = player;               
    }

    public void collision(PhysicsCollisionEvent event) {
        Node player = null;
        StarControl starControl = null;
        
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
        }else if(event.getNodeB().getName().equals("star"))
        {
            starControl = event.getNodeB().getControl(StarControl.class);
        }
        
        if(player == null)
        {
            System.out.print("Could not find player in collision");
        }else{
            System.out.print("Found player in collision");
        }
        
        if(starControl == null)
        {
           System.out.print("Could not find star in collision"); 
        }else{
            System.out.print("Found star in collision");
        }
        
        
        /*if(player != null && starControl != null)
        {
            
        }else{
            System.out.print("Unknown collision\n");
        }*/
            
    }
    
}

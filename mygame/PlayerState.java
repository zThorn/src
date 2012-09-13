/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.state.AbstractAppState;
import com.jme3.bullet.BulletAppState;
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
    Node playerRoot = new Node("playerRoot");
    Box playerBounds = new Box(Vector3f.ZERO, 1f, 6f, 1f);
    Main app;
    
    
    PlayerState(Main app)
    {
        this.app = app;
        Geometry player_geo = new Geometry("playerGeom", playerBounds);
        player_geo.setMaterial(app.defaultMat);
        playerRoot.attachChild(player_geo);
        playerRoot.setLocalTranslation(app.getCamera().getLocation());
        RigidBodyControl player_phys = new RigidBodyControl(2f);
        player_geo.addControl(player_phys);
        this.app.getStateManager().getState(BulletAppState.class)
                .getPhysicsSpace().add(player_phys);
        
        app.getRootNode().attachChild(playerRoot);
    }
    
    @Override
    public void update(float tpf)
    {
        if(app.doImpulse())
        {
            playerRoot.getChild("playerGeom").getControl(RigidBodyControl.class)
                    .setLinearVelocity(app.getCamera().getDirection().mult(25));
            app.doneImpulse();
        }
        
        playerRoot.getChild("playerGeom").setLocalTranslation(playerRoot.getChild("playerGeom").getControl(RigidBodyControl.class).getPhysicsLocation());
    }
    
}

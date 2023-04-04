package GUI;

import java.awt.*;
import java.util.List;

public class CollisionEntity {

    public boolean checkCollisionBool(GraphicalEntity identity, List<GraphicalEntity> listGraphicalEntity)
    {
        Rectangle rectIdentity = identity.getBounds();                 // Rect of Player

        for(GraphicalEntity object : listGraphicalEntity)
        {
            // Don't include Player and no physical object
            if (object instanceof PhysicalEntity && !(object instanceof CollaborationEntity) && object.getIfIsActive())
            {
                Rectangle objectRect = object.getBounds();          // Rect of visibleObject
                if (!object.equals(identity))                       // If != Player
                    if(rectIdentity.intersects(objectRect))           // Collision
                        return true;
            }
        }
        return  false;
    }

    public GraphicalEntity checkCollisionEntity(GraphicalEntity entity, List<GraphicalEntity> listGraphicalEntity) {
        // Use to search in collision with CollaborativeEntity when player want action
        Rectangle rectIdentity = entity.getBounds();                 // Rect of Player

        for (GraphicalEntity object : listGraphicalEntity) {
            // Don't include Player and no physical object
            if (object instanceof CollaborationEntity || object instanceof EnvironmentEntity && object.getIfIsActive()) {
                Rectangle objectRect = object.getBounds();          // Rect of visibleObject
                if (!object.equals(entity))                       // If != Player
                    if (rectIdentity.intersects(objectRect))           // Collision
                        return object;
            }
        }
        return entity;    // player if no collision
    }

    public boolean checkCollisionAnimal(GraphicalEntity identity, List<GraphicalEntity> listGraphicalEntity)
    {
        Rectangle rectIdentity = identity.getBounds();                 // Rect of Player
        for(GraphicalEntity object : listGraphicalEntity)
        {
            // Include PhysicalEntity exclude CollaborationEntity Player
            if (object instanceof PhysicalEntity && !(object instanceof CollaborationEntity) && !(object instanceof Player) && object.getIfIsActive())
            {
                Rectangle objectRect = object.getBounds();          // Rect of visibleObject
                if (!object.equals(identity))                           // If != Player
                    if (rectIdentity.intersects(objectRect))           // Collision
                    {
                        return true;
                    }
            }
        }
        return false;
    }
}

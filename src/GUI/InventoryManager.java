package GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    public GraphicalEntity getInventory(List<GraphicalEntity> listGraphicalEntity)
    {   // return the player inventory
        for (GraphicalEntity entity: listGraphicalEntity)
            if(entity instanceof Inventory)
                return entity;
        return null;
    }

    public void setInvisibleInventory(List<GraphicalEntity> listGraphicalEntity)
    {   // Set invisible the player inventory
        GraphicalEntity inventory = getInventory(listGraphicalEntity);
        ((Inventory) inventory).setInvisible();
    }

    public void setVisibilityInventory(List<GraphicalEntity> listGraphicalEntity)
    {   // Search and set inventory visibility
        GraphicalEntity inventory = getInventory(listGraphicalEntity);
        ((Inventory) inventory).setVisibility();
    }

    public String tryToUseInventory(List<GraphicalEntity> listGraphicalEntity, char numberCallUser)
    {   // use item of the inventory when user enter number 1 to 6
        GraphicalEntity inventory = getInventory(listGraphicalEntity);
        String physicalBlockName = "";
        if(((Inventory) inventory).getVisibility())
            physicalBlockName = ((Inventory) inventory).useItem(Character.getNumericValue(numberCallUser));

        return physicalBlockName;
    }

    public List<GraphicalEntity> createListCollaborationEntity(List<GraphicalEntity> listGraphicalEntity)
    {   // return list with CollaborationEntity in GraphicalEntity
        List<GraphicalEntity> listCollaborativeEntity = new ArrayList<>();
        for(GraphicalEntity entity : listGraphicalEntity)
            if(entity instanceof CollaborationEntity)
                listCollaborativeEntity.add(entity);

        return listCollaborativeEntity;
    }

    public void playerSearchCollaborationEntity(List<GraphicalEntity> listGraphicalEntity)
    {   // Find if player is in collision with CollaborationEntity
        for (GraphicalEntity entity: listGraphicalEntity)
            if(entity instanceof Player)
            {
                // Create list
                List<GraphicalEntity> listCollaborativeEntity = createListCollaborationEntity(listGraphicalEntity);
                CollisionEntity collisionForPlayer = new CollisionEntity();         // class Collision
                // check collision
                GraphicalEntity EntityCollision = collisionForPlayer.checkCollisionEntity
                        ((PhysicalEntity) entity, listCollaborativeEntity);
                if (!EntityCollision.equals(entity))
                {
                    GraphicalEntity inventory = getInventory(listGraphicalEntity);
                    System.out.println("Add item to inventory");
                    ((Inventory) inventory).setNewItem(((CollaborationEntity) EntityCollision).getInventoryItemName());

                    // Decrease usageDurability
                    ((CollaborationEntity) EntityCollision).decreaseUsageDurability();
                    if (((CollaborationEntity) EntityCollision).getUsageDurability() <= 0) {
                        listGraphicalEntity.remove(EntityCollision);
                        EntityCollision.setIcon(new ImageIcon());
                    }
                }
                break;
            }
    }

    public void mineBlock(List<GraphicalEntity> listGraphicalEntity, int [][] mapArray)
    {   // Destroy EnvironmentEntity and add it to inventory
        List<GraphicalEntity> listEnvironmentEntity = new ArrayList<>();
        TargetingCursor targetingCursor = null;
        Inventory inventory = null;

        for (GraphicalEntity entity: listGraphicalEntity) {
            if (entity instanceof TargetingCursor)
                targetingCursor = (TargetingCursor) entity;
            else if (entity instanceof EnvironmentEntity)
                listEnvironmentEntity.add(entity);
            else if (entity instanceof Inventory)
                inventory = (Inventory) entity;
        }

        CollisionEntity collisionEntity = new CollisionEntity();
        if(targetingCursor != null) {
            GraphicalEntity collidedEntity;
            if ((collidedEntity = collisionEntity.checkCollisionEntity(targetingCursor, listEnvironmentEntity)) != targetingCursor)
            {
                System.out.println("remove bloc position : " + collidedEntity.posX + " : " + collidedEntity.posY);
                mapArray[collidedEntity.posX / 60][collidedEntity.posY / 60] = 0;       // FIX ME
                // System.out.println("take pose : " + collidedEntity.posX / 60);
                ((Inventory) inventory).setNewItem(((EnvironmentEntity) collidedEntity).getItemNamePNG());
                collidedEntity.setInactive();                           // inactive
            }
        }
        else
            System.out.println("targetingCursor is null");
    }
}

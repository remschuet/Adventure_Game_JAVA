package GUI;

import java.util.List;
import java.util.Random;

public class MovementEntity {

    private final List<GraphicalEntity> listGraphicalEntity;
    private Player player;
    private final CollisionEntity collisionEntity = new CollisionEntity();
    private final int[][] mapArray;

    MovementEntity(List<GraphicalEntity> listGraphicalEntity, int[][] mapArray)
    {
        this.listGraphicalEntity = listGraphicalEntity;
        this.mapArray = mapArray;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public boolean movePigEntity(int moveX, int moveY)
    {
        boolean canMove = true;
        for(GraphicalEntity entity : this.listGraphicalEntity)
            if (!(entity instanceof Player))
                if (increasePos(entity, moveX, moveY)) {
                    decreasePos(moveX, moveY);
                    canMove = false;
                }
        return canMove;
    }

    public boolean increasePos(GraphicalEntity entity, int increaseX, int increaseY)
    {
        entity.setLocation(entity.getX() + increaseX, entity.getY() + increaseY);
        return (collisionEntity.checkCollisionBool(player, listGraphicalEntity));
    }

    public void decreasePos(int decreaseX, int decreaseY)
    {
        for(GraphicalEntity entity : this.listGraphicalEntity)
            if (!(entity instanceof Player))
                entity.setLocation(entity.getX() - decreaseX, entity.getY() - decreaseY);
    }

    public void callEverySecond(int currentCenterX, int currentCenterY)
    {
        moveAnimalAndBulletEntity(currentCenterX, currentCenterY);
    }

    public void callEvery200(int currentCenterX, int currentCenterY) {
        for (GraphicalEntity entity : this.listGraphicalEntity)
            if (entity instanceof Bullet && entity.getIfIsActive())
                moveBulletEntity((Bullet) entity);
    }

    public void moveAnimalAndBulletEntity(int currentCenterX, int currentCenterY)
    {
        for(GraphicalEntity entity : this.listGraphicalEntity)
        {
            if(entity instanceof Pig && entity.getIfIsActive())
            {
                movePigEntity((Animal) entity);
            }
            else if (entity instanceof Wolf && entity.getIfIsActive())
            {
                moveWolfEntity((Wolf) entity, currentCenterX, currentCenterY);
            }
        }
    }

    public void moveBulletEntity(Bullet bullet)
    {
        Gameplay.DIRECTION direction = bullet.getDIRECTION();

        int[] directionXY = {0, 0};
        int speed = bullet.getSpeed();

        switch (direction)
        {
            case LEFT-> directionXY[0] = -speed;
            case RIGHT-> directionXY[0] = speed;
            case UP-> directionXY[1] = -speed;
            case DOWN-> directionXY[1] = speed;
        }

        bullet.increasePos(bullet, directionXY[0], directionXY[1]);

        GraphicalEntity entity = collisionEntity.checkCollisionBullet(bullet, this.listGraphicalEntity);
        if (!entity.equals(bullet)) {
            bullet.setInactive();
            if (entity instanceof Animal)
                entity.setInactive();
        }
        else if (!bullet.decreaseNbrMovement())
            bullet.setInactive();
    }

    public void moveWolfEntity(Wolf wolf, int currentCenterX, int currentCenterY)
    {
        wolf.updatePath(mapArray, currentCenterX, currentCenterY);

        if (wolf.path.size() > 1 && wolf.path.size() < 12) {   // if animal not on objective
            wolf.increasePos(wolf,
                    (wolf.path.get(1).y * 60 - wolf.path.get(0).y * 60),
                    (wolf.path.get(1).x * 60 - wolf.path.get(0).x * 60));
            if (collisionEntity.checkCollisionAnimal(wolf, this.listGraphicalEntity)) // if collision
                wolf.decreasePos(wolf,
                        (wolf.path.get(1).y * 60 - wolf.path.get(0).y * 60),
                        (wolf.path.get(1).x * 60 - wolf.path.get(0).x * 60));
        }
    }

    public void movePigEntity(Animal animal)
    {
        int[] direction = new int[] {0, 0};              // x, y

        chooseDirectionAnimal(direction, animal);                   // Choose direction

        animal.increasePos(animal,direction[0], direction[1]);      // Increase

        do
        {
            animal.decreasePos(animal, direction[0], direction[1]); // Decrease
            direction[0] = 0; direction[1] = 0;                     // Reset
            chooseDirectionAnimal(direction, animal);               // Choose direction
            animal.increasePos(animal,direction[0], direction[1]);  // Increase
        }while (collisionEntity.checkCollisionAnimal(animal, this.listGraphicalEntity));
    }

    public void chooseDirectionAnimal(int[] direction, Animal animal)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(11);

        switch (randomNum) {
            case 0: direction[0] += animal.getSpeed(); break;           // right
            case 1: direction[0] -= animal.getSpeed(); break;           // left
            case 2: direction[1] += animal.getSpeed(); break;           // down
            case 3: direction[1] -= animal.getSpeed(); break;           // up
            case 4: case 5: break;                                      // don't move
            case 6: case 7: case 8: case 9: case 10:
                direction[0] += animal.getDirectionX() + (animal.getDirectionX() > 0 ? animal.getRun(): 0);
                direction[1] += animal.getDirectionY() + (animal.getDirectionY() > 0 ? animal.getRun(): 0);      // last direction
                break;
        }

    }
}

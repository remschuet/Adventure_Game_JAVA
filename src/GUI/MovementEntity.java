package GUI;

import java.util.List;
import java.util.Random;

public class MovementEntity {

    private final List<GraphicalEntity> listGraphicalEntity;
    private Player player;
    private CollisionEntity collisionEntity = new CollisionEntity();
    private final int[][] mapArray;

    public boolean wolfisCalcul = false;
    public int valueWolf = 1;



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
        moveAnimalEntity(currentCenterX, currentCenterY);
    }

    public void moveAnimalEntity(int currentCenterX, int currentCenterY)
    {
        for(GraphicalEntity entity : this.listGraphicalEntity)
        {
            if(entity instanceof Pig)
            {
                movePigEntity((Animal) entity);
            }
            else if (entity instanceof Wolf)
            {
                moveWolfEntity((Wolf) entity, currentCenterX, currentCenterY);
            }
        }
    }

    public void moveWolfEntity(Wolf animal, int currentCenterX, int currentCenterY)
    {

        animal.createPath(mapArray, currentCenterX, currentCenterY);
        animal.increasePos(animal,
                (animal.path.get(this.valueWolf).y * 60 - animal.path.get(this.valueWolf - 1).y * 60),
                (animal.path.get(this.valueWolf).x * 60 - animal.path.get(this.valueWolf - 1).x * 60));      // Increase
        System.out.println((currentCenterX - 280) % 60);
      //  System.out.println((animal.path.get(this.valueWolf - 1).x) + " first " + (animal.path.get(this.valueWolf - 1).y));
      //  System.out.println((animal.path.get(this.valueWolf).x) + " second " + (animal.path.get(this.valueWolf).y));

        /*
        if (!this.wolfisCalcul) {
            animal.createPath(mapArray, currentCenterX, currentCenterY);
            this.wolfisCalcul = true;
        }

        if (animal.path.size() - 1 > valueWolf) {
            this.valueWolf += 1;
            animal.increasePos(animal, animal.path.get(this.valueWolf).y * 60 - animal.path.get(this.valueWolf - 1).y * 60, animal.path.get(this.valueWolf).x * 60 - animal.path.get(this.valueWolf - 1).x * 60);      // Increase
            System.out.println((animal.getX()) + " - " + (animal.getY()));
        }

         */
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

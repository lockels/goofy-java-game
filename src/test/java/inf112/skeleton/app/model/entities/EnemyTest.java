package inf112.skeleton.app.model.entities;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Rectangle;

public class EnemyTest {
 
    
    @Test
    public void testMoveTowards() {
        // Arrange
        Rectangle hitbox = new Rectangle(0, 0, 32, 32);
        String dungeon_sheet = "dungeon_sheet.png";
        int spriteSheetX = 0;
        int spriteSheetY = 0;
        int spriteWidth = 32;
        int spriteHeight = 32;
        float speed = 1.0f;
        Enemy enemy = new Enemy(hitbox, dungeon_sheet, spriteSheetX, spriteSheetY, spriteWidth, spriteHeight, speed);

        //Act
        enemy.moveTowards(100, 100);

        // Assert
        // You can add assertions based on the expected behavior of moveTowards method
        // For example, you can check if the enemy's hitbox has moved towards the target
        // You can also check if the enemy's position is updated correctly after the movement
    }
}

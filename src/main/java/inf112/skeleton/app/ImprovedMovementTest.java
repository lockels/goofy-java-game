package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.skeleton.app.entities.*;
import inf112.skeleton.app.myInput.MyInputAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.Map;

public class ImprovedMovementTest extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<TextureRegion> entitySprites = new ArrayList<>();
    private Texture spriteSheet;
    private TextureRegion playerSprite;
    private TextureRegion enemySprite;
    private MyInputAdapter inputAdapter;
    private BitmapFont font;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public ImprovedMovementTest() {
        player = new Player(
                new Rectangle(400,20,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT),
                "dungeon_sheet.png",
                306,
                112,
                16,
                12
                );
        Enemy enemy1 = new Enemy(
                new Rectangle(400,400,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT),
                "dungeon_sheet.png",
                322,
                112,
                16,
                12
                );
        Enemy enemy2 = new Enemy(
                new Rectangle(300,500,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT),
                "dungeon_sheet.png",
                322,
                112,
                16,
                12
        );
        Enemy enemy3 = new Enemy(
                new Rectangle(500,500,Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT),
                "dungeon_sheet.png",
                322,
                112,
                16,
                12
        );
        entities.add(player);
        entities.add(enemy1);
        entities.add(enemy2);
        entities.add(enemy3);
        inputAdapter = new MyInputAdapter(player);
    }

    @Override
    public void create () {
        //Batch
        batch = new SpriteBatch();

        //Camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 800);

        //Sprites
        spriteSheet = getSpriteSheet(player.getSpriteSheetPath());
        for (Entity entity : entities){
            entitySprites.add(getSpriteFromSheet(spriteSheet, entity.getSpriteSheetX(), entity.getSpriteSheetY(), entity.getSpriteWidth(), entity.getSpriteHeight()));
        }


        //Font
        font = new BitmapFont();

        //Input
        Gdx.input.setInputProcessor(inputAdapter);

        show();
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 1, 0, 1);
        tick();
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        mapRenderer.setView(cam);
        mapRenderer.render();
        for (Entity entity : entities){
            TextureRegion entitySprite = entitySprites.get(entities.indexOf(entity));
            batch.draw(entitySprite, entity.getX(), entity.getY(), Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        }
        //Debug
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        font.draw(batch, activePlayerDirections(), 10, 40);
        
        batch.end();
    
    }

    private String activePlayerDirections () {
        StringBuilder result = new StringBuilder();
        Map<Direction, Boolean> dict = player.getMovementDirections();
        for (Direction dir : player.getMovementDirections().keySet()){
            if (dict.get(dir)){ result.append(dir.name()).append("/"); }
        }
        return result.toString();
    }

    private void tick() {
        player.move();
        for (Entity entity : entities.subList(1,entities.size())){
            entity.moveTowards(player.getX(), player.getY(), 1000);
        }
    }

    public void resize(int height, int width){
        cam.viewportHeight = height;
        cam.viewportWidth = width;
        cam.update();
    }

    private void show(){
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/map1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
    }

    public void dispose(){
        map.dispose();
        mapRenderer.dispose();
    }

    private TextureRegion getSpriteFromSheet(Texture spriteSheet, int x, int y, int width, int height){
        return new TextureRegion(spriteSheet, x, y, width, height);
    }

    private Texture getSpriteSheet(String spriteSheet){
        return new Texture(Gdx.files.internal(spriteSheet));
    }

}

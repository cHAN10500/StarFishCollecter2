package Hung.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import static com.badlogic.gdx.Gdx.audio;
import static com.badlogic.gdx.Gdx.input;

public class GameScreen implements Screen {
    Master game;
    ShapeRenderer shapeRenderer;
    Background waterborder;
    Stage stage;
    Random random;
    MyActor turtle;
    Rock rock;
    Rock rock1;
    Rock rock2;
    Rock rock3;
    Sign sign;
    Sign sign1;
    Starfish starfish1;
    Starfish starfish2;
    Starfish starfish3;
    Shark tank;
    Random ran;
    An an;
    Texture rockImage;
    Texture sighImage;
    Texture win;
    Texture gameover;
    Texture pressc;
    Sound dropSound;
    Sound lasersound;
    Music nenMusic;
    Whirlpool whirlpool;
    BitmapFont font;
    int starfish = 5;
    Boolean kt = false;
    Boolean audio = true;

    OrthographicCamera camera;

    GameScreen(Master game){
        this.game = game;
        stage = new Stage();
    }

    @Override
    public void show() {
        kt = false;
        starfish = 10;
        shapeRenderer = new ShapeRenderer();
        game.batch = new SpriteBatch();
        stage = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        waterborder = new Background(0,0,stage);
        rock = new Rock(100, 90, stage);
        rock1 = new Rock(180, 350, stage);
        rock2 = new Rock(290, 120, stage);
        rock3 = new Rock(230, 243, stage);
        turtle = new MyActor(new Texture("turtle.png"), 6,1, stage);

        starfish1 = new Starfish(40,190,stage);
        starfish2 = new Starfish(190,190,stage);
        starfish3 = new Starfish(210,111,stage);

        sign = new Sign(150,230,stage,new Texture("sign.png"));
        sign1 = new Sign(312,410,stage,new Texture("sign.png"));

        tank = new Shark(Gdx.graphics.getWidth() - 100,Gdx.graphics.getHeight() - 262,stage);

        rockImage = new Texture("rock.png");
        sighImage = new Texture("sign.png");
        win = new Texture("you-win.png");
        gameover = new Texture("game-over.png");

        dropSound =Gdx.audio.newSound(Gdx.files.internal("Water_Drop.ogg"));
        nenMusic = Gdx.audio.newMusic(Gdx.files.internal("Master_of_the_Feast.ogg"));

        rock.polygon.setPosition(rock.getX(),rock.getY());
        rock1.polygon.setPosition(rock2.getX(),rock2.getY());
        rock2.polygon.setPosition(rock3.getX(),rock3.getY());
        rock3.polygon.setPosition(rock3.getX(),rock3.getY());
        sign.polygon.setPosition(sign.getX(),sign.getY());
        sign1.polygon.setPosition(sign1.getX(),sign1.getY());
        turtle.polygon.setPosition(turtle .getX(),turtle .getY());
        tank.polygon.setPosition(tank.getX(),tank.getY());

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        TextButton startButton = new TextButton( "",style);
        startButton.setPosition(Gdx.graphics.getWidth() - startButton.getWidth() - 5,
            Gdx.graphics.getHeight() - startButton.getHeight() - 5) ;
        stage.addActor(startButton);
        input.setInputProcessor(stage);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(audio){
                    audio = false;
                }else{
                    audio = true;
                }
            }
        });
        System.out.println(stage.getCamera().position.x + " "+stage.getCamera().position.y);

    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.draw();
        stage.act();

//        if(turtle.getX()>320 - && turtle.getX()<880) {
//            stage.getCamera().position.x = turtle.getX();
//        }
//        if(turtle.getY()>240 && turtle.getY()<560) {
//            stage.getCamera().position.y = turtle.getY();
//        }
        if((turtle.getX()>320-(turtle.speed * MathUtils.cosDeg(turtle.getRotation()))-turtle.getWidth()/2)&&(turtle.getX()<1200-(turtle.speed * MathUtils.cosDeg(turtle.getRotation()))-turtle.getWidth()/2)) {
            stage.getCamera().position.x = turtle.getX()+ turtle.getWidth()/2;
        }
        if((turtle.getY()>240- (turtle.speed*MathUtils.sinDeg(turtle.getRotation()))-turtle.getHeight()/2)&&(turtle.getY()<800-(turtle.speed*MathUtils.sinDeg(turtle.getRotation()))-turtle.getHeight()/2)) {
            stage.getCamera().position.y = turtle.getY()+turtle.getHeight()/2;
        }



        if(stage.getCamera().position.x<320){
            stage.getCamera().position.x=320;
        }
        if(stage.getCamera().position.x>880){
            stage.getCamera().position.x=880;
        }
        if(stage.getCamera().position.y<240){
            stage.getCamera().position.y=240;
        }
        if(stage.getCamera().position.y>560){
            stage.getCamera().position.y=560;
        }

        game.batch.begin();
        game.font.draw(game.batch, "starfish:" + String.valueOf(starfish), 0, Gdx.graphics.getHeight());
        if (starfish < 1) {
            game.batch.draw(win, Gdx.graphics.getWidth() / 2 - win.getWidth() / 2, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2);
            kt = true;
            stage.clear();
        }
        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), tank.getPolygon())) {
            game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2 + 10);
           kt = true;
           stage.clear();
           nenMusic.stop();
        }
        game.batch.end();
        if(kt == false) {
            if (audio) {
                nenMusic.play();
            }
        }

        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), rock.getPolygon())
            || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock1.getPolygon())
            || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock2.getPolygon())
            || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock3.getPolygon())
            || Intersector.overlapConvexPolygons(turtle.getPolygon(), sign.getPolygon())
            || Intersector.overlapConvexPolygons(turtle.getPolygon(), sign1.getPolygon())) {
            turtle.moveBy(-3 * MathUtils.cosDeg(turtle.getRotation()), -3 * MathUtils.sinDeg(turtle.getRotation()));
        }

        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), starfish1.getPolygon())) {
            an = new An(new Texture("whirlpool.png"), 5, 2);
            dropSound.play();

            if(starfish==1){
                an = new An(new Texture("sparkle.png"), 8, 8);
            }
            an.setPosition(starfish1.getX(), starfish1.getY());
            starfish1.setPosition(MathUtils.random(Gdx.graphics.getWidth() - starfish1.getWidth()), MathUtils.random( Gdx.graphics.getHeight() - starfish1.getHeight()));
            stage.addActor(an);
            starfish--;
        }

        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), starfish2.getPolygon())) {
            an = new An(new Texture("whirlpool.png"), 5, 2);
            dropSound.play();

            if(starfish==1){
                an = new An(new Texture("sparkle.png"), 8, 8);
            }
            an.setPosition(starfish2.getX(), starfish2.getY());
            starfish2.setPosition(MathUtils.random(Gdx.graphics.getWidth() - starfish2.getWidth()), MathUtils.random( Gdx.graphics.getHeight() - starfish2.getHeight()));
            stage.addActor(an);
            starfish--;
        }

        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), starfish3.getPolygon())) {
            an = new An(new Texture("whirlpool.png"), 5, 2);
            dropSound.play();

            if(starfish==1){
                an = new An(new Texture("sparkle.png"), 8, 8);
            }
            an.setPosition(starfish3.getX(), starfish3.getY());
            starfish3.setPosition(MathUtils.random(Gdx.graphics.getWidth() - starfish3.getWidth()), MathUtils.random( Gdx.graphics.getHeight() - starfish3.getHeight()));
            stage.addActor(an);
            starfish--;

        }


        if(input.isKeyJustPressed(Input.Keys.C) && kt){
            game.setScreen(new MenuScreen(game));// goi man hinh moi
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


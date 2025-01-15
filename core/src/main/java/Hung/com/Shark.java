package Hung.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class Shark extends Actor {
    TextureRegion textureregion;
    Polygon polygon;

    int speedX = 2 ;
    Shark(float x,float y, Stage s){
        textureregion = new TextureRegion(new Texture("sharky.png")) ;
        setPosition(x,y);
        setRotation(0);
        setSize(textureregion.getRegionWidth(), textureregion.getRegionHeight());
        s.addActor(this);
        float[]toadocacdiem = {
            14,2,
            12,16,
            1,30,
            8,34,
            16,35,
            23,32,
            36,42,
            33,61,
            36,64,
            64,50,
            75,47,
            82,44,
            90,38,
            97,33,
            98,28,
            95,22,
            82,12,
            66,9,
            61,4,
            58,7,
            49,0,
            45,10,
            29,14,
        };
        polygon = new Polygon(toadocacdiem);
        polygon.setOrigin(getWidth()/2,getHeight()/2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureregion, getX(), getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(), getScaleY(),getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setOrigin(getWidth()/2,getHeight()/2);
        polygon.setRotation(getRotation());
        polygon.setPosition(getX(),getY());

        if(getX()>Gdx.graphics.getWidth()){
            setScaleY(-getScaleY());
            setRotation(180);

        }
        if(getX()<0){
            setScaleY(-getScaleY());
            setRotation(0);

        }


        moveBy(4*MathUtils.cosDeg(getRotation()),4*MathUtils.sinDeg(getRotation()));

    }
    public Rectangle getbounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    public Polygon getPolygon() {
        return polygon;
    }
}

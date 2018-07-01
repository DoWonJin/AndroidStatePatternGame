package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.SpriteAnimation;

public class Player extends SpriteAnimation {
    Rect m_BoundBox= new Rect( );
    Bitmap bitmap;
    private int item_speed=0;
    private int item_hp=0;
    private int item_skill=0;
    long LastShoot= System.currentTimeMillis( );
    int m_Life= 10;
    public Player ( Bitmap bitmap) {
            super(bitmap);
            this.initSpriteData(bitmap.getWidth()/6, bitmap.getHeight(),4 ,6);
            this.setPosition(AppManager.getInstance().getDeviceSize().x/2
            ,AppManager.getInstance().getDeviceSize().y-1000);
            this.bitmap = bitmap;
        }



    public void Update(long gameTime ) {
        super.Update(gameTime);
        Attak();
        m_BoundBox.left= m_x;
        m_BoundBox.top= m_y;
        m_BoundBox.right= m_x+ bitmap.getWidth();
        m_BoundBox.bottom= m_y+ bitmap.getHeight();
    }

    public void Attak(){

        if(System.currentTimeMillis( )-LastShoot>= 700 ) { //1초간격으로 발사하는 알고리즘: LastRegenMissile는 시간을 담는 그릇일 뿐...
            LastShoot = System.currentTimeMillis();
            AppManager.getInstance().m_gameState.m_pmslist.add(
                    new Missile_Player(AppManager.getInstance().m_gameState.m_player.getX()
                                    , AppManager.getInstance().m_gameState.m_player.getY())
            );         //플레이어의 현위치에서 미사일 생성및 리스트에 등록
        }
    }

    public void GetItem(Item item){
        if(item instanceof Item_Skill)item_skill++;

    }
    public void UseItem(Item item){
        if(item instanceof Item_Skill)item_skill--;

    }
    public int getLife( ) { return m_Life; }
    public void addLife( ) { m_Life++; }
    public void destroyPlayer( ) { m_Life--; }
    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
        Paint p = new Paint( );
        p.setTextSize(100);
        p.setColor(Color. BLACK);
        canvas.drawText("필살기 : "+String.valueOf(item_skill)
                , AppManager.getInstance().getDeviceSize().x-600
                , AppManager.getInstance().getDeviceSize().y-400
                , p);
//        Paint paint = new Paint(); // 페인트 객체 생성
//        paint.setColor(Color.RED); // 빨간색으로 설정
//        canvas.drawRect(m_x, m_y, m_x+ bitmap.getWidth()/6,  m_y+ bitmap.getHeight(), paint);

    }
}

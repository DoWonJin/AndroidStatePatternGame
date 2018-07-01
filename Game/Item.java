package Game;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.SpriteAnimation;

public class Item extends SpriteAnimation{
    public static final int STATE_NORMAL= 0;
    public static final int STATE_OUT= 1;
    public int state= STATE_NORMAL;

    public static final int MOVE_PATTERN_1= 0;
    public static final int MOVE_PATTERN_2= 1;
    public static final int MOVE_PATTERN_3= 2;

    protected int movetype;
    protected float speed;
    Bitmap m_bitmap;
    Rect m_BoundBox = new Rect();

    public Item(Bitmap bitmap)
    {
        super(bitmap);
        m_bitmap = bitmap;
    }

    void Move( ) {
        // 움직이는로직
        // 움직이는로직
        if( movetype== MOVE_PATTERN_1) {
            // 첫번째패턴
            if( m_y<= 200 ) m_y+= speed;// 중간지점까지기본속도
            else m_y+= speed;// 중간지점이후빠른속도
        }
        else if(movetype== MOVE_PATTERN_2) {
            // 두번째패턴
            if( m_y<= 200 ) m_y+= speed;// 중간지점까지일자로이동
            else {
                m_x += speed;// 중감지점이후대각선이동
                m_y += speed;
            }
        }
        else if( movetype== MOVE_PATTERN_3) {
            // 세번째패턴
            if( m_y<= 200 ) m_y+= speed;// 중간지점까지일자로이동
            else {
                m_x -= speed;// 중감지점이후대각선이동
                m_y += speed;
            }
        }
        if( m_y> AppManager.getInstance().getDeviceSize().y) state= STATE_OUT;
        if( m_x<0 || m_x > AppManager.getInstance().getDeviceSize().x) state= STATE_OUT;
    }



    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        Move(); //Move를 실행시키므로써 위치m_x,m_y값을 업뎃.
        m_BoundBox.set(getX(),getY(),getX()+getBitmapWidth()/4,getY()+getBitmapHeight());
    }
}

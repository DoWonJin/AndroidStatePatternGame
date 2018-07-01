package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.R;
import org.techtown.mypassion.SpriteAnimation;

public class Enemy extends SpriteAnimation {
        public static final int STATE_NORMAL= 0;
        public static final int STATE_OUT= 1;
        public int state= STATE_NORMAL;

        public static final int MOVE_PATTERN_1= 0;
        public static final int MOVE_PATTERN_2= 1;
        public static final int MOVE_PATTERN_3= 2;
        protected int movetype;

        protected int hp;
        protected float speed;
        Bitmap m_bitmap;
        Rect m_BoundBox= new Rect( ); //충돌계산을 위한 Rect박스

        long LastShoot= System.currentTimeMillis( );

        public Enemy(Bitmap bitmap) {
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
        void Attack( ) {
            // 공격하는로직
            if(System.currentTimeMillis( )-LastShoot>= 1000) {
                LastShoot= System.currentTimeMillis( );
            // 미사일발사로직
            AppManager.getInstance( ).m_gameState.m_enemmslist.add(new Missile_Enemy( m_x+40, m_y+m_bitmap.getHeight()-100));
            }
        }

        @Override
        public void Update( long GameTime) {
            super.Update(GameTime);
            Attack( );
            Move(); //Move를 실행시키므로써 위치m_x,m_y값을 업뎃.
            m_BoundBox.set( m_x, m_y, m_x+ m_bitmap.getWidth()/6
                    , m_y+ m_bitmap.getHeight());

        }
        @Override
        public void Draw(Canvas canvas) {
            super.Draw(canvas);
//            Paint paint = new Paint(); // 페인트 객체 생성
//            paint.setColor(Color.RED); // 빨간색으로 설정
//            canvas.drawRect(m_x, m_y, m_x+ m_bitmap.getWidth()/6,  m_y+ m_bitmap.getHeight(), paint);
        }

}

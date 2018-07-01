package Game;

import android.graphics.Bitmap;

import org.techtown.mypassion.SpriteAnimation;

public class Enemy extends SpriteAnimation {
        public static final int MOVE_PATTERN_1= 0;
        public static final int MOVE_PATTERN_2= 1;
        public static final int MOVE_PATTERN_3= 2;
        protected int movetype;

        protected int hp;
        protected float speed;

        public Enemy(Bitmap bitmap) {
            super(bitmap);
        }

        void Move( ) {
        // 움직이는로직
            // 움직이는로직
            if( movetype== MOVE_PATTERN_1) {
            // 첫번째패턴
                if( m_y<= 200 ) m_y+= speed;// 중간지점까지기본속도
                else m_y+= speed* 3;// 중간지점이후빠른속도
            }
            else if(movetype== MOVE_PATTERN_2) {
            // 두번째패턴
                if( m_y<= 200 ) m_y+= speed;// 중간지점까지일자로이동
                else {
                    m_x += speed*3;// 중감지점이후대각선이동
                    m_y += speed*3;
                }
            }
            else if( movetype== MOVE_PATTERN_3) {
            // 세번째패턴
                if( m_y<= 200 ) m_y+= speed;// 중간지점까지일자로이동
                else {
                    m_x -= speed*3;// 중감지점이후대각선이동
                    m_y += speed*3;
                }
            }
        }
        void Attack( ) {
        // 공격하는로직
        }

        @Override
        public void Update( long GameTime) {
            super.Update(GameTime);
            Move(); //Move를 실행시키므로써 위치m_x,m_y값을 업뎃.
        }

}

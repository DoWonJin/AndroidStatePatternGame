package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.GraphicObject;
import org.techtown.mypassion.R;

public class BackGround extends GraphicObject{
        static final float SCROLL_SPEED= 10f;
        private float m_scroll= -2000 + 480;

        Bitmap m_bitmap;
        Bitmap m_layer2;//구름

        static final float SCROLL_SPEED_2= 10f;
        private float m_scroll_2= -2000 + 480;
        public BackGround( int backtype) {
            super( null);
            if(backtype== 0)
                m_bitmap= AppManager.getInstance( ).getBitmap(R.drawable. background1);
            else if(backtype== 1) {
                m_bitmap = AppManager.getInstance().getBitmap(R.drawable.background2);
                m_layer2 = AppManager.getInstance().getBitmap(R.drawable.background_2);
            }
            setPosition(0, (int) m_scroll);
        }

        void Update() {
            m_scroll= m_scroll+ SCROLL_SPEED;
            if( m_scroll>=0) m_scroll= 0;
            setPosition(0, (int) m_scroll);
            m_scroll_2= m_scroll_2+ SCROLL_SPEED_2;
            if( m_scroll_2>=0) m_scroll_2= 0;
        }
        @Override
        public void Draw(Canvas canvas) {
                canvas.drawBitmap( m_bitmap, m_x, m_y, null);
                canvas.drawBitmap( m_layer2, m_x, m_scroll_2, null);
        }
}
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
        static final float SCROLL_SPEED= 40f;
        static final float SCROLL_SPEED_2= 40f;
        private float m_scroll=-3000;
        private float m_scroll_2=-3000;
        Bitmap m_bitmap;
        Bitmap m_layer2;//구름
        Bitmap m_layer3;

        public BackGround( int backtype) {
            super( null);
            if(backtype== 0) {
                m_bitmap = AppManager.getInstance().getBitmap(R.drawable.background1);
                m_bitmap = Bitmap.createScaledBitmap(m_bitmap, AppManager.getInstance().getDeviceSize().x,m_bitmap.getHeight(), true);
            }
            else if(backtype== 1) {
                m_bitmap = AppManager.getInstance().getBitmap(R.drawable.background1);
                m_bitmap = Bitmap.createScaledBitmap(m_bitmap, AppManager.getInstance().getDeviceSize().x, m_bitmap.getHeight(), true);
                m_layer2 = AppManager.getInstance().getBitmap(R.drawable.background2);
                m_layer2 = Bitmap.createScaledBitmap(m_layer2, AppManager.getInstance().getDeviceSize().x, m_layer2.getHeight(), true);
                m_layer3 = AppManager.getInstance().getBitmap(R.drawable.background_2);
                m_layer3 = Bitmap.createScaledBitmap(m_layer2, AppManager.getInstance().getDeviceSize().x, m_layer2.getHeight(), true);

            }
            m_scroll = - m_bitmap.getHeight() + AppManager.getInstance().getDeviceSize().y;
            m_scroll_2=-m_layer2.getHeight();
            setPosition(0, (int) m_scroll);
        }
        int over = 0;
        void Update() {
            m_scroll += SCROLL_SPEED;
            m_scroll_2 += SCROLL_SPEED_2;
            if( m_scroll>0 && over==0){
                m_scroll_2 = -m_layer2.getHeight();
                over = 1;
            }
            if(m_scroll_2>0 && over ==1){
                m_scroll = -m_bitmap.getHeight();
                over = 0;
            }

        }
        @Override
        public void Draw(Canvas canvas) {
                canvas.drawBitmap( m_bitmap, m_x, m_scroll, null);
                canvas.drawBitmap( m_layer2, m_x, m_scroll_2, null);
                canvas.drawBitmap( m_layer3, m_x, m_scroll_2, null);
        }
}
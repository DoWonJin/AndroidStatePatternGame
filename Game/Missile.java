package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import org.techtown.mypassion.AppManager;
import org.techtown.mypassion.GraphicObject;
import org.techtown.mypassion.R;

public class Missile extends GraphicObject {
        Rect m_BoundBox= new Rect( );
        public static final int STATE_NORMAL= 0;
        public static final int STATE_OUT= 1;
        public int state= STATE_NORMAL;
        Bitmap m_msl;
        public Missile(Bitmap bitmap)
        {
            super(bitmap);
            m_msl = bitmap;
        }
    public void Update( ) {
        if(m_y< 50) state= STATE_OUT;
        m_BoundBox.left= m_x;
        m_BoundBox.top= m_y;
        m_BoundBox.right= m_x+ m_msl.getWidth();
        m_BoundBox.bottom= m_y+ m_msl.getHeight();
    }
    @Override
    public void Draw(Canvas canvas) {
        super.Draw(canvas);
//        Paint paint = new Paint(); // 페인트 객체 생성
//        paint.setColor(Color.BLUE); // 빨간색으로 설정
//        canvas.drawRect(m_BoundBox.left, m_BoundBox.top, m_BoundBox.right,  m_BoundBox.bottom, paint);

    }

}
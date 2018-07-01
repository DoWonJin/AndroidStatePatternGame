package org.techtown.mypassion;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
    private Bitmap m_bitmap;
    protected int m_x;
    protected int m_y;

    public GraphicObject(Bitmap bitmap) {
        m_bitmap= bitmap;
        m_x= 0; //객체가 생성되면 위치를 (0,0)으로 초기화함.
        m_y= 0;
    }

    public void Draw(Canvas canvas) {

        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }
    // 좌표설정
    public void setPosition( int x, int y) {
        m_x= x;
        m_y= y;
    }
    // X, Y 각좌표반환
    public int getX( ) { return m_x; }
    public int getY( ) { return m_y; }

}

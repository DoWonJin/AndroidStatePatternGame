package org.techtown.mypassion;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject{

    private Rect m_rect;// 그려줄사각영역
    private int m_fps;// 밀리초 단위의 프레임
    private int m_iFrames;// 프레임개수
    private int m_currentFrame;// 최근프레임
    private int m_spriteWidth;
    private int m_spriteHeight;
    Bitmap m_bitmap;
    long m_frameTimer;
    long gameTime;
    public SpriteAnimation(Bitmap bitmap) {
        super(bitmap);
        m_bitmap = bitmap;
        m_rect= new Rect(0, 0, 0, 0);
        m_frameTimer= 0;
        m_currentFrame= 0;
    }
    public void initSpriteData(int _fps, int iFrame) {
        // 기본정보설정

        m_spriteWidth= m_bitmap.getWidth()/6;
        m_spriteHeight= m_bitmap.getWidth();
        m_rect.top= 0;  m_rect.bottom= m_spriteHeight;
        m_rect.left= 0; m_rect.right= m_spriteWidth;
        m_fps= 1000 / _fps;// 밀리초단위프레임
        m_iFrames= iFrame;
    }

    @Override
    public void Draw(Canvas canvas) {
        Rect dest= new Rect( m_x, m_y, m_x+ m_spriteWidth,
                m_y+ m_spriteHeight);
        canvas.drawBitmap( m_bitmap, m_rect, dest, null);

       //확인용
//        Paint p = new Paint( );
//        p.setTextSize(100);
//        p.setColor(Color. WHITE);
//        canvas.drawText("left: "+String.valueOf(m_rect.left)+" right: "+String.valueOf(m_rect.right), 0, 600, p);
//        canvas.drawText("currentFrame: "+String.valueOf(m_currentFrame), 0, 800, p);
//        canvas.drawText("gameTime: "+String.valueOf(gameTime), 0, 1600, p);


    }

    public void setPosition( int x, int y) {
        m_x= x;
        m_y= y;
    }
    public void Update( long gameTime) {
        this.gameTime = gameTime;
        if( gameTime> m_frameTimer+ m_fps) {
            m_frameTimer= gameTime;
            m_currentFrame+= 1;
// 프레임의순환
            if( m_currentFrame>= m_iFrames) m_currentFrame= 0;
        }

        m_rect.left= m_currentFrame* m_spriteWidth;
        m_rect.right= m_rect.left+ m_spriteWidth;
    }
}
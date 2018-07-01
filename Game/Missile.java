package Game;

import android.graphics.Bitmap;

import org.techtown.mypassion.GraphicObject;

public class Missile extends GraphicObject {
        public static final int STATE_NORMAL= 0;
        public static final int STATE_OUT= 1;
        public int state= STATE_NORMAL;
        public Missile(Bitmap bitmap)
        {
            super(bitmap);
        }
    public void Update( ) {
        if( m_y< 20 ) state= STATE_OUT;

    }

}